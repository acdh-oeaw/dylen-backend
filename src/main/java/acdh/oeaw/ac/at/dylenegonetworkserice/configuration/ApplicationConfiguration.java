package acdh.oeaw.ac.at.dylenegonetworkserice.configuration;

import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;

@Configuration
@EnableMongoRepositories(basePackageClasses = EgoNetworkRepository.class)
class ApplicationConfiguration extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "dylen";
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return ImmutableList.of("acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository");
    }
}
