docker cp animal-shelter-ui/. animal-shelter-ui-1:app/.
docker restart  animal-shelter-ui-1

docker cp animal-shelter-api/. animal-shelter-api-1:animal-shelter-api/.
docker exec -it animal-shelter-api-1 mvn clean install
docker restart  animal-shelter-api-1