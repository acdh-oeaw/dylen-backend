package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.cache;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks.GeneralNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.CorpusServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.QueryServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CacheLoader implements ApplicationListener<ApplicationReadyEvent> {

    final CorpusServiceInterface corpusService;

    final QueryServiceInterface queryService;

    final EgoNetworkServiceInterface networkService;

    final GeneralNetworkServiceInterface generalNetworkService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CacheLoader(CorpusServiceInterface corpusService,
                       QueryServiceInterface queryService,
                       EgoNetworkServiceInterface networkService,
                       GeneralNetworkServiceInterface generalNetworkService) {
        this.corpusService = corpusService;
        this.queryService = queryService;
        this.networkService = networkService;
        this.generalNetworkService = generalNetworkService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Loading cache");
        var corpora = corpusService.getAllCorpora();
        logger.info("Corpora loaded...");
        corpora.forEach((corpus) -> queryService.getSourcesByCorpus(corpus)
                .forEach((source) -> networkService.getTargetWordsOfCorpusAndSource(corpus, source, PageRequest.of(0, 20))));
        logger.info("Sources and Targetwords loaded..");
    }
}
