package acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.Node;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.NodeMetric;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GeneralNetworkNode {
    @NonNull
    @Field("id")
    private String id;
    @Field("cluster_id")
    private int clusterId;
    @NonNull
    private String text;
    private String pos;

    @Field("absolute_frequency")
    private Float absolute_frequency;

    @Field("normalized_frequency")
    private Float normalized_frequency;

    private GeneralNodeMetric metrics;

    public GeneralNetworkNode(@NonNull String id,
                              int clusterId,
                              @NonNull String text, String pos, Float absolute_frequency,
                              Float normalized_frequency,
                              GeneralNodeMetric metrics) {
        this.id = id;
        this.clusterId = clusterId;
        this.text = text;
        this.pos = pos;
        this.absolute_frequency = absolute_frequency;
        this.normalized_frequency = normalized_frequency;
        this.metrics = metrics;
    }

    @JsonCreator
    public static GeneralNetworkNode of(@JsonProperty("id") String id,
                                        @JsonProperty("cluster_id") int clusterId,
                                        @JsonProperty("text") String text,
                                        @JsonProperty("pos") String pos,
                                        @JsonProperty("absolute_frequency") Float absolute_frequency,
                                        @JsonProperty("normalized_frequency") Float normalized_frequency,
                                        @JsonProperty("metrics") GeneralNodeMetric metrics) {
        return new GeneralNetworkNode(id, clusterId, text, pos, absolute_frequency, normalized_frequency, metrics);
    }
}
