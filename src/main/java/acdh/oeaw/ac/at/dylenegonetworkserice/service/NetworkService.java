package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.EgoNetworkNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NetworkService implements EgoNetworkService {
    final
    EgoNetworkRepository egoNetworkRepository;


    public NetworkService(EgoNetworkRepository egoNetworkRepository) throws IOException {
        this.egoNetworkRepository = egoNetworkRepository;
    }

    @Override
    public EgoNetwork getNetworkById(String id) {
        return egoNetworkRepository.findById(id)
                .orElseThrow(()-> {
                    throw new EgoNetworkNotFoundException("No ego network found with the id", id);
                });
    }

    @Override
    public List<EgoNetwork> getNetworkBySource(String source) {
        var result = egoNetworkRepository.findBySource(source);

        if (result.size()==0) {
            return ImmutableList.of();
        }
        return result;
    }

    @Override
    public List<EgoNetwork> getNetworkByTargetWord(String targetWord) {
        return egoNetworkRepository.findByText(targetWord);
    }

    @Override
    public List<TargetWord> getTargetWordsOfCorpusAndSource(String corpus, String source) {
        var networks = egoNetworkRepository.findByCorpusAndSource(corpus, source);

        var targetWords = networks.stream()
                .collect(Collectors.groupingBy(EgoNetwork::getText))
                .entrySet()
                .stream()
                .map(entry -> {
                    return TargetWord.of(entry.getKey(), null, entry.getValue());
                })
                .collect(Collectors.toUnmodifiableList());
        return targetWords;
    }

    @Override
    public List<TargetWord> getAllAvailableTargetWords() {
        return null;
    }

}
