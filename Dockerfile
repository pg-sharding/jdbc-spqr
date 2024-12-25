FROM ubuntu:jammy

WORKDIR /root

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update -qq && apt install -y \
    openjdk-8-jdk maven postgresql-client && \
    apt remove -y openjdk-11-jre-headless

COPY . /root

ENTRYPOINT ["./entrypoint.sh"]
