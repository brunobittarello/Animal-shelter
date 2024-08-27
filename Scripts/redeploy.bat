docker cp sosnazario-api/. animal-shelter-api-1:sosnazario-api/.
docker exec -it animal-shelter-api-1 mvn clean install
docker restart  animal-shelter-api-1