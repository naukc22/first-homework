FROM openjdk:17

ADD target/*.jar first-homework-1.0-SNAPSHOT.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "first-homework-1.0-SNAPSHOT.jar"]