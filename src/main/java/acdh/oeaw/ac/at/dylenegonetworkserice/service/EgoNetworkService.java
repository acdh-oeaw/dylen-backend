package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;

import java.lang.annotation.Target;
import java.util.List;

public interface EgoNetworkService {
    public List<TargetWord> getAllAvailableTargetWords();
    public List<TargetWord> getTargetWordsOfCorpusAndSource(String corpus, String source);
    public TargetWord getTargetWordById(String id);
    public List<EgoNetwork> getNetworkByTargetWord(String targetWord);
}
