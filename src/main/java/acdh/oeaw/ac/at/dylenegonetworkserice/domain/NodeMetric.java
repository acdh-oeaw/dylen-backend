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
    public static NodeMetric of(@JsonProperty("degreeCentrality") double degreeCentrality,
                                @JsonProperty("closenessCentrality") double closenessCentrality,
                                @JsonProperty("betweennessCentrality") double betweennessCentrality,
                                @JsonProperty("eigenvectorCentrality") double eigenvectorCentrality,
                                @JsonProperty("pagerank") double pagerank,
                                @JsonProperty("loadCentrality") double loadCentrality,
                                @JsonProperty("harmonicCentrality") double harmonicCentrality,
                                @JsonProperty("clusteringCoefficient") double clusteringCoefficient
    ) {
        return new NodeMetric(degreeCentrality, closenessCentrality, betweennessCentrality, eigenvectorCentrality,
                pagerank, loadCentrality, harmonicCentrality, clusteringCoefficient);
    }
}
