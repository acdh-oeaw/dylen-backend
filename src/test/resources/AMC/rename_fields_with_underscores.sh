!/bin/zsh
sed -i '.bak' 's/closeness_centrality/closenessCentrality/g' selected/*.json
sed -i '.bak' 's/degree_centrality/degreeCentrality/g' selected/*.json
sed -i '.bak' 's/betweenness_centrality/betweennessCentrality/g' selected/*.json
sed -i '.bak' 's/eigenvector_centrality/eigenvectorCentrality/g' selected/*.json
