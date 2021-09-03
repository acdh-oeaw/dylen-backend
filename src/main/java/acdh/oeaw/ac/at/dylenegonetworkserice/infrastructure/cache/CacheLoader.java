package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.cache;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusService;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CacheLoader implements ApplicationListener<ApplicationReadyEvent> {

    final CorpusServiceInterface corpusService;

    final QueryServiceInterface queryService;

    public CacheLoader(CorpusServiceInterface corpusService, QueryServiceInterface queryService) {
        this.corpusService = corpusService;
        this.queryService = queryService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<String> corpora = corpusService.getAllCorpora();
        corpora.forEach(queryService::getSourcesByCorpus);
    }
}
