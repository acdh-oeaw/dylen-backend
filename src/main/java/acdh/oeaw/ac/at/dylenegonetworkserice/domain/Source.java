package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.graph.Network;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Source {
    @NonNull private String id;
    @NonNull private String name;
    @NonNull private List<EgoNetwork> networks;
}

