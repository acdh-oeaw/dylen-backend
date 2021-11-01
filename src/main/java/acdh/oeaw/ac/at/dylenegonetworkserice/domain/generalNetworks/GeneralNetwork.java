package acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.Edge;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class GeneralNetwork {
    private int year;
    @NonNull private List<GeneralNetworkNode> nodes;
    @NonNull private List<Edge> edges;

    private GeneralNetwork(int year,
                           List<GeneralNetworkNode> nodes,
                           List<Edge> edges) {
        this.year = year;
        this.nodes = nodes;
        this.edges = edges;
    }

    @JsonCreator
    public static GeneralNetwork of(
                                @JsonProperty("year") int year,
                                @JsonProperty("nodes") List<GeneralNetworkNode> nodes,
                                @JsonProperty("edges") List<Edge> edges
    ) {
        return new GeneralNetwork(year, nodes, edges);
    }

    public static GeneralNetwork fromJson(String json) {
        return null;
    }

}
