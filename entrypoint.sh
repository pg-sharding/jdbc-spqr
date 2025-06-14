#!/bin/bash
set -ex

do_sharding_setup=1

for arg in "$@"; do
  case $arg in
    --skip-sharding-setup)
      do_sharding_setup=0
      ;;
    --help|-h)
      echo "Usage: $0 [--skip-sharding-setup]"
      exit 0
      ;;
    *)
      echo "Unexpected argument: $arg"
      echo "See $0 --help for more"
      exit 1
      ;;
  esac
done

if [[ $do_sharding_setup -eq 1 ]]; then
    # set up sharding
    psql -h $PG_HOST -p $PG_PORT -U spqr-console -c "CREATE DISTRIBUTION ds1 COLUMN TYPES INTEGER"
    psql -h $PG_HOST -p $PG_PORT -U spqr-console -c "ALTER DISTRIBUTION ds1 ATTACH RELATION person DISTRIBUTION KEY id"
    psql -h $PG_HOST -p $PG_PORT -U spqr-console -c "CREATE KEY RANGE kr2 FROM 10 ROUTE TO sh2"
    psql -h $PG_HOST -p $PG_PORT -U spqr-console -c "CREATE KEY RANGE kr1 FROM 0 ROUTE TO sh1"
fi

# test
mvn test -DpgHost=$PG_HOST -DpgPort=$PG_PORT -DpgDb=$PG_DATABASE -DpgUser=$PG_USER -DpgPassword=$PGPASSWORD
