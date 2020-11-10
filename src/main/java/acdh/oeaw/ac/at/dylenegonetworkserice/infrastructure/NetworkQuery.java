package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkService;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import com.google.common.collect.ImmutableList;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class NetworkQuery implements GraphQLQueryResolver {

    final EgoNetworkService networkService;

    public NetworkQuery(EgoNetworkService networkService) {
        this.networkService = networkService;
    }

    public EgoNetwork getNetworkById(String id){
        return networkService.getNetworkById(id);
    }

    public List<EgoNetwork> getNetworksByTargetWord(String targetWord) {
        return networkService.getNetworkByTargetWord(targetWord);
    }
}
