FROM openjdk:18
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Employee-Service.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","/Employee-Service.jar"]
