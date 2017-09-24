FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD @project.build.finalName@.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
