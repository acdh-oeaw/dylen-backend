package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;
import acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto.TargetWordsSliceDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EgoNetworkServiceInterface {
    TargetWordsSliceDto getTargetWordsOfCorpusAndSource(String corpus, String source, Pageable pageRequest);
    TargetWord getTargetWordById(String id);
    List<EgoNetwork> getNetworkByTargetWord(String targetWord);
}
