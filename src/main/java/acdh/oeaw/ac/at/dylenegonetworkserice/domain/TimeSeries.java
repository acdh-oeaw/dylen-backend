package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TimeSeries {
    private TimeSeriesMetric freqDiffNorm;
    private TimeSeriesMetric frobeniusSimilarity;
    private TimeSeriesMetric jaccardSimilarity;
    private TimeSeriesMetric rankdcgSimilarity;
    private TimeSeriesMetric localNeighbourhoodSimilarity;

    public TimeSeries(TimeSeriesMetric freqDiffNorm, TimeSeriesMetric jaccardSimilarity, TimeSeriesMetric frobeniusSimilarity, TimeSeriesMetric rankdcgSimilarity, TimeSeriesMetric localNeighbourhoodSimilarity) {
        this.freqDiffNorm = freqDiffNorm;
        this.jaccardSimilarity = jaccardSimilarity;
        this.frobeniusSimilarity = frobeniusSimilarity;
        this.rankdcgSimilarity = rankdcgSimilarity;
        this.localNeighbourhoodSimilarity = localNeighbourhoodSimilarity;
    }

    @JsonCreator
    public static TimeSeries of(@JsonProperty("freqDiffNorm") TimeSeriesMetric freqDiffNorm,
                                @JsonProperty("jaccardSimilarity") TimeSeriesMetric jaccardSimilarity,
                                @JsonProperty("frobeniusSimilarity") TimeSeriesMetric frobeniusSimilarity,
                                @JsonProperty("rankdcgSimilarity") TimeSeriesMetric rankdcgSimilarity,
                                @JsonProperty("localNeighbourhoodSimilarity") TimeSeriesMetric localNeighbourhoodSimilarity) {
        return new TimeSeries(freqDiffNorm, jaccardSimilarity, frobeniusSimilarity, rankdcgSimilarity,
                localNeighbourhoodSimilarity);
    }
}
