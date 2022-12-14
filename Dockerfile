FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE=build/libs/PostMelon-0.0.1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9090
ARG USE_PROFILE
ENV USE_PROFILE=$USE_PROFILE
ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar","/app.jar"]