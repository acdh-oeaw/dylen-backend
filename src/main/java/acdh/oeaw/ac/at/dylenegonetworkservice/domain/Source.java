package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Source {
    @Indexed
    @NonNull private String name;
    @NonNull private List<TargetWord> targetWords;

    public static Source of(String name, List<TargetWord> targetWords) {
        var targetwordsOfSource = targetWords.stream()
                .filter(targetWord -> targetWord.getSource().equals(name))
                .collect(Collectors.toUnmodifiableList());

        return new Source(name, targetwordsOfSource);
    }

}

