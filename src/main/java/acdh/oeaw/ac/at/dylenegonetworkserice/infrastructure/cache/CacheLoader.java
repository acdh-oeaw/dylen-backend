package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.cache;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;


@Service
public class CacheLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    CorpusService corpusService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        corpusService.getAllCorpora();
    }
}
