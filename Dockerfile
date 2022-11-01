FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE=build/libs/PostMelon-0.0.1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/app.jar"]