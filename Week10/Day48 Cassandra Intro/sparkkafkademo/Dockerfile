FROM eclipse-temurin:21-jdk-ubi10-minimal
WORKDIR /app
# Ensure you run 'mvn clean package' before building this image
# The jar name matches the artifactId and version in pom.xml
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]