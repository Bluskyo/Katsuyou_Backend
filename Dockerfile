FROM eclipse-temurin:23-jdk-alpine
# run mvn clean install -DskipTests to create jar file.
ARG JAR_FILE=target/*.jar
COPY ./target/Katsuyou-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "-D\"spring.profiles.active\"=prod", "/app.jar"]