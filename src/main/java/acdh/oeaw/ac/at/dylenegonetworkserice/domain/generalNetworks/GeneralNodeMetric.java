package acdh.oeaw.ac.at.dylenegonetworkserice.domain.generalNetworks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GeneralNodeMetric {
    Double degree_centrality;
    Double closeness_centrality;
    Double betweenness_centrality;
    Double eigenvector_centrality;
    Double pagerank;
    Double load_centrality;
    Double harmonic_centrality;
    Double clustering_coefficient;

    private GeneralNodeMetric(Double degree_centrality, Double closeness_centrality, Double betweenness_centrality,
                              Double eigenvector_centrality,
                              Double pagerank, Double load_centrality, Double harmonic_centrality, Double clustering_coefficient) {
        this.degree_centrality = degree_centrality;
        this.closeness_centrality = closeness_centrality;
        this.betweenness_centrality = betweenness_centrality;
        this.eigenvector_centrality = eigenvector_centrality;
        this.pagerank = pagerank;
        this.load_centrality = load_centrality;
        this.harmonic_centrality = harmonic_centrality;
        this.clustering_coefficient = clustering_coefficient;
    }

    @JsonCreator
    public static GeneralNodeMetric of(@JsonProperty("degree_centrality") Double degree_centrality,
                                       @JsonProperty("closeness_centrality") Double closeness_centrality,
                                       @JsonProperty("betweenness_centrality") Double betweenness_centrality,
                                       @JsonProperty("eigenvector_centrality") Double eigenvector_centrality,
                                       @JsonProperty("pagerank") Double pagerank,
                                       @JsonProperty("load_centrality") Double load_centrality,
                                       @JsonProperty("harmonic_centrality") Double harmonic_centrality,
                                       @JsonProperty("clustering_coefficient") Double clustering_coefficient
    ) {
        return new GeneralNodeMetric(degree_centrality, closeness_centrality, betweenness_centrality, eigenvector_centrality,
                pagerank, load_centrality, harmonic_centrality, clustering_coefficient);
    }
}
