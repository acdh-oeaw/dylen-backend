package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Connection {
    @NonNull
    private String id;
    private String node1;
    private String node2;
    private float similarity;

    @JsonCreator
    public static Connection of (@JsonProperty("node1") String node1,
                                 @JsonProperty("node2") String node2,
                                 @JsonProperty("similarity") float similarity) {
        return new Connection(UUID.randomUUID().toString(), node1, node2, similarity);
    }
}
