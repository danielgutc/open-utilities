FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD openutilities-int-collect-csv-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
RUN mkdir -p /tmp/readings/input
RUN mkdir -p /tmp/readings/output
ENV JAVA_OPTS=""
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
