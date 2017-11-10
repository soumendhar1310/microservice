FROM java:8
VOLUME /tmp
ADD target/sample1-0.0.1-SNAPSHOT.jar sampleservice.jar
RUN bash -c 'touch /sampleservice.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /sampleservice.jar --spring.config.location=/deployments/config/application.properties"]