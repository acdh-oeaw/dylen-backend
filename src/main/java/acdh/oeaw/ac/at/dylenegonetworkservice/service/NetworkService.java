package acdh.oeaw.ac.at.dylenegonetworkservice.service;

import acdh.oeaw.ac.at.dylenegonetworkservice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkservice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkservice.exceptions.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkservice.persistence.repository.TargetWordRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NetworkService implements EgoNetworkServiceInterface {
    final
    TargetWordRepository targetWordRepository;


    public NetworkService(TargetWordRepository targetWordRepository) {
        this.targetWordRepository = targetWordRepository;
    }

    @Override
    public TargetWord getTargetWordById(String id) {
        return targetWordRepository.findById(id)
                .orElseThrow(()-> {
                    throw new TargetWordNotFoundException("No ego network found with the id", id);
                });
    }


    @Override
    public List<EgoNetwork> getNetworkByTargetWord(String targetWord) {
        return null;
    }

    @Override
    public List<TargetWord> getTargetWordsOfCorpusAndSource(String corpus, String source) {
        var targetWords = targetWordRepository.findByCorpusAndSource(corpus, source);
        var filtered = targetWords.stream()
                .filter(checkCorpusAndSource(corpus, source))
                .collect(Collectors.toUnmodifiableList());

        return filtered;
    }

    @NotNull
    private Predicate<TargetWord> checkCorpusAndSource(String corpus, String source) {
        return targetWord -> targetWord.getCorpus().equals(corpus) && targetWord.getSource().equals(source);
    }
}
