package acdh.oeaw.ac.at.dylenegonetworkservice.infrastructure.cache;

import acdh.oeaw.ac.at.dylenegonetworkservice.service.CorpusServiceInterface;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;


@Service
public class CacheLoader implements ApplicationListener<ApplicationReadyEvent> {

    final
    CorpusServiceInterface corpusService;

    public CacheLoader(CorpusServiceInterface corpusService) {
        this.corpusService = corpusService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        corpusService.getAllCorpora();
    }
}
