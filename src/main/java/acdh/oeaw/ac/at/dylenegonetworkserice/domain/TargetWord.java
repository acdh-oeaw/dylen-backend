package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "targetwords")
@CompoundIndex(def = "{'corpus': 1,'source':1, 'text': 'text'}")
public class TargetWord {
    @Id
    private String id;
    @NonNull
    private String text;
    private String pos;
    @Indexed
    @NonNull private String corpus;
    @Indexed
    @NonNull private String source;
    @NonNull private List<EgoNetwork> networks;
    @NonNull private TimeSeries timeSeries;

    private TargetWord(String id, String targetWord, String pos, String corpus, String source, List<EgoNetwork> networks, TimeSeries timeSeries) {
        this.id = id;
        this.text = targetWord;
        this.pos = pos;
        this.corpus = corpus;
        this.source = source;
        this.networks = networks;
        this.timeSeries = timeSeries;

    }

    @JsonCreator
    public static TargetWord of(@JsonProperty("_id") String id,
                                @JsonProperty("text") String targetWord,
                                @JsonProperty("pos") String pos,
                                @JsonProperty("corpus") String corpus,
                                @JsonProperty("source") String source,
                                @JsonProperty("networks") List<EgoNetwork> networks,
                                @JsonProperty("timeSeries") TimeSeries timeSeries) {
        return new TargetWord(id, targetWord, pos, corpus, source, networks, timeSeries);
    }

    public static TargetWord of(@JsonProperty("text") String targetWord,
                                @JsonProperty("pos") String pos,
                                @JsonProperty("corpus") String corpus,
                                @JsonProperty("source") String source,
                                @JsonProperty("networks") List<EgoNetwork> networks,
                                @JsonProperty("timeSeries") TimeSeries timeSeries) {
        return new TargetWord(null, targetWord, pos, corpus, source, networks, timeSeries);
    }

    public static TargetWord fromJson(String json) {
        return null;
    }
}
