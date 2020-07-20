package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import lombok.*;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Node {
    @NonNull private String id;
    private int clusterId;
    @NonNull private String text;
    @NonNull private String pos;
    private float similarity;
    private int absFreq;
    private float relFreq;
}
