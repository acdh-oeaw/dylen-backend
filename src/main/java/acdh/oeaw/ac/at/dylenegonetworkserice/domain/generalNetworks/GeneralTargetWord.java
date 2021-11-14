package acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.NetworkMetric;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.NodeMetric;
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
public class GeneralTargetWord {
    @Id
    private String id;
    @NonNull
    private String type;
    @NonNull
    private String entity;
    @NonNull private GeneralNetwork networks;

    private GeneralTargetWord(String id, String type, String entity, GeneralNetwork networks) {
        this.id = id;
        this.type = type;
        this.entity = entity;
        this.networks = networks;
    }

    @JsonCreator
    public static GeneralTargetWord of(@JsonProperty("_id") String id,
                                       @JsonProperty("type") String targetWord,
                                       @JsonProperty("entity") String entity,
                                       @JsonProperty("networks") GeneralNetwork networks
    ) {
        return new GeneralTargetWord(id, targetWord, entity, networks);
    }

    public static GeneralTargetWord of(@JsonProperty("type") String type,
                                       @JsonProperty("entity") String entity,
                                       @JsonProperty("networks") GeneralNetwork networks
    ) {
        return new GeneralTargetWord(null, type, entity, networks);
    }
}
