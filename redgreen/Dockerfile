FROM openjdk:11 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/redgreen/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
# download dependencies
RUN ./gradlew build -x :bootJar -x test --continue
COPY . .
RUN ./gradlew build

FROM openjdk:11-jre
WORKDIR /root/
ADD app-insights-agent/ /app/
COPY --from=BUILD_IMAGE /root/dev/redgreen/build/libs/redgreen-*.jar /app/redgreen.jar
EXPOSE 8081
ENTRYPOINT [ "java", "-javaagent:/app/applicationinsights-agent-3.2.7.jar", "-jar", "/app/redgreen.jar"]