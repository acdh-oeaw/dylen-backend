package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.graph.Network;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Source {
    @Id
    @NonNull private String id;
    @Indexed
    @NonNull private String name;
    @NonNull private List<EgoNetwork> networks;

    public static Source of(String name, List<EgoNetwork> networks) {
        return new Source(UUID.randomUUID().toString(), name, networks);
    }
}

