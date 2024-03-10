FROM amazoncorretto:21-alpine-jdk
# create a new user to run the application
RUN addgroup -S app && adduser -S app -G app
USER app:app
# copy the packaged jar file into our docker image
COPY target/*.jar app.jar
# set the startup command to execute the jar
ENTRYPOINT ["java","-jar","/app.jar"]