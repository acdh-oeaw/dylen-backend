package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.EgoNetworkService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TargetWordQuery implements GraphQLQueryResolver {
    private final EgoNetworkService networkService;

    public TargetWordQuery(EgoNetworkService networkService) {
        this.networkService = networkService;
    }