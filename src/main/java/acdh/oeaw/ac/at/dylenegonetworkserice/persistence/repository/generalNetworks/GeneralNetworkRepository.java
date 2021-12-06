package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.generalNetworks;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWordSpeaker;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.GeneralTargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks.PartyMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GeneralNetworkRepository extends MongoRepository<GeneralTargetWord, String> {
    @Query("{'entity':?0, 'available_years': {$exists: false}}")
    List<GeneralTargetWord> findGeneralSourceByParty(String entity);

    @Query("{'entity':?0, 'available_years': {$exists: true}}")
    PartyMetrics getAvailableYearsForParty(String entity);

    @Query("{'entity':?0, 'networks.year': ?1}")
    GeneralTargetWord findGeneralSourceByPartyYear(String entity, String year);
}
