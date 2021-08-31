package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NodeMetric {
    double degreeCentrality;
    double closenessCentrality;
    double betweennessCentrality;
    double eigenvectorCentrality;
    double pagerank;
    double loadCentrality;
    double harmonicCentrality;
    double clusteringCoefficient;

    private NodeMetric(double degreeCentrality, double closenessCentrality, double betweennessCentrality,
                       double eigenvectorCentrality,
                       double pagerank, double loadCentrality, double harmonicCentrality, double clusteringCoefficient) {
        this.degreeCentrality = degreeCentrality;
        this.closenessCentrality = closenessCentrality;
        this.betweennessCentrality = betweennessCentrality;
        this.eigenvectorCentrality = eigenvectorCentrality;
        this.pagerank = pagerank;
        this.loadCentrality = loadCentrality;
        this.harmonicCentrality = harmonicCentrality;
        this.clusteringCoefficient = clusteringCoefficient;
    }

    @JsonCreator
    public static NodeMetric of(@JsonProperty("degree_centrality") double degreeCentrality,
                                @JsonProperty("closeness_centrality") double closenessCentrality,
                                @JsonProperty("betweenness_centrality") double betweennessCentrality,
                                @JsonProperty("eigenvector_centrality") double eigenvectorCentrality,
                                @JsonProperty("pagerank") double pagerank,
                                @JsonProperty("load_centrality") double loadCentrality,
                                @JsonProperty("harmonic_centrality") double harmonicCentrality,
                                @JsonProperty("clustering_coefficient") double clusteringCoefficient
    ) {
        return new NodeMetric(degreeCentrality, closenessCentrality, betweennessCentrality, eigenvectorCentrality,
                pagerank, loadCentrality, harmonicCentrality, clusteringCoefficient);
    }
}
