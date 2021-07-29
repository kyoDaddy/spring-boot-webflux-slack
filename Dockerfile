# https://hub.docker.com/_/openjdk
# 일반적으로 container image로 만들 떄 활용하는 Dockerfile 사용법
FROM openjdk:11-jre-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT java $JAVA_OPTS -jar /app.jar

# 도커파일 위치에서 실행 : docker build -t spring-boot-webflux-slack .
# 원하는 스프링 프로파일로 이미지 실행 : docker run -e "SPRING_PROFILES_ACTIVE=dev" -d -p 8080:8080 --name spring-boot-webflux-slack spring-boot-webflux-slack
# local 에 도커를 올렸을때 다른 도커 이미지로 띄운 db에 접근이 안될 경우 : docker inspect postgres 로 해당 이미지의 IP를 찾아서 스프링 프로파일 별로 IP를 나눠서 실행함