package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.PartyMetrics;
import acdh.oeaw.ac.at.dylenegonetworkserice.service.GeneralNetworkServiceInterface;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Component
public class GeneralNetworkQuery implements GraphQLQueryResolver {

    private final GeneralNetworkServiceInterface gnsInterface;

    public GeneralNetworkQuery(GeneralNetworkServiceInterface gnsInterface) {
        this.gnsInterface = gnsInterface;
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public List<GeneralTargetWord> getGeneralSourceByParty(String party) {
        return gnsInterface.getGeneralSourceByParty(party);
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public GeneralTargetWord getGeneralSourceByPartyYear(String party, String year) {
        return gnsInterface.getGeneralSourceByPartyYear(party, year);
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public GeneralTargetWordSpeaker getGeneralSourceBySpeakerYear(String entity_name) {
        return gnsInterface.getGeneralSourceBySpeakerYear(entity_name);
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public PartyMetrics findSpeakerByParty(String party) {
        return gnsInterface.findSpeakerByParty(party);
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public PartyMetrics getAvailableYearsForParty(String party) {
        return gnsInterface.getAvailableYearsForParty(party);
    }

    @CrossOrigin(origins = "https://dylen-tool.acdh.oeaw.ac.at")
    public PartyMetrics getAvailableYearsForSpeaker(String entityName) {
        return gnsInterface.getAvailableYearsForSpeaker(entityName);
    }
}
