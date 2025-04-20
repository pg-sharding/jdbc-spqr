#!/bin/bash
set -ex

# set up sharding
psql -h $PG_HOST -p $PG_PORT -U spqr-console -c "CREATE DISTRIBUTION ds1 COLUMN TYPES INTEGER"
psql -h $PG_HOST -p $PG_PORT -U spqr-console -c "ALTER DISTRIBUTION ds1 ATTACH RELATION person DISTRIBUTION KEY id"
psql -h $PG_HOST -p $PG_PORT -U spqr-console -c "CREATE KEY RANGE kr2 FROM 10 ROUTE TO sh2"
psql -h $PG_HOST -p $PG_PORT -U spqr-console -c "CREATE KEY RANGE kr1 FROM 0 ROUTE TO sh1"

# test
mvn test -DpgHost=$PG_HOST -DpgPort=$PG_PORT -DpgDb=$PG_DATABASE -DpgUser=$PG_USER -DpgPassword=$PGPASSWORD
