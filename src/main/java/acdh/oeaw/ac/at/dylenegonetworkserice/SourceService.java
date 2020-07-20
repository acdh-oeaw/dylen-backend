package acdh.oeaw.ac.at.dylenegonetworkserice;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SourceService {
    public enum SourceEnum {
        STANDARD("1", "Standard"),
        FALTER("2", "Falter"),
        KRONE("3", "Krone"),
        HEUTE("4", "Heute");

        private final String id;
        private final String name;

        SourceEnum(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

    private Map<String, SourceEnum> map;

    public SourceService() {
        this.map = ImmutableMap.of("Standard", SourceEnum.STANDARD,
                "Falter", SourceEnum.FALTER,
                "Krone", SourceEnum.KRONE,
                "Heute", SourceEnum.HEUTE);
    }

    public String getIdByName(String sourceName) {
        return this.map.get(sourceName).getId();
    }

}
