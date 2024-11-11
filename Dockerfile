# Build Stage
FROM maven:3.8.6-openjdk-22 AS build

# Set the working directory in the container
WORKDIR /app

# Clone the Git repository
# Replace with your repository's URL (HTTPS or SSH if keys are configured)
ARG REPO_URL
RUN git clone ${'https://github.com/albe194e/SpisOpBackend'} .

# Optionally, check out a specific branch
ARG BRANCH=main
RUN git checkout ${BRANCH}

# Build the application
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:22-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
