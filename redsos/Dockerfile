FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS="" \
    SPRING_DATASOURCE_URL="" \
    GOOGLE_APPLICATION_CREDENTIALS=""

# Add a jhipster user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh redsos
WORKDIR /home/redsos

RUN mkdir config
COPY service-account-file.json config
ADD target/*jar com.trinity.dev.redsos-0.0.1-SNAPSHOT.jar

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown redsos:redsos entrypoint.sh

USER redsos

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8082

VOLUME ["/config"]

