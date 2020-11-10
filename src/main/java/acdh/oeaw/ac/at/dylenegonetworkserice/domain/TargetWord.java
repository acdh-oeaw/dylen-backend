package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class TargetWord {
    @Field("id")
    private String id;
    @NonNull private String text;
    private String pos;
    @NonNull private List<EgoNetwork> networks;

    @JsonCreator
    public static TargetWord of(@JsonProperty("text") String targetWord,
                                @JsonProperty("pos") String pos,
                                @JsonProperty("networks") List<EgoNetwork> networks
    ) {
        return new TargetWord(null, targetWord, pos,  networks);
    }

    public static TargetWord fromJson(String json) {
        return null;
    }
}
