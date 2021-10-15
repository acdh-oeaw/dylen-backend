package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.util.List;

@Data
public class TimeSeriesMetric {
    List<Double> firstYear;
    List<Double> lastYear;
    List<Double> previousYear;

    private TimeSeriesMetric(ImmutableList<Double> firstYear, ImmutableList<Double> lastYear, ImmutableList<Double> previousYear) {
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.previousYear = previousYear;
    }

    public static TimeSeriesMetric of(ImmutableList<Double> firstYear, ImmutableList<Double> lastYear, ImmutableList<Double> previousYear) {
        return new TimeSeriesMetric(firstYear, lastYear, previousYear);
    }
}
