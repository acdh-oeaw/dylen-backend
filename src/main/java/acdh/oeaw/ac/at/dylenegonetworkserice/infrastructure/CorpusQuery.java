package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;


import acdh.oeaw.ac.at.dylenegonetworkserice.SourceService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Source;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CorpusQuery implements GraphQLQueryResolver {

    public static final String CORPUS_ID = "corpus-1";
    public static final String CORPUS_NAME = "AMC";
    public final List<Corpus> corpora;

    public CorpusQuery() throws IOException {
        var mapper = new ObjectMapper();

        var network1 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Asyl_6.json");
        var network2 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Computer_6.json");
        var network3 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Flüchtling_6.json");
        var network4 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Abschiebung_6.json");
        var network5 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Küstenwache_6.json");
        var network6 = readEgoNetworkFromJson(mapper, "samples/amc/2015_Klick_6.json");
        var network7 = readEgoNetworkFromJson(mapper, "samples/amc/2015_Regierung_6.json");

        var standard = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.STANDARD.getName(),
                ImmutableList.of(network1, network2, network3));
        var krone = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.KRONE.getName(),
                ImmutableList.of(network6, network7));
        var heute = Source.of(UUID.randomUUID().toString(), SourceService.SourceEnum.HEUTE.getName(),
                ImmutableList.of(network4, network5));

        this.corpora = ImmutableList.of(Corpus.of(CORPUS_ID, CORPUS_NAME, ImmutableList.of(standard)));
    }

    private EgoNetwork readEgoNetworkFromJson(ObjectMapper mapper, String resourcePath) throws IOException {
        var resource = new ClassPathResource(resourcePath).getInputStream();
        EgoNetwork network1;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            var jsonStr = reader.lines()
                    .collect(Collectors.joining("\n"));
            network1 = mapper.readValue(jsonStr, EgoNetwork.class);
        }
        return network1;
    }

    public List<Corpus> getAllAvailableCorpora(){
        return this.corpora;

    }
}
