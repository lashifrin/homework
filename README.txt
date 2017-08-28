This simple service built with springboot, using spring data-jpa and spring web-mvc. Persistence managed by in-memory h2 database. Sample data get loaded during initializing.
To run:
    unzip crudwebervice.zip
    java -jar crudwebservice-0.0.1-SNAPSHOT.jar

Rest end points:
GET: http://localhost:8080/products/all
GET: http://localhost:8080/products/1
POST: http://localhost:8080/products/create with body: {"name":"device1", "description": "sample description"}
PUT: http://localhost:8080/products/3 with body: {"name":"device1", "description": "description update"}
DELETE: http://localhost:8080/products/1

