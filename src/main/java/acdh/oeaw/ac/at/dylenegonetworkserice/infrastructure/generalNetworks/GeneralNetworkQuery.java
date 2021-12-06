package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.PartyMetrics;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks.GeneralNetworkServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneralNetworkQuery implements GraphQLQueryResolver {

    private final GeneralNetworkServiceInterface gnsInterface;

    public GeneralNetworkQuery(GeneralNetworkServiceInterface gnsInterface) {
        this.gnsInterface = gnsInterface;
    }

    public List<GeneralTargetWord> getGeneralSourceByParty(String party) {
        return gnsInterface.getGeneralSourceByParty(party);
    }

    public GeneralTargetWord getGeneralSourceByPartyYear(String party, String year) {
        return gnsInterface.getGeneralSourceByPartyYear(party, year);
    }

    public GeneralTargetWordSpeaker getGeneralSourceBySpeakerYear(String entity_name, String year) {
        return gnsInterface.getGeneralSourceBySpeakerYear(entity_name, year);
    }

    public PartyMetrics findSpeakerByParty(String party) {
        return gnsInterface.findSpeakerByParty(party);
    }

    public PartyMetrics getAvailableYearsForParty(String party) {
        return gnsInterface.getAvailableYearsForParty(party);
    }

    public PartyMetrics getAvailableYearsForSpeaker(String entityName) {
        return gnsInterface.getAvailableYearsForSpeaker(entityName);
    }
}
