package acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class PartyMetrics {
    @Field("jaccard_similarity")
    private PartyMetricsSimilarity jaccard_similarity;

    @Field("rankdcg_similarity")
    private PartyMetricsSimilarity rankdcg_similarity;

    @Field("frobenius_similarity")
    private PartyMetricsSimilarity frobenius_similarity;

    private List<String> available_years;
    private List<String> speakers;

    public PartyMetrics(PartyMetricsSimilarity jaccard_similarity,
                        PartyMetricsSimilarity rankdcg_similarity,
                        PartyMetricsSimilarity frobenius_similarity,
                        List<String> available_years,
                        List<String> speakers) {
        this.jaccard_similarity = jaccard_similarity;
        this.rankdcg_similarity = rankdcg_similarity;
        this.frobenius_similarity = frobenius_similarity;
        this.available_years = available_years;
        this.speakers = speakers;
    }

    @JsonCreator
    public static PartyMetrics of(
            @JsonProperty("jaccard_similarity") PartyMetricsSimilarity jaccard_similarity,
            @JsonProperty("rankdcg_similarity") PartyMetricsSimilarity rankdcg_similarity,
            @JsonProperty("frobenius_similarity") PartyMetricsSimilarity frobenius_similarity,
            @JsonProperty("available_years") List<String> available_years,
            @JsonProperty("speakers") List<String> speakers
    ) {
        return new PartyMetrics(jaccard_similarity,
                rankdcg_similarity,
                frobenius_similarity,
                available_years,
                speakers);
    }

    public static PartyMetrics fromJson(String json) {
        return null;
    }
}
