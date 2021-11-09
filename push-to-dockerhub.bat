docker build -t lgaljo/rt_basketball_matchmaking -f Dockerfile_with_maven_build .
docker tag lgaljo/rt_basketball_matchmaking lgaljo/rt_basketball_matchmaking:latest
docker push -a lgaljo/rt_basketball_matchmaking