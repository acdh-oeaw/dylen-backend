package acdh.oeaw.ac.at.dylenegonetworkserice.service;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.EgoNetwork;
import acdh.oeaw.ac.at.dylenegonetworkserice.exceptions.EgoNetworkNotFoundException;
import acdh.oeaw.ac.at.dylenegonetworkserice.persistence.repository.EgoNetworkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Profile("dummy")
public class NetworkServiceDummy implements EgoNetworkService {

    public Map<String, EgoNetwork> map;

    public NetworkServiceDummy(EgoNetworkRepository egoNetworkRepository) throws IOException {
        var mapper = new ObjectMapper();

        var network1 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Asyl_6.json");
        var network2 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Computer_6.json");
        var network3 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Fluechtling_6.json");
        var network4 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Abschiebung_6.json");
        var network5 = readEgoNetworkFromJson(mapper, "samples/amc/2014_Kuestenwache_6.json");
        var network6 = readEgoNetworkFromJson(mapper, "samples/amc/2015_Klick_6.json");
        var network7 = readEgoNetworkFromJson(mapper, "samples/amc/2015_Regierung_6.json");

        this.map = new HashMap<>();
        map.put(network1.getId(), network1);
        map.put(network2.getId(), network2);
        map.put(network3.getId(), network3);
        map.put(network3.getId(), network3);
        map.put(network4.getId(), network4);
        map.put(network5.getId(), network5);
        map.put(network6.getId(), network6);
        map.put(network7.getId(), network7);
    }

    @Override
    public EgoNetwork getNetworkById(String id) {
        var network = map.get(id);
        if(network==null) {
            throw new EgoNetworkNotFoundException("No ego network found with the id", id);
        }
        return network;
    }
    @Override
    public List<EgoNetwork> getNetworkBySource(String source) {
        return map.values().stream()
                .filter(network -> network.getSource().equals(source))
                .collect(Collectors.toList());
    }

    @Override
    public List<EgoNetwork> getNetworkByTargetWord(String targetWord) {
        return null;
    }

    void putEgoNetwork(EgoNetwork network) {
        map.put(network.getId(), network);
    }

    private EgoNetwork readEgoNetworkFromJson(ObjectMapper mapper, String resourcePath) throws IOException {
        var resource = new ClassPathResource(resourcePath).getInputStream();

        EgoNetwork network1;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            var jsonStr = reader.lines()
                    .collect(Collectors.joining("\n"));
            network1 = mapper.readValue(jsonStr, EgoNetwork.class);
        }

        return network1;
    }
}
