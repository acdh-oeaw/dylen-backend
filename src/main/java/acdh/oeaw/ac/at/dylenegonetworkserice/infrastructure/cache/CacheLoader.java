package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.cache;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks.GeneralNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.CorpusServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord.QueryServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class CacheLoader {

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

    @Bean
    public CommandLineRunner schedulingRunner(@Qualifier("taskExecutor") TaskExecutor executor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                executor.execute(new CacheRunner(corpusService, queryService, networkService));
            }
        };
    }
}
