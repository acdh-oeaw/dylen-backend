package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class CorpusService {

    public static final String AMC = "AMC";
    public static final String PARLAT = "Parlat";
    final EgoNetworkRepository egoNetworkRepository;

    public CorpusService(EgoNetworkRepository egoNetworkRepository) {
        this.egoNetworkRepository = egoNetworkRepository;
    }

    @Cacheable(value = "corpora", cacheManager = "cacheMgr", key = "#root.method.name")
    public List<Corpus> getAllCorpora() {
        log.info("LOADING All Corpora...");
        var amc = this.egoNetworkRepository.findEgoNetworksByCorpus(AMC);
        var parlat = this.egoNetworkRepository.findEgoNetworksByCorpus(PARLAT);

        var result  = Stream.of(amc,parlat)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());

        var amc_sources = amc.stream()
                .collect(Collectors.groupingBy(EgoNetwork::getSource));
        var amc_sources_list = amc_sources.entrySet().stream()
                .map(entry -> {
                    return Source.of(entry.getKey(), entry.getValue());
                })
                .collect(Collectors.toUnmodifiableList());
        var amc_corpus = Corpus.of(UUID.randomUUID().toString(), "AMC", amc_sources_list);
        log.info("FINISHED LOADING All Corpora");

        return ImmutableList.of(amc_corpus);
    }

}
