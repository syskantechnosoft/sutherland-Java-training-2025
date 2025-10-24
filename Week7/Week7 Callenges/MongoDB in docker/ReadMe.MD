To run MongoDB in Docker, you can use the command-line interface (CLI) for a single container or Docker Compose for more complex applications. Using Docker is especially beneficial for development and testing, as it creates a consistent and portable database environment. 

Prerequisites

You must have Docker installed on your system. You can verify this by running docker --version in your terminal. 

Method 1: Use docker run for a basic container

This method is suitable for quickly starting a single MongoDB instance for development or testing. 

1\. Create a Docker volume for data persistence

By default, data inside a container is deleted when the container is removed. To save your data permanently, create a named volume. 

sh

docker volume create mongo\_data

Use code with caution.



2\. Run the MongoDB container

Use the docker run command to create and start the container in the background. 

sh

docker run --name mongodb-container -d -p 27017:27017 -v mongo\_data:/data/db mongo

Use code with caution.



--name mongodb-container: Assigns a name to your container for easy reference.

-d: Runs the container in detached (background) mode.

-p 27017:27017: Maps port 27017 from the host machine to port 27017 in the container, allowing external connections.

-v mongo\_data:/data/db: Mounts the named volume mongo\_data to the default MongoDB data directory /data/db inside the container.

mongo: Specifies that you want to use the official MongoDB image from Docker Hub. 

3\. Verify the container is running

Check the status of your Docker containers to confirm MongoDB is active. 

sh

docker ps

Use code with caution.



4\. Access the MongoDB shell

To interact with your running database, execute the mongosh command inside the container. 

sh

docker exec -it mongodb-container mongosh

Use code with caution.



Method 2: Use Docker Compose for multiple services

Docker Compose is ideal for defining and running multi-container applications, such as a backend server and a database. This is the best approach for managing an application with persistent storage and a secure setup. 

1\. Create a docker-compose.yml file

Create a file named docker-compose.yml in your project directory and add the following content. This configuration sets up a secure MongoDB container with a persistent volume. 

yaml

version: '3.8'



services:

&nbsp; mongodb:

&nbsp;   image: mongo:latest

&nbsp;   container\_name: mongodb-container

&nbsp;   ports:

&nbsp;     - "27017:27017"

&nbsp;   environment:

&nbsp;     - MONGO\_INITDB\_ROOT\_USERNAME=admin

&nbsp;     - MONGO\_INITDB\_ROOT\_PASSWORD=password

&nbsp;   volumes:

&nbsp;     - mongodb-data:/data/db

&nbsp;   restart: unless-stopped



volumes:

&nbsp; mongodb-data:

Use code with caution.



image: mongo:latest: Specifies the image to use.

container\_name: mongodb-container: Sets a friendly name for the container.

ports: Exposes MongoDB to your host machine.

environment: Sets the root username and password for your database, a crucial step for a secure setup.

volumes: Defines a named volume for persistent storage.

restart: unless-stopped: Ensures the container restarts automatically if it crashes. 

2\. Start your services

From the same directory as your docker-compose.yml file, run the following command to start MongoDB in the background. 

sh

docker compose up -d

Use code with caution.



3\. Access the MongoDB shell

Connect to the database using the MongoDB shell with the username and password you configured. 

sh

docker exec -it mongodb-container mongosh -u admin -p password --authenticationDatabase admin

Use code with caution.



AI can make mistakes, so double-check responses

