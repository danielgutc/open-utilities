FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD openutilities-int-coll-csv-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
