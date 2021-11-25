package acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;

import java.util.List;

public interface GeneralNetworkServiceInterface {
    List<GeneralTargetWord> getGeneralSourceByParty(String party);
    GeneralTargetWord getGeneralSourceByPartyYear(String party, String year);
    GeneralTargetWordSpeaker getGeneralSourceBySpeakerYear(String entityName, String year);
    List<GeneralTargetWordSpeaker> findSpeakerByParty(String party);
}
