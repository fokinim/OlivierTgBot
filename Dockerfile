FROM anapsix/alpine-java
EXPOSE 8080
ENTRYPOINT java -jar /target/OlivierBot-1.0-SNAPSHOT.jar
