FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} notification-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/notification-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8110