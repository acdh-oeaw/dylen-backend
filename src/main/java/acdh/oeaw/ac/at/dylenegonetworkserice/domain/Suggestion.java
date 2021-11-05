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

    private Suggestion(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public static Suggestion of(String id, String text) {
        return new Suggestion(id, text);
    }
}
