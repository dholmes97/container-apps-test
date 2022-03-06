FROM openjdk:11 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/redgreen/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
# download dependencies
RUN chmod +x ./gradlew
RUN ./gradlew build -x :bootJar -x test --continue
COPY . .
RUN ./gradlew build

FROM openjdk:11-jre
WORKDIR /root/
COPY --from=BUILD_IMAGE /root/dev/redgreen/build/libs/redgreen-*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]