package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.graph.Network;
import lombok.*;

import java.util.List;

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
}
