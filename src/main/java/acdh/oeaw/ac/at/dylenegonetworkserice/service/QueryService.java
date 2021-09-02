package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService implements QueryServiceInterface {

    final TargetWordRepository targetWordRepository;

    public QueryService(TargetWordRepository targetWordRepository) {
        this.targetWordRepository = targetWordRepository;
    }

    @Override
    public List<String> getSourcesByCorpus(String corpus) {
        return targetWordRepository.findSourceByCorpus(corpus);
    }
}
