package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkServiceInterface;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import graphql.kickstart.tools.GraphQLQueryResolver;
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
                .filter(egoNetwork -> egoNetwork.getYear()==year)
                .collect(toSingleton());

        return network;
    }
}
