!/bin/zsh
SPEAKER_DIRS=(*/)
for dir in "${SPEAKER_DIRS[@]}"; 
do
	JSONS=($dir/*.json) 
	for json in "${JSONS[@]}";
		do
			mongoimport --uri YOUR_MONGODB_URI_STR -c generalNet --file $json --type json;
		done		 
done
