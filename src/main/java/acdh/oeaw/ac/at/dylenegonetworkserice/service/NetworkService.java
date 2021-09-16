package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.TargetWordNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.TargetWordsSliceDto;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NetworkService implements EgoNetworkServiceInterface {

    final TargetWordRepository targetWordRepository;

    final Logger logger = LoggerFactory.getLogger(this.getClass());


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
    @Cacheable(value = "targetwords", cacheManager = "cacheMgr")
    public TargetWordsSliceDto getTargetWordsOfCorpusAndSource(String corpus, String source, Pageable pageRequest) {
        logger.info("Loading Targetwords of Corpus: " +  corpus + " and Source: " +  source);
        var targetWords = targetWordRepository.findByCorpusAndSource(corpus, source, pageRequest);
        return TargetWordsSliceDto.fromSlice(targetWords);
    }

    @NotNull
    private Predicate<TargetWord> checkCorpusAndSource(String corpus, String source) {
        return targetWord -> targetWord.getCorpus().equals(corpus) && targetWord.getSource().equals(source);
    }
}
