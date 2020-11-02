package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.EgoNetworkNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Profile("prod")
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
        var result = egoNetworkRepository.findBySource(source);

        if (result.size()==0) {
            return ImmutableList.of();
        }
        return result;
    }

    @Override
    public List<EgoNetwork> getNetworkByTargetWord(String targetWord) {
        return egoNetworkRepository.findByText(targetWord);
    }
}
