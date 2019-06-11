FROM openjdk:8-jre-alpine

RUN mkdir -p /opt/testrest
RUN mkdir -p /opt/testrest/config

COPY test-rest-0.0.1-SNAPSHOT.jar /opt/testrest
COPY application.yml /opt/testrest/config

WORKDIR /opt/testrest

EXPOSE 8081

CMD java -jar -Dspring.config.location=/opt/testrest/config/application.yml test-rest-0.0.1-SNAPSHOT.jar

# docker build -f Dockerfile -t testrest .
# docker images
# docker run -p 8081:8081 testrest
# docker ps
# docker stop testresst
# docker rm testrest
# docker container ls -a
# docker container rm 913db1f146d7
