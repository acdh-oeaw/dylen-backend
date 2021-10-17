package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.util.List;

@Data
public class TimeSeriesMetric {
    List<Double> firstYear;
    List<Double> lastYear;
    List<Double> previousYear;

    private TimeSeriesMetric(List<Double> firstYear, List<Double> lastYear, List<Double> previousYear) {
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.previousYear = previousYear;
    }

    @JsonCreator
    public static TimeSeriesMetric of(@JsonProperty("firstYear") List<Double> firstYear,
                                      @JsonProperty("lastYear") List<Double> lastYear,
                                      @JsonProperty("previousYear") List<Double> previousYear) {
        return new TimeSeriesMetric(firstYear, lastYear, previousYear);
    }
}
