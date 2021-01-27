package acdh.oeaw.ac.at.dylenegonetworkservice.service;

import acdh.oeaw.ac.at.dylenegonetworkservice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkservice.domain.TargetWord;

import java.util.List;

public interface EgoNetworkServiceInterface {
    List<TargetWord> getTargetWordsOfCorpusAndSource(String corpus, String source);
    TargetWord getTargetWordById(String id);
    List<EgoNetwork> getNetworkByTargetWord(String targetWord);
}
