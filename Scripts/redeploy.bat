docker cp sosnazario-ui/. animal-shelter-ui-1:app/.
docker restart  animal-shelter-ui-1

@REM docker cp sosnazario-api/. animal-shelter-api-1:sosnazario-api/.
@REM docker exec -it animal-shelter-api-1 mvn clean install
@REM docker restart  animal-shelter-api-1