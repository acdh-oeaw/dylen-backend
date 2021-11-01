package acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord;

import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord.TargetWordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
