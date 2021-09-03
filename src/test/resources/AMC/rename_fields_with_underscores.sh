!/bin/zsh
sed -i '.bak' 's/closeness_centrality/closenessCentrality/g' selected/*.json
sed -i '.bak' 's/degree_centrality/degreeCentrality/g' selected/*.json
sed -i '.bak' 's/betweenness_centrality/betweennessCentrality/g' selected/*.json
sed -i '.bak' 's/eigenvector_centrality/eigenvectorCentrality/g' selected/*.json
sed -i '.bak' 's/load_centrality/loadCentrality/g' selected/*.json
sed -i '.bak' 's/harmonic_centrality/harmonicCentrality/g' selected/*.json
sed -i '.bak' 's/clustering_coefficient/clusteringCoefficient/g' selected/*.json
sed -i '.bak' 's/closeness_centrality/closenessCentrality/g' *.json
sed -i '.bak' 's/degree_centrality/degreeCentrality/g' *.json
sed -i '.bak' 's/betweenness_centrality/betweennessCentrality/g' *.json
sed -i '.bak' 's/eigenvector_centrality/eigenvectorCentrality/g' *.json
sed -i '.bak' 's/load_centrality/loadCentrality/g' *.json
sed -i '.bak' 's/harmonic_centrality/harmonicCentrality/g' *.json
sed -i '.bak' 's/clustering_coefficient/clusteringCoefficient/g' *.json