package acdh.oeaw.ac.at.dylenegonetworkserice.service.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;

import java.util.List;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.generalNetworks.GeneralNetworkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GeneralNetworkService implements GeneralNetworkServiceInterface {
    final GeneralNetworkRepository generalNetworkRepository;
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GeneralNetworkService(GeneralNetworkRepository generalNetworkRepository) {
        this.generalNetworkRepository = generalNetworkRepository;
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
    public GeneralTargetWordSpeaker getGeneralSourceBySpeakerYear(String entityName, String year) {
        return generalNetworkRepository.findGeneralSourceBySpeakerYear(entityName, year);
    }

}
