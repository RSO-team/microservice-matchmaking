# RSO: Microservice matchmaking

Microservice which manages matchmaking in our service

## Prerequisites

```bash
docker run -d --name pg-matchmaking -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=matchmaking -p 5432:5432 postgres:13
```