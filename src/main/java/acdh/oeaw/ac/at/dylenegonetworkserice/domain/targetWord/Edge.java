package acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Edge {
    @Id
    private String id;
    @Indexed
    private String node1;
    @Indexed
    private String node2;
    private float similarity;

    @JsonCreator
    public static Edge of (@JsonProperty("node1") String node1,
                           @JsonProperty("node2") String node2,
                           @JsonProperty("similarity") float similarity) {
        return new Edge(UUID.randomUUID().toString(), node1, node2, similarity);
    }
}
