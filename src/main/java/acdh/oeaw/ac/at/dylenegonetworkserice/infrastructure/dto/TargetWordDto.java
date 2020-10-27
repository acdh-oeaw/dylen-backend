package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor(staticName = "of")
public class TargetWordDto {
    @Id
    @NonNull
    private String id;
    @NonNull private String text;
    @NonNull private String pos;
    @Indexed
    @NonNull private String corpus;
    @Indexed
    @NonNull private String source;

    public static TargetWord fromJson(String json) {
        return null;
    }
}
