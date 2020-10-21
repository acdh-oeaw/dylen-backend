package acdh.oeaw.ac.at.dylenegonetworkserice;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

//@EnableMongoRepositories(basePackages= "acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository")
class TestConfiguration /*extends AbstractMongoClientConfiguration */{

    /*@Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder
                .applyToClusterSettings(settings  -> {
                    settings.hosts(singletonList(new ServerAddress("127.0.0.1", 20000)));
                });
    }*/
}