package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.NetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;


@Component
public class NetworkQuery implements GraphQLQueryResolver {

    final NetworkService networkService;

    public NetworkQuery(NetworkService networkService) {
        this.networkService = networkService;
    }

    public EgoNetwork getNetworkById(String id){
        return networkService.getNetworkById(id);
    }

}
