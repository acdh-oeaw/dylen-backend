package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class Node {
    @Id
    @NonNull private String id;
    @Indexed
    private int clusterId;
    @NonNull private String text;
    @NonNull private String pos;
    private float similarity;
    private int absFreq;
    private float relFreq;

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
