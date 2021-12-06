package acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PartyMetricsSimilarity {

    private List<Double> previous_year;
    private List<Double> first_year;
    private List<Double> last_year;

    public PartyMetricsSimilarity(List<Double> previous_year, List<Double> first_year, List<Double> last_year) {
        this.previous_year = previous_year;
        this.first_year = first_year;
        this.last_year = last_year;
    }

    @JsonCreator
    public static PartyMetricsSimilarity of(
            @JsonProperty("previous_year") List<Double> previous_year,
            @JsonProperty("first_year") List<Double> first_year,
            @JsonProperty("last_year") List<Double> last_year
    ) {
        return new PartyMetricsSimilarity(previous_year, first_year, last_year);
    }
}
