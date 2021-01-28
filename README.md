## myRetail

This application provides an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
This application has been developed simulating the microservices architecture using Spring framework, Java and MongoDB. Although the solution to application is unified, there are different approaches in launching the application as mentioned below:

# Pre-requisites for Approaches 1 and 2

```
Java 1.8
Spring Boot Version 2.4.2
MongoDb Database
Eclipse (or any IDE for Java)
Postman (or any Web Services Testing Engine)
Target Service: https://redsky.target.com/v3/pdp/tcin/13860428excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate#_blank

```
Case study assumes there is an entry of product id in database for successful demonstration.

## Approach 1 (Direct Application Launch)


# Setup:

```
Clone the project and import project folder(Product) into Eclipse and run as Java application.

Or

Go to https://github.com/akhileshmusuku/myRetail/tree/main/jars and download product-0.0.1-SNAPSHOT.jar
```

# Launch the jar file from command prompt using command:

```
java -jar product-0.0.1-SNAPSHOT.jar
```

## Approach 2 (Scalable application using Eureka service discovery)

# Setup:

Clone the project and import three project folders (Product, discovery-server, client-application)  into Eclipse and run all of them as Java applications.

Or

Go to https://github.com/akhileshmusuku/myRetail/tree/main/jars and download product-0.0.1-SNAPSHOT.jar, client-application-0.0.1-SNAPSHOT.jar, discovery-server-0.0.1-SNAPSHOT.jar

Launch the jar files from command prompt using command line:

```
java -jar product-0.0.1-SNAPSHOT.jar
java -jar client-application-0.0.1-SNAPSHOT.jar
java -jar discovery-server-0.0.1-SNAPSHOT.jar
```


To scale the product application, Launch another instance of product application on a different port using below:

```
java -jar -Dserver.port={any unused port} product-0.0.1-SNAPSHOT.jar
```


## Approach 3 (Application on Docker Environment)

# Prerequisite:

```
Docker
```

# Setup:

Clone the project and import project folder(Product).

Launch command line from root directory of project, Product. Execute the below commands to launch the application in Docker environment:

```
1. docker build -t productapp .
2. docker-compose up -d
```


## Sample Request and Response

```
Request method : GET
Response body:
{
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 13.80,
        "currency_code": "USD"
    }
}
```

```
Request method : PUT
Request body:
{
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 13.80,
        "currency_code": "USD"
    }
}
Response body:
{
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 13.80,
        "currency_code": "USD"
    }
}
```













