FROM maven:3.5.2-jdk-8-alpine AS MVN_M2
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
RUN ls /build/target/
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MVN_M2 /build/target/projectDemoUser-0.0.1-SNAPSHOT /app/
ENTRYPOINT ["java", "-jar", "projectDemoUser-0.0.1-SNAPSHOT"]
