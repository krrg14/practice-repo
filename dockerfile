FROM openjdk:17
COPY . .
RUN javec SimpleWebServer.java
EXPOSE 8080
ENTRYPOINT ["java", "SimpleWebServer"]