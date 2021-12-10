package acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Node {
    @NonNull
    @Field("id")
    private String id;
    @Field("cluster_id")
    private int clusterId;
    @NonNull
    private String text;
    private String pos;
    private float similarity;
    private NodeMetric metrics;
    private int absoluteFrequency;
    private double normalisedFrequency;

    private Node(String id, int clusterId, String text, String pos, float similarity, NodeMetric metrics, int absoluteFrequency, double normalisedFrequency) {
        this.id = id;
        this.clusterId = clusterId;
        this.text = text;
        this.pos = pos;
        this.similarity = similarity;
        this.metrics = metrics;
        this.absoluteFrequency = absoluteFrequency;
        this.normalisedFrequency = normalisedFrequency;
    }


    @JsonCreator
    public static Node of(@JsonProperty("id") String id,
                          @JsonProperty("cluster_id") int clusterId,
                          @JsonProperty("text") String text,
                          @JsonProperty("pos") String pos,
                          @JsonProperty("similarity") float similarity,
                          @JsonProperty("metrics") NodeMetric metrics,
                          @JsonProperty("absoluteFrequency") int absoluteFrequency,
                          @JsonProperty("normalisedFrequency") double normalisedFrequency) {
        return new Node(id, clusterId, text, pos, similarity, metrics, absoluteFrequency, normalisedFrequency);
    }
}
