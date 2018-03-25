FROM jfisbein/alpine-oracle-jdk9
VOLUME /tmp
ADD openutilities-rm-am-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
