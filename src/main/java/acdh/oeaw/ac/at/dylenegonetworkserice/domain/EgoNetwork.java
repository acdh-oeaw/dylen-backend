package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.graph.Network;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class EgoNetwork {
    @NonNull private String id;
    @NonNull private String text;
    private int year;
    @NonNull private String corpusId;
    @NonNull private String sourceId;
    private int absFreq;
    private float relFreq;
    private float threshold;
    @NonNull private List<Node> nodes;
    @NonNull private List<Connection> connections;


    @JsonCreator
    public static EgoNetwork of(@JsonProperty("text") String text,
                                @JsonProperty("year") int year,
                                @JsonProperty("corpus") String corpus,
                                @JsonProperty("source") String source,
                                @JsonProperty("abs_freq") int absFreq,
                                @JsonProperty("rel_freq") float relFreq,
                                @JsonProperty("threshold") float threshold,
                                @JsonProperty("nodes") List<Node> nodes,
                                @JsonProperty("connections") List<Connection> connections
                                ) {
        var id = UUID.randomUUID();
        return new EgoNetwork(id.toString(), text, year, corpus, source, absFreq, relFreq, threshold, nodes, connections);
    }

    public static EgoNetwork fromJson(String json) {
        return null;
    }
}
