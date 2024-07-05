# springboot + docker-nuxt3-oracle

### Start the Client Application with Docker

    docker-compose up
  
### pull oracle docker

    https://container-registry.oracle.com/

    docker login container-registry.oracle.com

    docker pull container-registry.oracle.com/database/express:latest

    docker run -d -it --name oracle-xe-21c -p 1521:1521 -p 5500:5500 -e ORACLE_PWD=xxx container-registry.oracle.com/database/express:latest

<p align="center">
  <b>Thank You :)</b>
</p>
 
