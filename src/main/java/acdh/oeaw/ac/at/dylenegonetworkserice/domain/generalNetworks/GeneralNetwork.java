package acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.Edge;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.NetworkMetric;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class GeneralNetwork {
    private String year;
    @NonNull private List<GeneralNetworkNode> nodes;
    @NonNull private List<Edge> edges;
    private NetworkMetric metrics;

    private GeneralNetwork(String year,
                           List<GeneralNetworkNode> nodes,
                           List<Edge> edges,
                           NetworkMetric metrics) {
        this.year = year;
        this.nodes = nodes;
        this.edges = edges;
        this.metrics = metrics;
    }

    @JsonCreator
    public static GeneralNetwork of(
                                @JsonProperty("year") String year,
                                @JsonProperty("nodes") List<GeneralNetworkNode> nodes,
                                @JsonProperty("edges") List<Edge> edges,
                                @JsonProperty("metrics") NetworkMetric metrics
    ) {
        return new GeneralNetwork(year, nodes, edges, metrics);
    }

    public static GeneralNetwork fromJson(String json) {
        return null;
    }

}
