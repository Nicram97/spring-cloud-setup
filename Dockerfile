FROM openjdk:17

WORKDIR /app

COPY build/libs/*.jar spring-cloud-setup.jar
COPY build/resources resources

EXPOSE 8080

CMD ["java", "-jar", "spring-cloud-setup.jar"]