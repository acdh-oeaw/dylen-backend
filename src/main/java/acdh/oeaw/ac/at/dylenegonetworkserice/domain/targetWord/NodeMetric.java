package acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NodeMetric {
    Double degreeCentrality;
    Double closenessCentrality;
    Double betweennessCentrality;
    Double eigenvectorCentrality;
    Double pagerank;
    Double loadCentrality;
    Double harmonicCentrality;
    Double clusteringCoefficient;

    private NodeMetric(Double degreeCentrality, Double closenessCentrality, Double betweennessCentrality,
                       Double eigenvectorCentrality,
                       Double pagerank, Double loadCentrality, Double harmonicCentrality, Double clusteringCoefficient) {
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
    public static NodeMetric of(@JsonProperty("degreeCentrality") Double degreeCentrality,
                                @JsonProperty("closenessCentrality") Double closenessCentrality,
                                @JsonProperty("betweennessCentrality") Double betweennessCentrality,
                                @JsonProperty("eigenvectorCentrality") Double eigenvectorCentrality,
                                @JsonProperty("pagerank") Double pagerank,
                                @JsonProperty("loadCentrality") Double loadCentrality,
                                @JsonProperty("harmonicCentrality") Double harmonicCentrality,
                                @JsonProperty("clusteringCoefficient") Double clusteringCoefficient
    ) {
        return new NodeMetric(degreeCentrality, closenessCentrality, betweennessCentrality, eigenvectorCentrality,
                pagerank, loadCentrality, harmonicCentrality, clusteringCoefficient);
    }
}
