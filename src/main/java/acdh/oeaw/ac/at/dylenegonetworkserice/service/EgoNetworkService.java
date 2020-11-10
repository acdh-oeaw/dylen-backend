package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.domain.TargetWord;

import java.util.List;

public interface EgoNetworkService {
    public EgoNetwork getNetworkById(String id);
    public List<EgoNetwork> getNetworkBySource(String source);
    public List<EgoNetwork> getNetworkByTargetWord(String targetWord);
    public List<TargetWord> getTargetWordsOfCorpusAndSource(String corpus, String source);
    public List<TargetWord> getAllAvailableTargetWords();
}
