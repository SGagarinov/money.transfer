FROM openjdk:17-oracle

EXPOSE 5500

VOLUME /tmp
COPY target/*.jar transfer.jar
ENTRYPOINT ["java","-jar","/transfer.jar"]
