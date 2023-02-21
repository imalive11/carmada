FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/CARMADA-0.0.2-SNAPSHOT.jar carmada-app.jar
ENTRYPOINT ["java","-jar","/carmada-app.jar"]
EXPOSE 8080