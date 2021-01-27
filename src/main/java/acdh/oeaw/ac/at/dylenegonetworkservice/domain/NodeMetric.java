package acdh.oeaw.ac.at.dylenegonetworkservice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NodeMetric {
    double degreeCentrality;
    double closenessCentrality;
    double betweennessCentrality;
    double eigenvectorCentrality;

    private NodeMetric(double degreeCentrality, double closenessCentrality, double betweennessCentrality, double eigenvectorCentrality) {
        this.degreeCentrality = degreeCentrality;
        this.closenessCentrality = closenessCentrality;
        this.betweennessCentrality = betweennessCentrality;
        this.eigenvectorCentrality = eigenvectorCentrality;
    }

    @JsonCreator
    public static NodeMetric of(@JsonProperty("degree_centrality") double degreeCentrality,
                                @JsonProperty("closeness_centrality") double closenessCentrality,
                                @JsonProperty("betweenness_centrality") double betweennessCentrality,
                                @JsonProperty("eigenvector_centrality") double eigenvectorCentrality
    ) {
        return new NodeMetric(degreeCentrality, closenessCentrality, betweennessCentrality, eigenvectorCentrality);
    }
}
