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
    @NonNull private List<EgoNetwork> networks;


    public static Source of(String name, List<EgoNetwork> networks) {
        var targetwordsMap = networks.stream()
                .collect(Collectors.groupingBy(EgoNetwork::getText));

        var targetWords = targetwordsMap.entrySet().stream()
                .map(entry -> {
                    return TargetWord.of(entry.getKey(), null, entry.getValue());
                })
                .collect(Collectors.toUnmodifiableList());
        return new Source(name, targetWords, networks);
    }

    private List<TargetWord> convertToTargetWords() {
        return null;
    }
}

