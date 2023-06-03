FROM openjdk:17
ARG JAR_FILE=target/maven-guruva.jar
COPY ${JAR_FILE} guruva.jar
ENTRYPOINT ["java","-jar","/guruva.jar"]
