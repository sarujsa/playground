Client Server MongoDB (csm)
===========================

A simple 3-app (or 3-container) system for Docker practice.

It consists of 3 parts:
- an interactive client that sends POST/GET requests to the server
- the server that handles requests from the client and communicates with mongoDB
- a mongoDB server

The mongoDB stores its database state in a volume, so it persists after container shutdown.

# Starting the apps
First create a volume for mongoDB:
> docker volume create csm_mongodb_vol

Then:
> docker compose up

To connect stdin to the client and interact with the client, then execute
> docker attach csm_client_container

# Building individual images

To build docker images for client and docker, invoke 'docker build' from the clientServerMongo directory.
For example, to build the client image:
> docker build -f dockerFiles/Docker_client .
