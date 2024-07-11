# This is a multi-stage build. The first stage is used to build our Spring Boot application.

# Use the official maven/Java 8 image to create a build artifact.
FROM maven:3.9-eclipse-temurin-22-alpine AS build

# Copy local code to the container image.
WORKDIR /build
COPY . .

# Package the application
RUN mvn clean package

# Use Eclipse Temurin (formerly AdoptOpenJDK) as the base image for the final image
FROM eclipse-temurin:22-alpine

# Copy the jar to the production image from the builder stage.
COPY --from=build /build/target/*.jar /app.jar

# Run the web service on container startup.
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
