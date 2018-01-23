#!/bin/sh
java -Djava.security.egd=file:/dev/./urandom -Duser.timezone=UTC -Dfile.encoding=UTF-8 -jar target/portfolio-server-0.0.1-SNAPSHOT.jar
