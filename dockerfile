FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY *.class .
EXPOSE 8080
CMD ["java", "SimpleWebServer"]