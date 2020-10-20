package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.CorpusService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.CORPUS_1;

@RunWith(SpringRunner.class)
class CorpusServiceTest {

    @Test
    void shouldRetrieveAllCorpora() {
        var corpusService = new CorpusService();
        var corpus_1 = CORPUS_1;
        var corpora = corpusService.getAllCorpora();

        //assertEquals();
    }
}