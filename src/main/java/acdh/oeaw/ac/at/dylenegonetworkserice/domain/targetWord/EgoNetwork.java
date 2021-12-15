package acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Edge;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.NetworkMetric;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Data
public class EgoNetwork {
    @Id
    @NonNull private String id;
    private int year;
    @NonNull private List<Node> nodes;
    @NonNull private List<Edge> edges;
    @NonNull NetworkMetric metrics;
    private int absoluteFrequency;
    private double normalisedFrequency;


    private EgoNetwork(String id,
                       int year,
                       List<Node> nodes,
                       List<Edge> edges,
                       NetworkMetric metrics,
                       int absoluteFrequency,
                       double normalisedFrequency) {
        this.id = id;
        this.year = year;
        this.nodes = nodes;
        this.edges = edges;
        this.metrics = metrics;
        this.absoluteFrequency = absoluteFrequency;
        this.normalisedFrequency = normalisedFrequency;
    }

    public static EgoNetwork of(
                                @JsonProperty("year") int year,
                                @JsonProperty("nodes") List<Node> nodes,
                                @JsonProperty("edges") List<Edge> edges,
                                @JsonProperty("metrics") NetworkMetric metrics,
                                @JsonProperty("absoluteFrequency") int absoluteFrequency,
                                @JsonProperty("normalisedFrequency") double normalisedFrequency
    ) {
        return new EgoNetwork(null, year, nodes, edges, metrics, absoluteFrequency, normalisedFrequency);
    }

    @JsonCreator
    public static EgoNetwork of(@JsonProperty("_id") String id,
                                @JsonProperty("year") int year,
                                @JsonProperty("nodes") List<Node> nodes,
                                @JsonProperty("edges") List<Edge> edges,
                                @JsonProperty("metrics") NetworkMetric metrics,
                                @JsonProperty("absoluteFrequency") int absoluteFrequency,
                                @JsonProperty("normalisedFrequency") double normalisedFrequency
                                ) {
        var idNotNull = StringUtils.isEmpty(id)?UUID.randomUUID().toString():id;
        return new EgoNetwork(idNotNull, year, nodes, edges, metrics, absoluteFrequency, normalisedFrequency);
    }

}
