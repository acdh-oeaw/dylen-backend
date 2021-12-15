package acdh.oeaw.ac.at.dylenegonetworkserice;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.*;

import java.net.UnknownHostException;

@Configuration
@ComponentScan({"acdh.oeaw.ac.at.dylenegonetworkserice.service.targetWord",
                "acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.targetWord",
                "acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks",
                "acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.generalNetworks"})
public class AppConfig extends AbstractElasticsearchConfiguration {
    @Value("${elasticsearch.host}")
    private String EsHost="localhost";

    private int EsPort=9200;

    private String EsClusterName="dylen-elastic";

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }


    @Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchRestTemplate(elasticsearchClient()) {
        };
    }

}
