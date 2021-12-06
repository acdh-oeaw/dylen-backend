package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.PartyMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GeneralNetworkSpeakerRepository extends MongoRepository<GeneralTargetWordSpeaker, String> {

    @Query("{'entity_name':?0, 'networks.year': ?1}")
    GeneralTargetWordSpeaker findGeneralSourceBySpeakerYear(String entity_name, String year);

    @Query("{'entity_name':?0, 'available_years': {$exists: true}}")
    PartyMetrics getAvailableYearsForSpeaker(String entityName);

    @Query("{'entity':?0, 'available_years': {$exists: true}}")
    PartyMetrics findSpeakerByParty(String party);
}
