FROM openjdk:8
EXPOSE 8081
COPY target/*.jar spring-boot-jwt.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-jwt.jar"]