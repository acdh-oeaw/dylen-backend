package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.PartyMetrics;

import java.util.List;

public interface GeneralNetworkServiceInterface {
    List<GeneralTargetWord> getGeneralSourceByParty(String party);
    GeneralTargetWord getGeneralSourceByPartyYear(String party, String year);
    PartyMetrics getAvailableYearsForParty(String party);
    PartyMetrics getAvailableYearsForSpeaker(String entityName);
    GeneralTargetWordSpeaker getGeneralSourceBySpeakerYear(String entityName);
    PartyMetrics findSpeakerByParty(String party);
}
