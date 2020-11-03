package main

import (
	"strings"
	"testing"

	"github.com/gruntwork-io/terratest/modules/helm"
	"github.com/gruntwork-io/terratest/modules/k8s"
	"github.com/gruntwork-io/terratest/modules/random"
	"github.com/stretchr/testify/require"
	appsV1 "k8s.io/api/apps/v1"
	coreV1 "k8s.io/api/core/v1"
	extensions "k8s.io/api/extensions/v1beta1"
	netV1 "k8s.io/api/networking/v1"
	metav1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	"k8s.io/apimachinery/pkg/util/intstr"
)

const (
	chartName     = "auto-deploy-app-1.0.7"
	helmChartPath = ".."
)

func TestDeploymentTemplate(t *testing.T) {
	for _, tc := range []struct {
		CaseName string
		Release  string
		Values   map[string]string

		ExpectedName         string
		ExpectedRelease      string
		ExpectedStrategyType extensions.DeploymentStrategyType
		ExpectedSelector     *metav1.LabelSelector
	}{
		{
			CaseName: "happy",
			Release:  "production",
			Values: map[string]string{
				"releaseOverride": "productionOverridden",
			},
			ExpectedName:         "productionOverridden",
			ExpectedRelease:      "production",
			ExpectedStrategyType: extensions.DeploymentStrategyType(""),
		},
		{
			CaseName:             "long release name",
			Release:              strings.Repeat("r", 80),
			ExpectedName:         strings.Repeat("r", 63),
			ExpectedRelease:      strings.Repeat("r", 80),
			ExpectedStrategyType: extensions.DeploymentStrategyType(""),
		},
		{
			CaseName: "strategyType",
			Release:  "production",
			Values: map[string]string{
				"strategyType": "Recreate",
			},
			ExpectedName:         "production",
			ExpectedRelease:      "production",
			ExpectedStrategyType: extensions.RecreateDeploymentStrategyType,
		},
		{
			CaseName: "enableSelector",
			Release:  "production",
			Values: map[string]string{
				"enableSelector": "true",
			},
			ExpectedName:         "production",
			ExpectedRelease:      "production",
			ExpectedStrategyType: extensions.DeploymentStrategyType(""),
			ExpectedSelector: &metav1.LabelSelector{
				MatchLabels: map[string]string{
					"app":     "production",
					"release": "production",
					"tier":    "web",
					"track":   "stable",
				},
			},
		},
	} {
		t.Run(tc.CaseName, func(t *testing.T) {
			namespaceName := "minimal-ruby-app-" + strings.ToLower(random.UniqueId())

			values := map[string]string{
				"gitlab.app": "auto-devops-examples/minimal-ruby-app",
				"gitlab.env": "prod",
			}

			mergeStringMap(values, tc.Values)

			options := &helm.Options{
				SetValues:      values,
				KubectlOptions: k8s.NewKubectlOptions("", "", namespaceName),
			}

			output := helm.RenderTemplate(t, options, helmChartPath, tc.Release, []string{"templates/deployment.yaml"})

			var deployment extensions.Deployment
			helm.UnmarshalK8SYaml(t, output, &deployment)

			require.Equal(t, tc.ExpectedName, deployment.Name)
			require.Equal(t, tc.ExpectedStrategyType, deployment.Spec.Strategy.Type)

			require.Equal(t, map[string]string{
				"app.gitlab.com/app": "auto-devops-examples/minimal-ruby-app",
				"app.gitlab.com/env": "prod",
			}, deployment.Annotations)
			require.Equal(t, map[string]string{
				"app":      tc.ExpectedName,
				"chart":    chartName,
				"heritage": "Tiller",
				"release":  tc.ExpectedRelease,
				"tier":     "web",
				"track":    "stable",
			}, deployment.Labels)

			require.Equal(t, tc.ExpectedSelector, deployment.Spec.Selector)

			require.Equal(t, map[string]string{
				"app.gitlab.com/app":           "auto-devops-examples/minimal-ruby-app",
				"app.gitlab.com/env":           "prod",
				"checksum/application-secrets": "",
			}, deployment.Spec.Template.Annotations)
			require.Equal(t, map[string]string{
				"app":     tc.ExpectedName,
				"release": tc.ExpectedRelease,
				"tier":    "web",
				"track":   "stable",
			}, deployment.Spec.Template.Labels)
		})
	}

	for _, tc := range []struct {
		CaseName                string
		Release                 string
		Values                  map[string]string
		ExpectedImageRepository string
	}{
		{
			CaseName: "skaffold",
			Release:  "production",
			Values: map[string]string{
				"image.repository": "skaffold",
				"image.tag":        "",
			},
			ExpectedImageRepository: "skaffold",
		},
		{
			CaseName: "skaffold",
			Release:  "production",
			Values: map[string]string{
				"image.repository": "skaffold",
				"image.tag":        "stable",
			},
			ExpectedImageRepository: "skaffold:stable",
		},
	} {
		t.Run(tc.CaseName, func(t *testing.T) {
			namespaceName := "minimal-ruby-app-" + strings.ToLower(random.UniqueId())

			values := map[string]string{
				"gitlab.app": "auto-devops-examples/minimal-ruby-app",
				"gitlab.env": "prod",
			}

			mergeStringMap(values, tc.Values)

			options := &helm.Options{
				SetValues:      values,
				KubectlOptions: k8s.NewKubectlOptions("", "", namespaceName),
			}

			output := helm.RenderTemplate(t, options, helmChartPath, tc.Release, []string{"templates/deployment.yaml"})

			var deployment appsV1.Deployment
			helm.UnmarshalK8SYaml(t, output, &deployment)

			require.Equal(t, tc.ExpectedImageRepository, deployment.Spec.Template.Spec.Containers[0].Image)
		})
	}

	// deployment livenessProbe, and readinessProbe tests
	for _, tc := range []struct {
		CaseName string
		Release  string
		Values   map[string]string

		ExpectedLivenessProbe  *coreV1.Probe
		ExpectedReadinessProbe *coreV1.Probe
	}{
		{
			CaseName:               "defaults",
			ExpectedLivenessProbe:  defaultLivenessProbe(),
			ExpectedReadinessProbe: defaultReadinessProbe(),
		},
	} {
		t.Run(tc.CaseName, func(t *testing.T) {
			namespaceName := "minimal-ruby-app-" + strings.ToLower(random.UniqueId())

			values := map[string]string{
				"gitlab.app": "auto-devops-examples/minimal-ruby-app",
				"gitlab.env": "prod",
			}

			mergeStringMap(values, tc.Values)

			options := &helm.Options{
				SetValues:      values,
				KubectlOptions: k8s.NewKubectlOptions("", "", namespaceName),
			}

			output := helm.RenderTemplate(t, options, helmChartPath, tc.Release, []string{"templates/deployment.yaml"})

			var deployment appsV1.Deployment
			helm.UnmarshalK8SYaml(t, output, &deployment)

			require.Equal(t, tc.ExpectedLivenessProbe, deployment.Spec.Template.Spec.Containers[0].LivenessProbe)
			require.Equal(t, tc.ExpectedReadinessProbe, deployment.Spec.Template.Spec.Containers[0].ReadinessProbe)
		})
	}

	for _, tc := range []struct {
		CaseName string
		Release  string
		Values   map[string]string

		ExpectedName         string
		ExpectedRelease      string
		ExpectedStrategyType appsV1.DeploymentStrategyType
		ExpectedSelector     *metav1.LabelSelector
	}{
		{
			CaseName: "appsv1",
			Release:  "production",
			Values: map[string]string{
				"deploymentApiVersion": "apps/v1",
			},
			ExpectedName:         "production",
			ExpectedRelease:      "production",
			ExpectedStrategyType: appsV1.DeploymentStrategyType(""),
			ExpectedSelector: &metav1.LabelSelector{
				MatchLabels: map[string]string{
					"app":     "production",
					"release": "production",
					"tier":    "web",
					"track":   "stable",
				},
			},
		},
	} {
		t.Run(tc.CaseName, func(t *testing.T) {
			namespaceName := "minimal-ruby-app-" + strings.ToLower(random.UniqueId())

			values := map[string]string{
				"gitlab.app": "auto-devops-examples/minimal-ruby-app",
				"gitlab.env": "prod",
			}

			mergeStringMap(values, tc.Values)

			options := &helm.Options{
				SetValues:      values,
				KubectlOptions: k8s.NewKubectlOptions("", "", namespaceName),
			}

			output := helm.RenderTemplate(t, options, helmChartPath, tc.Release, []string{"templates/deployment.yaml"})

			var deployment appsV1.Deployment
			helm.UnmarshalK8SYaml(t, output, &deployment)

			require.Equal(t, tc.ExpectedName, deployment.Name)
			require.Equal(t, tc.ExpectedStrategyType, deployment.Spec.Strategy.Type)
			require.Equal(t, map[string]string{
				"app.gitlab.com/app": "auto-devops-examples/minimal-ruby-app",
				"app.gitlab.com/env": "prod",
			}, deployment.Annotations)
			require.Equal(t, map[string]string{
				"app":      tc.ExpectedName,
				"chart":    chartName,
				"heritage": "Tiller",
				"release":  tc.ExpectedRelease,
				"tier":     "web",
				"track":    "stable",
			}, deployment.Labels)

			require.Equal(t, tc.ExpectedSelector, deployment.Spec.Selector)

			require.Equal(t, map[string]string{
				"app.gitlab.com/app":           "auto-devops-examples/minimal-ruby-app",
				"app.gitlab.com/env":           "prod",
				"checksum/application-secrets": "",
			}, deployment.Spec.Template.Annotations)
			require.Equal(t, map[string]string{
				"app":     tc.ExpectedName,
				"release": tc.ExpectedRelease,
				"tier":    "web",
				"track":   "stable",
			}, deployment.Spec.Template.Labels)
		})
	}
}

func TestWorkerDeploymentTemplate(t *testing.T) {
	for _, tc := range []struct {
		CaseName string
		Release  string
		Values   map[string]string

		ExpectedName        string
		ExpectedRelease     string
		ExpectedDeployments []workerDeploymentTestCase
	}{
		{
			CaseName: "happy",
			Release:  "production",
			Values: map[string]string{
				"releaseOverride":            "productionOverridden",
				"workers.worker1.command[0]": "echo",
				"workers.worker1.command[1]": "worker1",
				"workers.worker2.command[0]": "echo",
				"workers.worker2.command[1]": "worker2",
			},
			ExpectedName:    "productionOverridden",
			ExpectedRelease: "production",
			ExpectedDeployments: []workerDeploymentTestCase{
				{
					ExpectedName:         "productionOverridden-worker1",
					ExpectedCmd:          []string{"echo", "worker1"},
					ExpectedStrategyType: extensions.DeploymentStrategyType(""),
				},
				{
					ExpectedName:         "productionOverridden-worker2",
					ExpectedCmd:          []string{"echo", "worker2"},
					ExpectedStrategyType: extensions.DeploymentStrategyType(""),
				},
			},
		},
		{
			CaseName: "long release name",
			Release:  strings.Repeat("r", 80),
			Values: map[string]string{
				"workers.worker1.command[0]": "echo",
				"workers.worker1.command[1]": "worker1",
			},
			ExpectedName:    strings.Repeat("r", 63),
			ExpectedRelease: strings.Repeat("r", 80),
			ExpectedDeployments: []workerDeploymentTestCase{
				{
					ExpectedName:         strings.Repeat("r", 63) + "-worker1",
					ExpectedCmd:          []string{"echo", "worker1"},
					ExpectedStrategyType: extensions.DeploymentStrategyType(""),
				},
			},
		},
		{
			CaseName: "strategyType",
			Release:  "production",
			Values: map[string]string{
				"workers.worker1.command[0]":   "echo",
				"workers.worker1.command[1]":   "worker1",
				"workers.worker1.strategyType": "Recreate",
			},
			ExpectedName:    "production",
			ExpectedRelease: "production",
			ExpectedDeployments: []workerDeploymentTestCase{
				{
					ExpectedName:         "production" + "-worker1",
					ExpectedCmd:          []string{"echo", "worker1"},
					ExpectedStrategyType: extensions.RecreateDeploymentStrategyType,
				},
			},
		},
		{
			CaseName: "enableSelector",
			Release:  "production",
			Values: map[string]string{
				"enableSelector":             "true",
				"workers.worker1.command[0]": "echo",
				"workers.worker1.command[1]": "worker1",
				"workers.worker2.command[0]": "echo",
				"workers.worker2.command[1]": "worker2",
			},
			ExpectedName:    "production",
			ExpectedRelease: "production",
			ExpectedDeployments: []workerDeploymentTestCase{
				{
					ExpectedName:         "production-worker1",
					ExpectedCmd:          []string{"echo", "worker1"},
					ExpectedStrategyType: extensions.DeploymentStrategyType(""),
					ExpectedSelector: &metav1.LabelSelector{
						MatchLabels: map[string]string{
							"release": "production",
							"tier":    "worker",
							"track":   "stable",
						},
					},
				},
				{
					ExpectedName:         "production-worker2",
					ExpectedCmd:          []string{"echo", "worker2"},
					ExpectedStrategyType: extensions.DeploymentStrategyType(""),
					ExpectedSelector: &metav1.LabelSelector{
						MatchLabels: map[string]string{
							"release": "production",
							"tier":    "worker",
							"track":   "stable",
						},
					},
				},
			},
		},
	} {
		t.Run(tc.CaseName, func(t *testing.T) {
			namespaceName := "minimal-ruby-app-" + strings.ToLower(random.UniqueId())

			values := map[string]string{
				"gitlab.app": "auto-devops-examples/minimal-ruby-app",
				"gitlab.env": "prod",
			}

			mergeStringMap(values, tc.Values)

			options := &helm.Options{
				SetValues:      values,
				KubectlOptions: k8s.NewKubectlOptions("", "", namespaceName),
			}

			output := helm.RenderTemplate(t, options, helmChartPath, tc.Release, []string{"templates/worker-deployment.yaml"})

			var deployments deploymentList
			helm.UnmarshalK8SYaml(t, output, &deployments)

			require.Len(t, deployments.Items, len(tc.ExpectedDeployments))
			for i, expectedDeployment := range tc.ExpectedDeployments {
				deployment := deployments.Items[i]

				require.Equal(t, expectedDeployment.ExpectedName, deployment.Name)
				require.Equal(t, expectedDeployment.ExpectedStrategyType, deployment.Spec.Strategy.Type)

				require.Equal(t, map[string]string{
					"app.gitlab.com/app": "auto-devops-examples/minimal-ruby-app",
					"app.gitlab.com/env": "prod",
				}, deployment.Annotations)
				require.Equal(t, map[string]string{
					"chart":    chartName,
					"heritage": "Tiller",
					"release":  tc.ExpectedRelease,
					"tier":     "worker",
					"track":    "stable",
				}, deployment.Labels)

				require.Equal(t, expectedDeployment.ExpectedSelector, deployment.Spec.Selector)

				require.Equal(t, map[string]string{
					"app.gitlab.com/app":           "auto-devops-examples/minimal-ruby-app",
					"app.gitlab.com/env":           "prod",
					"checksum/application-secrets": "",
				}, deployment.Spec.Template.Annotations)
				require.Equal(t, map[string]string{
					"release": tc.ExpectedRelease,
					"tier":    "worker",
					"track":   "stable",
				}, deployment.Spec.Template.Labels)

				require.Len(t, deployment.Spec.Template.Spec.Containers, 1)
				require.Equal(t, expectedDeployment.ExpectedCmd, deployment.Spec.Template.Spec.Containers[0].Command)
			}
		})
	}

	for _, tc := range []struct {
		CaseName string
		Release  string
		Values   map[string]string

		ExpectedName        string
		ExpectedRelease     string
		ExpectedDeployments []workerDeploymentAppsV1TestCase
	}{
		{
			CaseName: "appsv1",
			Release:  "production",
			Values: map[string]string{
				"deploymentApiVersion":       "apps/v1",
				"workers.worker1.command[0]": "echo",
				"workers.worker1.command[1]": "worker1",
				"workers.worker2.command[0]": "echo",
				"workers.worker2.command[1]": "worker2",
			},
			ExpectedName:    "production",
			ExpectedRelease: "production",
			ExpectedDeployments: []workerDeploymentAppsV1TestCase{
				{
					ExpectedName:         "production-worker1",
					ExpectedCmd:          []string{"echo", "worker1"},
					ExpectedStrategyType: appsV1.DeploymentStrategyType(""),
					ExpectedSelector: &metav1.LabelSelector{
						MatchLabels: map[string]string{
							"release": "production",
							"tier":    "worker",
							"track":   "stable",
						},
					},
				},
				{
					ExpectedName:         "production-worker2",
					ExpectedCmd:          []string{"echo", "worker2"},
					ExpectedStrategyType: appsV1.DeploymentStrategyType(""),
					ExpectedSelector: &metav1.LabelSelector{
						MatchLabels: map[string]string{
							"release": "production",
							"tier":    "worker",
							"track":   "stable",
						},
					},
				},
			},
		},
	} {
		t.Run(tc.CaseName, func(t *testing.T) {
			namespaceName := "minimal-ruby-app-" + strings.ToLower(random.UniqueId())

			values := map[string]string{
				"gitlab.app": "auto-devops-examples/minimal-ruby-app",
				"gitlab.env": "prod",
			}

			mergeStringMap(values, tc.Values)

			options := &helm.Options{
				SetValues:      values,
				KubectlOptions: k8s.NewKubectlOptions("", "", namespaceName),
			}

			output := helm.RenderTemplate(t, options, helmChartPath, tc.Release, []string{"templates/worker-deployment.yaml"})

			var deployments deploymentAppsV1List
			helm.UnmarshalK8SYaml(t, output, &deployments)

			require.Len(t, deployments.Items, len(tc.ExpectedDeployments))
			for i, expectedDeployment := range tc.ExpectedDeployments {
				deployment := deployments.Items[i]

				require.Equal(t, expectedDeployment.ExpectedName, deployment.Name)
				require.Equal(t, expectedDeployment.ExpectedStrategyType, deployment.Spec.Strategy.Type)

				require.Equal(t, map[string]string{
					"app.gitlab.com/app": "auto-devops-examples/minimal-ruby-app",
					"app.gitlab.com/env": "prod",
				}, deployment.Annotations)
				require.Equal(t, map[string]string{
					"chart":    chartName,
					"heritage": "Tiller",
					"release":  tc.ExpectedRelease,
					"tier":     "worker",
					"track":    "stable",
				}, deployment.Labels)

				require.Equal(t, expectedDeployment.ExpectedSelector, deployment.Spec.Selector)

				require.Equal(t, map[string]string{
					"app.gitlab.com/app":           "auto-devops-examples/minimal-ruby-app",
					"app.gitlab.com/env":           "prod",
					"checksum/application-secrets": "",
				}, deployment.Spec.Template.Annotations)
				require.Equal(t, map[string]string{
					"release": tc.ExpectedRelease,
					"tier":    "worker",
					"track":   "stable",
				}, deployment.Spec.Template.Labels)

				require.Len(t, deployment.Spec.Template.Spec.Containers, 1)
				require.Equal(t, expectedDeployment.ExpectedCmd, deployment.Spec.Template.Spec.Containers[0].Command)
			}
		})
	}

	// worker livenessProbe, and readinessProbe tests
	for _, tc := range []struct {
		CaseName string
		Values   map[string]string
		Release  string

		ExpectedDeployments []workerDeploymentTestCase
	}{
		{
			CaseName: "default liveness and readiness values",
			Release:  "production",
			Values: map[string]string{
				"workers.worker1.command[0]": "echo",
				"workers.worker1.command[1]": "worker1",
				"workers.worker2.command[0]": "echo",
				"workers.worker2.command[1]": "worker2",
			},
			ExpectedDeployments: []workerDeploymentTestCase{
				{
					ExpectedName:           "production-worker1",
					ExpectedCmd:            []string{"echo", "worker1"},
					ExpectedLivenessProbe:  defaultLivenessProbe(),
					ExpectedReadinessProbe: defaultReadinessProbe(),
				},
				{
					ExpectedName:           "production-worker2",
					ExpectedCmd:            []string{"echo", "worker2"},
					ExpectedLivenessProbe:  defaultLivenessProbe(),
					ExpectedReadinessProbe: defaultReadinessProbe(),
				},
			},
		},
	} {
		t.Run(tc.CaseName, func(t *testing.T) {
			namespaceName := "minimal-ruby-app-" + strings.ToLower(random.UniqueId())

			values := map[string]string{
				"gitlab.app": "auto-devops-examples/minimal-ruby-app",
				"gitlab.env": "prod",
			}

			mergeStringMap(values, tc.Values)

			options := &helm.Options{
				SetValues:      values,
				KubectlOptions: k8s.NewKubectlOptions("", "", namespaceName),
			}

			output := helm.RenderTemplate(t, options, helmChartPath, tc.Release, []string{"templates/worker-deployment.yaml"})

			var deployments deploymentAppsV1List
			helm.UnmarshalK8SYaml(t, output, &deployments)

			require.Len(t, deployments.Items, len(tc.ExpectedDeployments))

			for i, expectedDeployment := range tc.ExpectedDeployments {
				deployment := deployments.Items[i]
				require.Equal(t, expectedDeployment.ExpectedName, deployment.Name)
				require.Len(t, deployment.Spec.Template.Spec.Containers, 1)
				require.Equal(t, expectedDeployment.ExpectedCmd, deployment.Spec.Template.Spec.Containers[0].Command)
				require.Equal(t, expectedDeployment.ExpectedLivenessProbe, deployment.Spec.Template.Spec.Containers[0].LivenessProbe)
				require.Equal(t, expectedDeployment.ExpectedReadinessProbe, deployment.Spec.Template.Spec.Containers[0].ReadinessProbe)
			}
		})
	}
}

func TestNetworkPolicyDeployment(t *testing.T) {
	releaseName := "network-policy-test"
	templates := []string{"templates/network-policy.yaml"}
	expectedLabels := map[string]string{
		"app":      releaseName,
		"chart":    chartName,
		"release":  releaseName,
		"heritage": "Tiller",
	}

	tcs := []struct {
		name       string
		valueFiles []string
		values     map[string]string

		meta        metav1.ObjectMeta
		podSelector metav1.LabelSelector
		policyTypes []netV1.PolicyType
		ingress     []netV1.NetworkPolicyIngressRule
		egress      []netV1.NetworkPolicyEgressRule
	}{
		{
			name: "defaults",
		},
		{
			name:        "with default policy",
			values:      map[string]string{"networkPolicy.enabled": "true"},
			meta:        metav1.ObjectMeta{Name: releaseName + "-auto-deploy", Labels: expectedLabels},
			podSelector: metav1.LabelSelector{MatchLabels: map[string]string{}},
			ingress: []netV1.NetworkPolicyIngressRule{
				{
					From: []netV1.NetworkPolicyPeer{
						{PodSelector: &metav1.LabelSelector{MatchLabels: map[string]string{}}},
						{NamespaceSelector: &metav1.LabelSelector{
							MatchLabels: map[string]string{"app.gitlab.com/managed_by": "gitlab"},
						}},
					},
				},
			},
		},
		{
			name:        "with custom policy",
			valueFiles:  []string{"./testdata/custom-policy.yaml"},
			meta:        metav1.ObjectMeta{Name: releaseName + "-auto-deploy", Labels: expectedLabels},
			podSelector: metav1.LabelSelector{MatchLabels: map[string]string{"foo": "bar"}},
			ingress: []netV1.NetworkPolicyIngressRule{
				{
					From: []netV1.NetworkPolicyPeer{
						{PodSelector: &metav1.LabelSelector{MatchLabels: map[string]string{}}},
						{NamespaceSelector: &metav1.LabelSelector{
							MatchLabels: map[string]string{"name": "foo"},
						}},
					},
				},
			},
		},
		{
			name:        "with full spec policy",
			valueFiles:  []string{"./testdata/full-spec-policy.yaml"},
			meta:        metav1.ObjectMeta{Name: releaseName + "-auto-deploy", Labels: expectedLabels},
			podSelector: metav1.LabelSelector{MatchLabels: map[string]string{}},
			policyTypes: []netV1.PolicyType{"Ingress", "Egress"},
			ingress: []netV1.NetworkPolicyIngressRule{
				{
					From: []netV1.NetworkPolicyPeer{
						{PodSelector: &metav1.LabelSelector{MatchLabels: map[string]string{}}},
					},
				},
			},
			egress: []netV1.NetworkPolicyEgressRule{
				{
					To: []netV1.NetworkPolicyPeer{
						{NamespaceSelector: &metav1.LabelSelector{
							MatchLabels: map[string]string{"name": "gitlab-managed-apps"},
						}},
					},
				},
			},
		},
	}

	for _, tc := range tcs {
		t.Run(tc.name, func(t *testing.T) {
			opts := &helm.Options{
				ValuesFiles: tc.valueFiles,
				SetValues:   tc.values,
			}
			output := helm.RenderTemplate(t, opts, helmChartPath, releaseName, templates)

			policy := new(netV1.NetworkPolicy)
			helm.UnmarshalK8SYaml(t, output, policy)

			require.Equal(t, tc.meta, policy.ObjectMeta)
			require.Equal(t, tc.podSelector, policy.Spec.PodSelector)
			require.Equal(t, tc.policyTypes, policy.Spec.PolicyTypes)
			require.Equal(t, tc.ingress, policy.Spec.Ingress)
			require.Equal(t, tc.egress, policy.Spec.Egress)
		})
	}
}

func TestIngressTemplate_ModSecurity(t *testing.T) {
	templates := []string{"templates/ingress.yaml"}
	modSecuritySnippet := "SecRuleEngine DetectionOnly\n"
	modSecuritySnippetWithSecRules := modSecuritySnippet + `SecRule REQUEST_HEADERS:User-Agent \"scanner\" \"log,deny,id:107,status:403,msg:\'Scanner Identified\'\"
SecRule REQUEST_HEADERS:Content-Type \"text/plain\" \"log,deny,id:\'20010\',status:403,msg:\'Text plain not allowed\'\"
`
	defaultAnnotations := map[string]string{
		"kubernetes.io/ingress.class": "nginx",
		"kubernetes.io/tls-acme":      "true",
	}
	defaultModSecurityAnnotations := map[string]string{
		"nginx.ingress.kubernetes.io/modsecurity-transaction-id": "$server_name-$request_id",
	}
	modSecurityAnnotations := make(map[string]string)
	secRulesAnnotations := make(map[string]string)
	mergeStringMap(modSecurityAnnotations, defaultAnnotations)
	mergeStringMap(modSecurityAnnotations, defaultModSecurityAnnotations)
	mergeStringMap(secRulesAnnotations, defaultAnnotations)
	mergeStringMap(secRulesAnnotations, defaultModSecurityAnnotations)
	modSecurityAnnotations["nginx.ingress.kubernetes.io/modsecurity-snippet"] = modSecuritySnippet
	secRulesAnnotations["nginx.ingress.kubernetes.io/modsecurity-snippet"] = modSecuritySnippetWithSecRules

	tcs := []struct {
		name       string
		valueFiles []string
		values     map[string]string
		meta       metav1.ObjectMeta
	}{
		{
			name: "defaults",
			meta: metav1.ObjectMeta{Annotations: defaultAnnotations},
		},
		{
			name:   "with modSecurity enabled without custom secRules",
			values: map[string]string{"ingress.modSecurity.enabled": "true"},
			meta:   metav1.ObjectMeta{Annotations: modSecurityAnnotations},
		},
		{
			name:       "with custom secRules",
			valueFiles: []string{"./testdata/modsecurity-ingress.yaml"},
			meta:       metav1.ObjectMeta{Annotations: secRulesAnnotations},
		},
	}

	for _, tc := range tcs {
		t.Run(tc.name, func(t *testing.T) {
			opts := &helm.Options{
				ValuesFiles: tc.valueFiles,
				SetValues:   tc.values,
			}
			output := helm.RenderTemplate(t, opts, helmChartPath, "", templates)

			ingress := new(extensions.Ingress)
			helm.UnmarshalK8SYaml(t, output, ingress)

			require.Equal(t, tc.meta.Annotations, ingress.ObjectMeta.Annotations)
		})
	}
}

func TestIngressTemplate_Disable(t *testing.T) {
	templates := []string{"templates/ingress.yaml"}
	releaseName := "ingress-disable-test"
	tcs := []struct {
		name   string
		values map[string]string

		expectedrelease string
	}{
		{
			name:            "defaults",
			expectedrelease: releaseName + "-auto-deploy",
		},
		{
			name:            "with ingress.enabled key undefined, but service is enabled",
			values:          map[string]string{"ingress.enabled": "null", "service.enabled": "true"},
			expectedrelease: releaseName + "-auto-deploy",
		},
		{
			name:            "with service enabled and track non-stable",
			values:          map[string]string{"service.enabled": "true", "application.track": "non-stable"},
			expectedrelease: "",
		},
		{
			name:            "with service disabled and track stable",
			values:          map[string]string{"service.enabled": "false", "application.track": "stable"},
			expectedrelease: "",
		},
		{
			name:            "with service disabled and track non-stable",
			values:          map[string]string{"service.enabled": "false", "application.track": "non-stable"},
			expectedrelease: "",
		},
		{
			name:            "with ingress disabled",
			values:          map[string]string{"ingress.enabled": "false"},
			expectedrelease: "",
		},
		{
			name:            "with ingress enabled and track non-stable",
			values:          map[string]string{"ingress.enabled": "true", "application.track": "non-stable"},
			expectedrelease: "",
		},
		{
			name:            "with ingress enabled and service disabled",
			values:          map[string]string{"ingress.enabled": "true", "service.enabled": "false"},
			expectedrelease: "",
		},
		{
			name:            "with ingress disabled and service enabled and track stable",
			values:          map[string]string{"ingress.enabled": "false", "service.enabled": "true", "application.track": "stable"},
			expectedrelease: "",
		},
	}

	for _, tc := range tcs {
		t.Run(tc.name, func(t *testing.T) {
			opts := &helm.Options{
				SetValues: tc.values,
			}
			output := helm.RenderTemplate(t, opts, helmChartPath, releaseName, templates)

			ingress := new(extensions.Ingress)

			helm.UnmarshalK8SYaml(t, output, ingress)
			require.Equal(t, tc.expectedrelease, ingress.ObjectMeta.Name)
		})
	}
}

func TestServiceTemplate_Disable(t *testing.T) {
	templates := []string{"templates/service.yaml"}
	releaseName := "service-disable-test"
	tcs := []struct {
		name   string
		values map[string]string

		expectedrelease string
	}{
		{
			name:            "defaults",
			expectedrelease: releaseName + "-auto-deploy",
		},
		{
			name:            "with service enabled and track non-stable",
			values:          map[string]string{"service.enabled": "true", "application.track": "non-stable"},
			expectedrelease: "",
		},
		{
			name:            "with service disabled and track stable",
			values:          map[string]string{"service.enabled": "false", "application.track": "stable"},
			expectedrelease: "",
		},
		{
			name:            "with service disabled and track non-stable",
			values:          map[string]string{"service.enabled": "false", "application.track": "non-stable"},
			expectedrelease: "",
		},
	}

	for _, tc := range tcs {
		t.Run(tc.name, func(t *testing.T) {
			opts := &helm.Options{
				SetValues: tc.values,
			}
			output := helm.RenderTemplate(t, opts, helmChartPath, releaseName, templates)

			service := new(coreV1.Service)

			helm.UnmarshalK8SYaml(t, output, service)

			require.Equal(t, tc.expectedrelease, service.ObjectMeta.Name)
		})
	}
}

type workerDeploymentTestCase struct {
	ExpectedName           string
	ExpectedCmd            []string
	ExpectedStrategyType   extensions.DeploymentStrategyType
	ExpectedSelector       *metav1.LabelSelector
	ExpectedLivenessProbe  *coreV1.Probe
	ExpectedReadinessProbe *coreV1.Probe
}

type workerDeploymentAppsV1TestCase struct {
	ExpectedName         string
	ExpectedCmd          []string
	ExpectedStrategyType appsV1.DeploymentStrategyType
	ExpectedSelector     *metav1.LabelSelector
}

type deploymentList struct {
	metav1.TypeMeta `json:",inline"`

	Items []extensions.Deployment `json:"items" protobuf:"bytes,2,rep,name=items"`
}

type deploymentAppsV1List struct {
	metav1.TypeMeta `json:",inline"`

	Items []appsV1.Deployment `json:"items" protobuf:"bytes,2,rep,name=items"`
}

func mergeStringMap(dst, src map[string]string) {
	for k, v := range src {
		dst[k] = v
	}
}

func defaultLivenessProbe() *coreV1.Probe {
	return &coreV1.Probe{
		Handler: coreV1.Handler{
			HTTPGet: &coreV1.HTTPGetAction{
				Path:   "/",
				Port:   intstr.FromInt(5000),
				Scheme: coreV1.URISchemeHTTP,
			},
		},
		InitialDelaySeconds: 15,
		TimeoutSeconds:      15,
	}
}

func defaultReadinessProbe() *coreV1.Probe {
	return &coreV1.Probe{
		Handler: coreV1.Handler{
			HTTPGet: &coreV1.HTTPGetAction{
				Path:   "/",
				Port:   intstr.FromInt(5000),
				Scheme: coreV1.URISchemeHTTP,
			},
		},
		InitialDelaySeconds: 5,
		TimeoutSeconds:      3,
	}
}
