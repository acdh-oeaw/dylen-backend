package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import lombok.*;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Connection {
    @NonNull
    private String id;
    private Node node1;
    private Node node2;
    private float similarity;
}
