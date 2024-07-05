#!/bin/bash

# Exit on fail
set -e

# Bundle install
yarn install

# Start services
yarn run dev


rm /tmp/nitro/worker-82-1.sock

# Finally call command issued to the docker service
exec "$@"
