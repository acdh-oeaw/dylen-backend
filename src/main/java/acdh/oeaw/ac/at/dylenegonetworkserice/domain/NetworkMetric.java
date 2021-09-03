package acdh.oeaw.ac.at.dylenegonetworkserice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NetworkMetric {
    double density;
    double clusteringCoefficient;

    private NetworkMetric(double density, double clusteringCoefficient) {
        this.density = density;
        this.clusteringCoefficient = clusteringCoefficient;
    }

    @JsonCreator
    public static NetworkMetric of(@JsonProperty("density") double density, @JsonProperty("clustering_coefficient") double clusteringCoefficient) {
        return new NetworkMetric(density, clusteringCoefficient);
    }
}
