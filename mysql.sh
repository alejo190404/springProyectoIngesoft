#!/bin/sh

# ejecute `mysql -p` dentro del contenedor `tareas-db`
docker exec -it bikemap-db \
    mysql -p