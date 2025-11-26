FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar categoria-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "categoria-service.jar"]
