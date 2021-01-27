package acdh.oeaw.ac.at.dylenegonetworkservice.domain;

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
    @NonNull private List<Connection> connections;


    private EgoNetwork(String id,
                       int year,
                       List<Node> nodes,
                       List<Connection> connections) {
        this.id = id;
        this.year = year;
        this.nodes = nodes;
        this.connections = connections;
    }

    public static EgoNetwork of(
                                @JsonProperty("year") int year,
                                @JsonProperty("nodes") List<Node> nodes,
                                @JsonProperty("connections") List<Connection> connections
    ) {
        return new EgoNetwork(null, year, nodes, connections);
    }

    @JsonCreator
    public static EgoNetwork of(@JsonProperty("_id") String id,
                                @JsonProperty("year") int year,
                                @JsonProperty("nodes") List<Node> nodes,
                                @JsonProperty("connections") List<Connection> connections
                                ) {
        var idNotNull = StringUtils.isEmpty(id)?UUID.randomUUID().toString():id;
        return new EgoNetwork(idNotNull, year, nodes, connections);
    }

    public static EgoNetwork fromJson(String json) {
        return null;
    }

}
