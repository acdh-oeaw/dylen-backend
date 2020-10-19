package acdh.oeaw.ac.at.dylenegonetworkserice.persistence;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Connection;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Node;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class EgoNetworkEntity {
    @NonNull
    @Id
    String technical_id;
    @NonNull
    private String id;
    @NonNull private String text;
    private int year;
    @NonNull private String corpusId;
    @NonNull private String sourceId;
    private int absFreq;
    private float relFreq;
    private float threshold;
    @NonNull private List<Node> nodes;
    @NonNull private List<Connection> connections;

    public EgoNetworkEntity() {

    }



}
