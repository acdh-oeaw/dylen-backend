package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;

import java.util.List;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.PartyMetrics;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.GeneralNetworkRepository;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.GeneralNetworkSpeakerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GeneralNetworkService implements GeneralNetworkServiceInterface {
    final GeneralNetworkRepository generalNetworkRepository;
    final GeneralNetworkSpeakerRepository generalNetworkSpeakerRepository;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GeneralNetworkService(GeneralNetworkRepository generalNetworkRepository,
                                 GeneralNetworkSpeakerRepository generalNetworkSpeakerRepository) {
        this.generalNetworkRepository = generalNetworkRepository;
        this.generalNetworkSpeakerRepository = generalNetworkSpeakerRepository;
    }

    @Override
    public List<GeneralTargetWord> getGeneralSourceByParty(String party) {
        logger.info(String.format("Loading party %s", party));
        return generalNetworkRepository.findGeneralSourceByParty(party);
    }

    @Override
    public GeneralTargetWord getGeneralSourceByPartyYear(String party, String year) {
        return generalNetworkRepository.findGeneralSourceByPartyYear(party, year);
    }

    @Override
    public PartyMetrics getAvailableYearsForParty(String party) {
        return generalNetworkRepository.getAvailableYearsForParty(party);
    }

    @Override
    public PartyMetrics getAvailableYearsForSpeaker(String entityName) {
        return generalNetworkSpeakerRepository.getAvailableYearsForSpeaker(entityName);
    }

    @Override
    public GeneralTargetWordSpeaker getGeneralSourceBySpeakerYear(String entityName) {
        return generalNetworkSpeakerRepository.findGeneralSourceBySpeakerYear(entityName);
    }

    @Override
    public PartyMetrics findSpeakerByParty(String party) {
        return generalNetworkSpeakerRepository.findSpeakerByParty(party);
    }

}
