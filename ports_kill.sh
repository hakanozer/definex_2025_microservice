#!/bin/bash

PORTS=(9999 1010)
echo "Stopping processes on ports: ${PORTS[*]}"

for PORT in "${PORTS[@]}"; do
  PID=$(lsof -t -i:$PORT)
  if [ ! -z "$PID" ]; then
    echo "Killing process on port $PORT (PID: $PID)"
    kill -9 $PID
  else
    echo "No process found on port $PORT"
  fi
done