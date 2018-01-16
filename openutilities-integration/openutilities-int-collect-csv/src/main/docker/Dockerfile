FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD entrypoint.sh entrypoint.sh
RUN chmod u+x entrypoint.sh
ADD openutilities-int-collect-csv-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
RUN mkdir -p /ou/pre-readings
RUN mkdir -p /ou/readings/input
RUN mkdir -p /ou/readings/output
ENV JAVA_OPTS=""
ENTRYPOINT ["/bin/sh", "/entrypoint.sh"]
