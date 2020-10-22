package acdh.oeaw.ac.at.dylenegonetworkserice;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestUtil {
    public static EgoNetwork extractEgoNetwork(org.springframework.core.io.Resource r) {
        try {
            var jsonString = new String(r.getInputStream().readAllBytes());
            return new ObjectMapper().readValue(jsonString, EgoNetwork.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
