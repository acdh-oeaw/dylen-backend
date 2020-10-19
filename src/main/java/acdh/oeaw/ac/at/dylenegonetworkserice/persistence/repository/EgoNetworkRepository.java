package acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EgoNetworkRepository extends MongoRepository<EgoNetwork, String> {
    public Optional<EgoNetwork> findById(String id);
}
