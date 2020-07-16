package acdh.oeaw.ac.at.dylenegonetworkserice;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.xml.transform.Source;
import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Corpus {
    @NonNull private String id;
    @NonNull private String name;
    private List<Source> sources;
}
