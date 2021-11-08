sed -i '.bak' 's/closeness_centrality/closenessCentrality/g' *.json
sed -i '.bak' 's/degree_centrality/degreeCentrality/g' *.json
sed -i '.bak' 's/betweenness_centrality/betweennessCentrality/g' *.json
sed -i '.bak' 's/eigenvector_centrality/eigenvectorCentrality/g' *.json
sed -i '.bak' 's/load_centrality/loadCentrality/g' *.json
sed -i '.bak' 's/harmonic_centrality/harmonicCentrality/g' *.json
sed -i '.bak' 's/clustering_coefficient/clusteringCoefficient/g' *.json
sed -i '.bak' 's/normalised_frequency/normalized_frequency/g' *.json