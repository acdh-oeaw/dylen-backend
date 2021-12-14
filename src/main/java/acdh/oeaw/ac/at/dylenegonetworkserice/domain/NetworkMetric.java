package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NetworkMetric {
    Double density;
    Double clusteringCoefficient;

    private NetworkMetric(Double density, Double clusteringCoefficient) {
        this.density = density;
        this.clusteringCoefficient = clusteringCoefficient;
    }

    @JsonCreator
    public static NetworkMetric of(@JsonProperty("density") Double density, @JsonProperty("clustering_coefficient") Double clusteringCoefficient) {
        return new NetworkMetric(density, clusteringCoefficient);
    }
}
