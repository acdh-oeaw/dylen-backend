package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.TargetWordsSliceDto;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static acdh.oeaw.ac.at.dylenegonetworkserice.util.StreamUtils.toSingleton;


@Component
public class NetworkQuery implements GraphQLQueryResolver {

    private final EgoNetworkServiceInterface networkService;

    public NetworkQuery(EgoNetworkServiceInterface networkService) {
        this.networkService = networkService;
    }

    public EgoNetwork getNetwork(String targetWordId, int year) {
        var targetWord = networkService.getTargetWordById(targetWordId);
        var network = targetWord.getNetworks().stream()
                .filter(egoNetwork -> egoNetwork.getYear() == year)
                .collect(toSingleton());

        return network;
    }

    public TargetWordsSliceDto getNetworksByCorpusAndSource(String corpus, String source, int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        return networkService.getTargetWordsOfCorpusAndSource(corpus, source, pageRequest);
    }
}
