# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file
COPY target/tracking-number-generate-app-1.0.0.jar app.jar

EXPOSE 8001

# Run the application
CMD ["java", "-jar", "app.jar"]
