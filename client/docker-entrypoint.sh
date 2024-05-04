#!/bin/bash

# Exit on fail
set -e

# Bundle install
yarn install

# Start services
yarn run dev

# Finally call command issued to the docker service
exec "$@"
