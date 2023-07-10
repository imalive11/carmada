FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/CARMADA-1.1.3-SNAPSHOT.jar carmada-app.jar
ENTRYPOINT ["java","-jar","/carmada-app.jar"]
EXPOSE 8080