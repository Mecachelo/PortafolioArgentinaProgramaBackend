FROM amazoncorretto:11-alpine-jdk
MAINTAINER MEM
COPY target/mem-0.0.1-SNAPSHOT.jar app-portfolio-mem.jar
ENTRYPOINT ["java","-jar","/app-portfolio-mem.jar"]
EXPOSE 8080