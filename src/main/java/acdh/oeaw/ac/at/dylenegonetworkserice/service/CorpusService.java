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

    final TargetWordRepository targetWordRepository;

    public CorpusService(TargetWordRepository targetWordRepository) {
        this.targetWordRepository = targetWordRepository;
    }

    @Cacheable(value = "corpora", cacheManager = "cacheMgr", key = "#root.method.name")
    public List<String> getAllCorpora() {
        return targetWordRepository.findAvailableCorpora();
    }

}
