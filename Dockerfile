# Use a suitable version of OpenJDK as the base image
FROM openjdk:17-jdk-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the entire project directory into the container
COPY . .

# Build the Maven project (skip tests during build)
RUN mvn clean package -DskipTests

# Create a new stage for the final image
FROM openjdk:17-jdk-slim

# Copy the JAR file from the build stage to the final image
COPY --from=build /app/target/learnsphere-0.0.1-SNAPSHOT.jar /app/learnsphere.jar

# Expose the port that your application listens on (if it's 8080)
EXPOSE 8080

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "/app/learnsphere.jar"]
