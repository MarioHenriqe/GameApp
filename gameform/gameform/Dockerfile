FROM openjdk:11
ADD target/GameApp.jar GameApp.jar
ADD applicationinsights-agent-2.5.1.jar applicationinsights-agent-2.5.1.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "GameApp.jar"]