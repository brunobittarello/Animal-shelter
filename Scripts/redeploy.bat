docker cp bezkoder-app/. animal-shelter-app-1:bezkoder-app/.
docker exec -it animal-shelter-app-1 mvn clean install
docker restart  animal-shelter-app-1