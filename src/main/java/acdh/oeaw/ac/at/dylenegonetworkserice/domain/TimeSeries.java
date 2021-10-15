package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

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

    public static TimeSeries of(TimeSeriesMetric freqDiffNorm, TimeSeriesMetric jaccardSimilarity,
                                TimeSeriesMetric frobeniusSimilarity, TimeSeriesMetric rankdcgSimilarity,
                                TimeSeriesMetric localNeighbourhoodSimilarity) {
        return new TimeSeries(freqDiffNorm, jaccardSimilarity, frobeniusSimilarity, rankdcgSimilarity,
                localNeighbourhoodSimilarity);
    }
}
