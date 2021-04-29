# the first stage of our build will use a maven 3.6.1 parent image
FROM maven:3.6.1-jdk-13-alpine AS MAVEN_BUILD

# copy the pom and src code to the container
COPY src /home/app/src
COPY pom.xml /home/app

# package our application code
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# the second stage of our build will use open jdk 8 on alpine 3.9
FROM openjdk:13-alpine

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /home/app/target/*.jar /app.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/app.jar"]