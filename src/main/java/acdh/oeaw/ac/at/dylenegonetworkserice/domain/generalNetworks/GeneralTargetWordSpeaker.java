package acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "generalNet")
public class GeneralTargetWordSpeaker {
    @Id
    private String id;
    @NonNull
    private String type;
    @NonNull
    private String entity_name;
    @NonNull private List<GeneralNetwork> networks;

    private GeneralTargetWordSpeaker(String id, String type, String entity_name, List<GeneralNetwork> networks) {
        this.id = id;
        this.type = type;
        this.entity_name = entity_name;
        this.networks = networks;
    }

    @JsonCreator
    public static GeneralTargetWordSpeaker of(@JsonProperty("_id") String id,
                                       @JsonProperty("type") String type,
                                       @JsonProperty("entity_name") String entity_name,
                                       @JsonProperty("networks") List<GeneralNetwork> networks
    ) {
        return new GeneralTargetWordSpeaker(id, type, entity_name, networks);
    }

    @JsonCreator
    public static GeneralTargetWordSpeaker of(@JsonProperty("type") String type,
                                       @JsonProperty("entity_name") String entity_name,
                                       @JsonProperty("networks") List<GeneralNetwork> networks
    ) {
        return new GeneralTargetWordSpeaker(null, type, entity_name, networks);
    }
}
