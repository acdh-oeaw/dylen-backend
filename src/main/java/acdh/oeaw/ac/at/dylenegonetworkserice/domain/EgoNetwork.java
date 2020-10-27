package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection="egoNetwork")
public class EgoNetwork {
    @Id
    @NonNull private String id;
    @NonNull private String text;
    private int year;
    @Indexed
    @NonNull private String corpus;
    @Indexed
    @NonNull private String source;
    @Field("abs_freq")
    private int absFreq;
    @Field("rel_freq")
    private float relFreq;
    private float threshold;
    @NonNull private List<Node> nodes;
    @NonNull private List<Connection> connections;


    private EgoNetwork(String id,
                       String text,
                       int year,
                       String corpus,
                       String source,
                       int absFreq,
                       float relFreq,
                       float threshold,
                       List<Node> nodes,
                       List<Connection> connections) {
        this.id = id;
        this.text = text;
        this.year = year;
        this.corpus = corpus;
        this.source = source;
        this.absFreq = absFreq;
        this.relFreq = relFreq;
        this.threshold = threshold;
        this.nodes = nodes;
        this.connections = connections;
    }

    public static EgoNetwork of(
                                @JsonProperty("text") String text,
                                @JsonProperty("year") int year,
                                @JsonProperty("corpus") String corpus,
                                @JsonProperty("source") String source,
                                @JsonProperty("abs_freq") int absFreq,
                                @JsonProperty("rel_freq") float relFreq,
                                @JsonProperty("threshold") float threshold,
                                @JsonProperty("nodes") List<Node> nodes,
                                @JsonProperty("connections") List<Connection> connections
    ) {
        return new EgoNetwork(null, text, year, corpus, source, absFreq, relFreq, threshold, nodes, connections);
    }

    @JsonCreator
    public static EgoNetwork of(@JsonProperty("_id") String id,
                                @JsonProperty("text") String text,
                                @JsonProperty("year") int year,
                                @JsonProperty("corpus") String corpus,
                                @JsonProperty("source") String source,
                                @JsonProperty("abs_freq") int absFreq,
                                @JsonProperty("rel_freq") float relFreq,
                                @JsonProperty("threshold") float threshold,
                                @JsonProperty("nodes") List<Node> nodes,
                                @JsonProperty("connections") List<Connection> connections
                                ) {
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        return new EgoNetwork(id, text, year, corpus, source, absFreq, relFreq, threshold, nodes, connections);
    }

    public static EgoNetwork fromJson(String json) {
        return null;
    }

}
