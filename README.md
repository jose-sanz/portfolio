# Portfolio

It is a simple project to keep track of assets and transactions between them.

## Technology Stack

* [Java 8](http://www.java.com)
* [Maven](http://maven.apache.org)
* [Spring Framework](https://spring.io)
* [Spring Boot](https://projects.spring.io/spring-boot)
* [MongoDB](https://www.mongodb.com)
* [Angular](https://angular.io)

## Build

### Prerequisites

* [Java 8](http://www.java.com)
* [Maven 3.5.0+](http://maven.apache.org)
* [Node.js & npm](https://nodejs.org)
* [Angular CLI](https://github.com/angular/angular-cli)

### Build client
Run Maven in "client" directory:
```
ng build --prod [--base-href /portfolio/]
```

### Build server
Run Maven in "portfolio" directory:
```
mvn clean install
```

## Run

### Client:
Copy the files from "client/dist" to the HTTP Server.

Visit: http://hostname:4200/

### Server:
```
java -Duser.timezone=UTC -Dfile.encoding=UTF-8 -jar portfolio-server-X.Y.Z.jar
```

## Todo

* portfolio-client
* Dynamic collection of the Net Asset Values (NAVs)
* Historical snapshots of the overall portfolio
* ...
