FROM adoptopenjdk/openjdk11:alpine-jre
ADD CARMADA-1.1.6-Release.jar carmada-app.jar
ENTRYPOINT ["java","-jar","/carmada-app.jar"]
EXPOSE 8080
