Client Server MongoDB
=====================

A simple 3-app (or 3-container) system for Docker practice.

It consists of 3 parts:
- a client that sends requests to the server
- the server that handles requests from the client and communicates with mongoDB
- a mongoDB server

# Building docker images
To build docker images for client and docker, invoke 'docker build' from the clientServerMongo directory.

For example, to build the client image:
> docker build -f dockerFiles/Docker_client .