package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;


import acdh.oeaw.ac.at.dylenegonetworkserice.SourceService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class CorpusQuery implements GraphQLQueryResolver {

    public static final String CORPUS_ID = "corpus-1";
    public static final String CORPUS_NAME = "AMC";
    public final List<Corpus> corpora;

    public CorpusQuery() throws IOException {
        var mapper = new ObjectMapper();
        var network1 = mapper.readValue(ResourceUtils.getFile("classpath:samples/amc/2014_Asyl_6.json"),
                EgoNetwork.class);
        var network2 = mapper.readValue(ResourceUtils.getFile("classpath:samples/amc/2014_Computer_6.json"),
                EgoNetwork.class);
        var network3 = mapper.readValue(ResourceUtils.getFile("classpath:samples/amc/2014_Flüchtling_6.json"),
                EgoNetwork.class);
        var network4 = mapper.readValue(ResourceUtils.getFile("classpath:samples/amc/2014_Abschiebung_6.json"),
                EgoNetwork.class);
        var network5 = mapper.readValue(ResourceUtils.getFile("classpath:samples/amc/2014_Küstenwache_6.json"),
                EgoNetwork.class);
        var network6 = mapper.readValue(ResourceUtils.getFile("classpath:samples/amc/2015_Klick_6.json"),
                EgoNetwork.class);
        var network7 = mapper.readValue(ResourceUtils.getFile("classpath:samples/amc/2015_Regierung_6.json"),
                EgoNetwork.class);
        var standard = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.STANDARD.getName(),
                ImmutableList.of(network1, network2, network3));
        var krone = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.KRONE.getName(),
                ImmutableList.of(network6, network7));
        var heute = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.HEUTE.getName(),
                ImmutableList.of(network4, network5));

        this.corpora = ImmutableList.of(Corpus.of(CORPUS_ID, CORPUS_NAME, ImmutableList.of(standard, krone, heute)));
    }

    public List<Corpus> getAllAvailableCorpora(){
        return this.corpora;

    }
}
