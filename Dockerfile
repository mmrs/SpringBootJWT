FROM openjdk:8
EXPOSE 8081
ADD target/spring-boot-jwt.jar spring-boot-jwt.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-jwt.jar"]