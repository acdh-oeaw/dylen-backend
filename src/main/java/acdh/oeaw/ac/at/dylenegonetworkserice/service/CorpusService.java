package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class CorpusService implements CorpusServiceInterface{

    private static final String AMC = "AMC";
    private static final String PARLAT = "Parlat";
    final TargetWordRepository targetWordRepository;

    public CorpusService(TargetWordRepository targetWordRepository) {
        this.targetWordRepository = targetWordRepository;
    }

    @Cacheable(value = "corpora", cacheManager = "cacheMgr", key = "#root.method.name")
    public List<Corpus> getAllCorpora() {
        log.info("LOADING All Corpora...");
        
        var amc = this.targetWordRepository.findByCorpus(AMC);
        var parlat = this.targetWordRepository.findByCorpus(PARLAT);

        var result  = Stream.of(amc,parlat)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());

        var sourceToTargetWordMap = amc.stream()
                .collect(Collectors.groupingBy(TargetWord::getSource));
        var sources = sourceToTargetWordMap.entrySet().stream()
                .map(entry -> Source.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toUnmodifiableList());

        var amc_corpus = Corpus.of(UUID.randomUUID().toString(), "AMC", sources);

        log.info("FINISHED LOADING All Corpora");

        return ImmutableList.of(amc_corpus);
    }

}
