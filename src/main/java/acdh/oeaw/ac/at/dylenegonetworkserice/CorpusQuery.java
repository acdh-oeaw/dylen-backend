package acdh.oeaw.ac.at.dylenegonetworkserice;


import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CorpusQuery implements GraphQLQueryResolver {

    public static final String CORPUS_ID = "corpus-1";
    public static final String CORPUS_NAME = "AMC";

    public List<Corpus> getAllAvailableCorpora(){
        var nodes = Arrays.asList(
                ImmutableMap.<String, Object>builder()
                        .put("id", "node-1")
                        .put("cluster_id", "cluster-1")
                        .put("text", "Flüchtling")
                        .put("pos", "Noun")
                        .put("similarity", 0.9)
                        .put("abs_freq", 200)
                        .put("rel_freq", 0.4)
                        .build(),
                ImmutableMap.<String, Object>builder()
                        .put("id", "node-2")
                        .put("cluster_id", "cluster-1")
                        .put("text", "Krieg")
                        .put("pos", "Noun")
                        .put("similarity", 0.5)
                        .put("abs_freq", 100)
                        .put("rel_freq", 0.2)
                        .build()
        );
        var connections = Arrays.asList(
                ImmutableMap.of(
                        "id", "connection-1",
                        "node1", "node-1",
                        "node2", "node-2",
                        "similarity", 0.8
                )
        );
        var networks = Arrays.asList(
                ImmutableMap.<String, Object>builder()
                        .put("id", "network-1")
                        .put("text", "Balkanroute")
                        .put("year", 2019)
                        .put("corpus", "corpus-1")
                        .put("source", "source-1")
                        .put("abs_freq", 198)
                        .put("rel_freq", 0.4)
                        .put("threshold", 0.6)
                        .put("nodes", nodes)
                        .put("connections", connections)
                        .build());
        var amc_sources = Arrays.asList(
                ImmutableMap.of("id", "source-1",
                        "name", "FALTER",
                        "networks", networks));
        return ImmutableList.of(Corpus.of(CORPUS_ID, CORPUS_NAME, ImmutableList.of()));

    }
}
