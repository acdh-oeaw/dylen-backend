# GitLab's Auto-deploy Helm Chart

## Deprecation Notice

GitLab is moving all development for `auto-deploy-app` into [`auto-deploy-image`](https://gitlab.com/gitlab-org/cluster-integration/auto-deploy-image). 
Going forward, the `auto-deploy-app` Helm chart will be bundled with `auto-deploy-image`
and will no longer released as a stand-alone Helm chart. Existing releases of `auto-deploy-app`
will remain in [GitLab's chart registry](http://charts.gitlab.io/).

If you have any questions, please ask in <https://gitlab.com/gitlab-org/charts/auto-deploy-app/-/issues/70>.

## Requirements

- Helm `2.9.0` and above is required in order support `"helm.sh/hook-delete-policy": before-hook-creation` for migrations

## Configuration

| Parameter                     | Description | Default                            |
| ---                           | ---         | ---                                |
| replicaCount                  |             | `1`                                |
| strategyType                  | Pod deployment [strategy](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/#strategy) | `nil` |
| enableSelector                | If `true`, enables selector field for the deployment. Only applicable for `extensions/v1beta1`, as selector field will always be included for `apps/v1` | `nil` |
| deploymentApiVersion          | Sets `apiVersion` field for the deployment. Can be set to either `extensions/v1beta1` or `apps/v1`. | `extensions/v1beta1` |
| image.repository              |             | `gitlab.example.com/group/project` |
| image.tag                     |             | `stable`                           |
| image.pullPolicy              |             | `Always`                           |
| image.secrets                 |             | `[name: gitlab-registry]`          |
| podAnnotations                | Pod annotations | `{}`                           |
| application.track             |             | `stable`                           |
| application.tier              |             | `web`                              |
| application.migrateCommand    | If present, this variable will run as a shell command within an application Container as a Helm pre-upgrade Hook. Intended to run migration commands. | `nil` |
| application.initializeCommand | If present, this variable will run as shell command within an application Container as a Helm post-install Hook. Intended to run database initialization commands. When set, the Deployment resource will be skipped.| `nil` |
| application.secretName        | Pass in the name of a Secret which the deployment will [load all key-value pairs from the Secret as environment variables](https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/#configure-all-key-value-pairs-in-a-configmap-as-container-environment-variables) in the application container. | `nil` |
| application.secretChecksum    | Pass in the checksum of the secrets referenced by `application.secretName`. | `nil` |
| hpa.enabled                   | If true, enables horizontal pod autoscaler. A resource request is also required to be set, such as `resources.requests.cpu: 200m`.| `false` |
| hpa.minReplicas               |             | `1`                                |
| hpa.maxReplicas               |             | `5`                                |
| hpa.targetCPUUtilizationPercentage | Percentage threshold when HPA begins scaling out pods | `80` |
| gitlab.app                    | GitLab project slug. | `nil` |
| gitlab.env                    | GitLab environment slug. | `nil` |
| gitlab.envName                | GitLab environment name. | `nil` |
| gitlab.envURL                 | GitLab environment URL.  | `nil` |
| service.enabled               |             | `true`                             |
| service.annotations           | Service annotations | `{}`                       |
| service.name                  |             | `web`                              |
| service.type                  |             | `ClusterIP`                        |
| service.url                   |             | `http://my.host.com/`              |
| service.additionalHosts       | If present, this list will add additional hostnames to the server configuration. | `nil` |
| service.commonName            | If present, this will define the ssl certificate common name to be used by CertManager. `service.url` and `service.additionalHosts` will be added as Subject Alternative Names (SANs) | `nil` |
| service.externalPort          |             | `5000`                             |
| service.internalPort          |             | `5000`                             |
| ingress.enabled               | If true, enables ingress | `true`                |
| ingress.tls.enabled           | If true, enables SSL | `true`                    |
| ingress.tls.secretName        | Name of the secret used to terminate SSL traffic | `""` |
| ingress.modSecurity.enabled | Enable custom configuration for modsecurity, defaulting to [the Core Rule Set](https://coreruleset.org) | `false` |
| ingress.modSecurity.secRuleEngine | Configuration for [ModSecurity's rule engine](https://github.com/SpiderLabs/ModSecurity/wiki/Reference-Manual-(v2.x)#SecRuleEngine) | `DetectionOnly` |
| ingress.modSecurity.secRules | Configuration for custom [ModSecurity's rules](https://github.com/SpiderLabs/ModSecurity/wiki/Reference-Manual-(v2.x)#secrule) | `nil` |
| ingress.annotations           | Ingress annotations | `{kubernetes.io/tls-acme: "true", kubernetes.io/ingress.class: "nginx"}` |
| livenessProbe.path            | Path to access on the HTTP server on periodic probe of container liveness. | `/`                                |
| livenessProbe.scheme          | Scheme to access the HTTP server (HTTP or HTTPS). | `HTTP`                                |
| livenessProbe.initialDelaySeconds | # of seconds after the container has started before liveness probes are initiated. | `15`                               |
| livenessProbe.timeoutSeconds  | # of seconds after which the liveness probe times out. | `15`                               |
| livenessProbe.probeType       | Type of [liveness probe](https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes) to use. | `httpGet`
| livenessProbe.command         | Commands for use with probe type 'exec'. | `{}`
| readinessProbe.path           | Path to access on the HTTP server on periodic probe of container readiness. | `/`                                |
| readinessProbe.scheme         | Scheme to access the HTTP server (HTTP or HTTPS). | `HTTP`                                |
| readinessProbe.initialDelaySeconds | # of seconds after the container has started before readiness probes are initiated. | `5`                                |
| readinessProbe.timeoutSeconds | # of seconds after which the readiness probe times out. | `3`                                |
| readinessProbe.probeType     | Type of [readiness probe](https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes) to use. | `httpGet`
| readinessProbe.command       | Commands for use with probe type 'exec'. | `{}`
| postgresql.enabled            |             | `true`                             |
| postgresql.managed            | If true, this will provision a managed Postgres instance via crossplane.            | `false`                             |
| postgresql.managedClassSelector            | This will allow provisioning a Postgres instance based on label selectors via Crossplane, eg: `managedClassSelector.matchLabels.stack: gitlab`. The `postgresql.managed` value should be true as well for this to be honoured. [Crossplane Configuration](https://docs.gitlab.com/ee/user/clusters/applications.html#crossplane)            | `{}`                             |
| podDisruptionBudget.enabled   |             | `false`                            |
| podDisruptionBudget.maxUnavailable |             | `1`                            |
| podDisruptionBudget.minAvailable | If present, this variable will configure minAvailable in the PodDisruptionBudget. :warning: if you have `replicaCount: 1` and `podDisruptionBudget.minAvailable: 1` `kubectl drain` will be blocked.              | `nil`                            |
| prometheus.metrics            | Annotates the service for prometheus auto-discovery. Also denies access to the `/metrics` endpoint from external addresses with Ingress. | `false` |
| networkPolicy.enabled         | Enable container network policy | `false` |
| networkPolicy.spec            | [Network policy](https://kubernetes.io/docs/concepts/services-networking/network-policies/) definition | `{ podSelector: { matchLabels: {} }, ingress: [{ from: [{ podSelector: { matchLabels: {} } }, { namespaceSelector: { matchLabels: { app.gitlab.com/managed_by: gitlab } } }] }] }` |

## PostgreSQL

This chart depends on version 0.7.1 of the `stable/postgresql` chart.
For reference the source code for this specific version can be found at https://github.com/helm/charts/tree/b90ad657e1a226eb52c3eb6a2a95ba3d6d494f58/stable/postgresql