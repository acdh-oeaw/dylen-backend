package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture;
import org.junit.jupiter.api.Test;

import static acdh.oeaw.ac.at.dylenegonetworkserice.TestFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TimeSeriesTest {
    @Test
    void shouldInstantiateTimeSeries() {
        var timeSeries = TimeSeries.of(freqDiffNorm, jaccardSimilarity, frobeniusSimilarity, rankdcgSimilarity, localNeighbourhoodSimilarity);

        assertThat(timeSeries.getFreqDiffNorm().getFirstYear().size()).isEqualTo(2);
        assertThat(timeSeries.getJaccardSimilarity().getFirstYear().size()).isEqualTo(2);
        assertThat(timeSeries.getRankdcgSimilarity().getFirstYear().size()).isEqualTo(2);
        assertThat(timeSeries.getFrobeniusSimilarity().getFirstYear().size()).isEqualTo(2);
        assertThat(timeSeries.getLocalNeighbourhoodSimilarity().getFirstYear().size()).isEqualTo(2);
    }

}