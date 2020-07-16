package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Corpus {
    @NonNull private String id;
    @NonNull private String name;
    @NonNull private List<Source> sources;
}
