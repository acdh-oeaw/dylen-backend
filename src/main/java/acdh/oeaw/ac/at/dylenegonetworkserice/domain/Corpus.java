package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Corpus {
    @Id
    @NonNull private String id;
    @Indexed
    @NonNull private String name;
    @NonNull private List<Source> sources;
}
