package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.cache;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.CorpusServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.QueryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class CacheLoader {

    final CorpusServiceInterface corpusService;

    final QueryServiceInterface queryService;

    final EgoNetworkServiceInterface networkService;

    public CacheLoader(CorpusServiceInterface corpusService, QueryServiceInterface queryService,
                       EgoNetworkServiceInterface networkService) {
        this.corpusService = corpusService;
        this.queryService = queryService;
        this.networkService = networkService;
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
