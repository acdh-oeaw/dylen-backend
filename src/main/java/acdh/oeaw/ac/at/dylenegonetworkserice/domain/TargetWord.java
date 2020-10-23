package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class TargetWord {
    @Id
    @NonNull
    private String id;
    @NonNull private String text;
    @Indexed
    @NonNull private String corpus;
    @Indexed
    @NonNull private String source;
    @NonNull private List<EgoNetwork> networks;

    @JsonCreator
    public static TargetWord of(@JsonProperty("text") String targetWord,
                                @JsonProperty("corpus") String corpus,
                                @JsonProperty("source") String source,
                                @JsonProperty("networks") List<EgoNetwork> networks
    ) {
        var id = UUID.randomUUID();
        return new TargetWord(id.toString(), targetWord, corpus, source, networks);
    }

    public static TargetWord fromJson(String json) {
        return null;
    }

}
