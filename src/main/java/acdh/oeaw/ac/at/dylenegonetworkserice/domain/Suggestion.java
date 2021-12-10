package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(indexName = "dylen_text")
public class Suggestion {
    @Id
    private String id;

    private String text;

    private String corpus;

    private String source;

    private String pos;

    private Suggestion(String id, String corpus, String source, String pos, String text) {
        this.id = id;
        this.corpus = corpus;
        this.source = source;
        this.pos = pos;
        this.text = text;
    }

    public static Suggestion of(String id, String corpus, String source, String pos, String text) {
        return new Suggestion(id, corpus, source, pos, text);
    }
}
