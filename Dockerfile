FROM anapsix/alpine-java
EXPOSE 8080
ENTRYPOINT ["java","-jar","https://github.com/fokinim/OlivierTgBot/blob/main/OlivierBot/target/OlivierBot-1.0-SNAPSHOT.jar"]
