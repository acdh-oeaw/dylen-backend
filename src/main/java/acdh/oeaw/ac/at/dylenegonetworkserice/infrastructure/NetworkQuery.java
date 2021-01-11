package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Component
public class NetworkQuery implements GraphQLQueryResolver {

    final EgoNetworkService networkService;

    public NetworkQuery(EgoNetworkService networkService) {
        this.networkService = networkService;
    }

    public EgoNetwork getNetwork(String targetWordId, int year) {
        var targetWord = networkService.getTargetWordById(targetWordId);
        var network = targetWord.getNetworks().stream()
                .filter(egoNetwork -> egoNetwork.getYear()==year)
                .collect(toSingleton());
        return network;
    }

    private static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if(list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}
