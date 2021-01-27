package acdh.oeaw.ac.at.dylenegonetworkservice;

import acdh.oeaw.ac.at.dylenegonetworkservice.domain.TargetWord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;

import java.io.IOException;

public class TestUtil {
    public static TargetWord extractTargetWord(org.springframework.core.io.Resource r) {
        try {
            var jsonString = new String(r.getInputStream().readAllBytes());
            return new ObjectMapper().readValue(jsonString, TargetWord.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Document extractJsonStrings(org.springframework.core.io.Resource r) {
        try {
            var jsonString = new String(r.getInputStream().readAllBytes());
            return Document.parse(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
