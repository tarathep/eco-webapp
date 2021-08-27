FROM openjdk:8u111-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#docker run -d --name saga-web -p 12300:8080 -e spring.datasource.url=jdbc:mysql://192.168.88.208:3306/sagaeco -e spring.datasource.password= sagaweb:1.0.0
