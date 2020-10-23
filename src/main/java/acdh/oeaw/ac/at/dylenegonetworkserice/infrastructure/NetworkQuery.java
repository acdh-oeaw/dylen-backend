package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.NetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NetworkQuery implements GraphQLQueryResolver {

    final NetworkService networkService;

    public NetworkQuery(NetworkService networkService) {
        this.networkService = networkService;
    }

    public EgoNetwork getNetworkById(String id){
        return networkService.getNetworkById(id);
    }

    public List<EgoNetwork> networkByTargetWord(String targetWord) {
        return networkService.getNetworkByTargetWord(targetWord);
    }
}
