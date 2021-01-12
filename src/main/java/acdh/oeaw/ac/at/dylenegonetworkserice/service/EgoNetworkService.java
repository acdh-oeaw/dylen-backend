package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;

import java.util.List;

public interface EgoNetworkService {
    List<TargetWord> getTargetWordsOfCorpusAndSource(String corpus, String source);
    TargetWord getTargetWordById(String id);
    List<EgoNetwork> getNetworkByTargetWord(String targetWord);
}
