# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy only the necessary files needed for dependency resolution
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN apt-get update \
    && apt-get install -y maven \
    && mvn clean install \
    && apt-get remove -y maven \
    && apt-get autoremove -y \
    && rm -rf /var/lib/apt/lists/* /root/.m2

# Copy the application JAR after the dependencies have been resolved
COPY target/achat-1.0.jar /app/target/achat-1.0.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application when the container launches
CMD ["java", "-jar", "target/achat-1.0.jar"]
