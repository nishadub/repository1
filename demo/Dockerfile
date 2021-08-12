FROM openjdk:8-jdk-alpine
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=test", "target/app.jar"]
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
