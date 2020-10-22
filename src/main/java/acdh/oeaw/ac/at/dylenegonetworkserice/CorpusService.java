package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Corpus;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorpusService {

    final EgoNetworkRepository egoNetworkRepository;

    public CorpusService(EgoNetworkRepository egoNetworkRepository) {
        this.egoNetworkRepository = egoNetworkRepository;
    }

    public List<Corpus> getAllCorpora() {
        var amc = this.egoNetworkRepository.findEgoNetworksByCorpus("AMC");
        return null;
    }

}
