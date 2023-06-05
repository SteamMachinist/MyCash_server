FROM gradle:7.6.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/mycash-application.jar

WORKDIR /app
COPY src/main/resources /app/resources

ENTRYPOINT ["java","-jar","/app/mycash-application.jar"]