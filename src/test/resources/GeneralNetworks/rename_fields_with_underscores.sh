sed -i '.bak' 's/closenessCentrality/closeness_centrality/g' *.json
sed -i '.bak' 's/degreeCentrality/degree_centrality/g' *.json
sed -i '.bak' 's/betweennessCentrality/betweenness_centrality/g' *.json
sed -i '.bak' 's/eigenvectorCentrality/eigenvector_centrality/g' *.json
sed -i '.bak' 's/loadCentrality/load_centrality/g' *.json
sed -i '.bak' 's/harmonicCentrality/harmonic_centrality/g' *.json
sed -i '.bak' 's/clusteringCoefficient/clustering_coefficient/g' *.json
sed -i '.bak' 's/normalisedFrequency/normalised_frequency/g' *.json
