docker cp sosnazario-ui/. animal-shelter-ui-1:app/.
docker restart  animal-shelter-ui-1

docker cp sosnazario-api/. animal-shelter-api-1:sosnazario-api/.
docker exec -it animal-shelter-api-1 mvn clean install
docker restart  animal-shelter-api-1