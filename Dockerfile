# Start with a base image containing Java runtime
FROM eclipse-temurin:11

# Add Maintainer Info
LABEL maintainer="derlisarguello01@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 9000
EXPOSE 9000

# The application's jar file
ARG JAR_FILE=target/superheroes-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} superheroes.jar

# Run the jar file
ENTRYPOINT ["java", "-Dspring.profiles.active=default", "-jar", "/superheroes.jar"]
