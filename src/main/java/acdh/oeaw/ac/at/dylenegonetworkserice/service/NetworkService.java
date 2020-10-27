package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.EgoNetworkNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;

import java.io.IOException;
import java.util.List;

public class NetworkService implements EgoNetworkService {
    final
    EgoNetworkRepository egoNetworkRepository;


    public NetworkService(EgoNetworkRepository egoNetworkRepository) throws IOException {
        this.egoNetworkRepository = egoNetworkRepository;
    }

    @Override
    public EgoNetwork getNetworkById(String id) {
        return egoNetworkRepository.findById(id)
                .orElseThrow(()-> {
                    throw new EgoNetworkNotFoundException("No ego network found with the id", id);
                });
    }

    @Override
    public List<EgoNetwork> getNetworkBySource(String source) {
        return null;
    }

    @Override
    public List<EgoNetwork> getNetworkByTargetWord(String targetWord) {
        return egoNetworkRepository.findByTargetWord(targetWord);
    }
}
