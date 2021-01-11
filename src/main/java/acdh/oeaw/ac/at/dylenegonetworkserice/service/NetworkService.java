package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("prod")
public class NetworkService implements EgoNetworkService {
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
                .filter(targetWord -> targetWord.getCorpus().equals(corpus) && targetWord.getSource().equals(source))
                .collect(Collectors.toUnmodifiableList());

        return filtered;
    }

    @Override
    public List<TargetWord> getAllAvailableTargetWords() {
        return null;
    }

}
