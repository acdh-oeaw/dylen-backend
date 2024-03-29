package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.cache;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CacheRunner implements Runnable{

    final CorpusServiceInterface corpusService;

    final QueryServiceInterface queryService;

    final EgoNetworkServiceInterface networkService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    public CacheRunner(CorpusServiceInterface corpusService, QueryServiceInterface queryService, EgoNetworkServiceInterface networkService) {
        this.corpusService = corpusService;
        this.queryService = queryService;
        this.networkService = networkService;
    }

    @Override
    public void run() {
        logger.info("Loading cache");
        var corpora = corpusService.getAllCorpora();
        logger.info("Corpora loaded...");
        corpora.forEach((corpus) -> queryService.getSourcesByCorpus(corpus)
                .forEach((source) -> networkService.getTargetWordsOfCorpusAndSource(corpus, source, PageRequest.of(0, 10))));
        logger.info("Sources and Targetwords loaded..");
    }
}
