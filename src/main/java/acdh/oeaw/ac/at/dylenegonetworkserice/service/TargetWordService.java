package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.TargetWordDto;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.TargetWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetWordService {

    final TargetWordRepository targetWordRepository;

    public TargetWordService(TargetWordRepository targetRepository) {
        this.targetWordRepository = targetRepository;
    }


    public List<TargetWordDto> getAllAvailableTargetWords() {
        //this.targetRepository.find
        return null;
    }
}
