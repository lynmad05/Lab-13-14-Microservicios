FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar producto-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "producto-service.jar"]
