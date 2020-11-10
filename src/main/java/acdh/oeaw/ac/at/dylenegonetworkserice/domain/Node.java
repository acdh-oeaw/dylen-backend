package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

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
    @Field("abs_freq")
    private int absFreq;
    @Field("rel_freq")
    private float relFreq;


    private Node(String id, int clusterId, String text, String pos, float similarity, int absFreq, float relFreq) {
        this.id = id;
        this.clusterId = clusterId;
        this.text = text;
        this.pos = pos;
        this.similarity = similarity;
        this.absFreq = absFreq;
        this.relFreq = relFreq;

    }


    @JsonCreator
    public static Node of(@JsonProperty("id") String id,
                          @JsonProperty("cluster_id") int clusterId,
                          @JsonProperty("text") String text,
                          @JsonProperty("pos") String pos,
                          @JsonProperty("similarity") float similarity,
                          @JsonProperty("abs_freq") int absFreq,
                          @JsonProperty("rel_freq") float relFreq) {
        return new Node(id, clusterId, text, pos, similarity, absFreq, relFreq);
    }
}
