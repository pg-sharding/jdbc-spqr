FROM ubuntu:jammy

WORKDIR /root

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update && apt install -y ca-certificates
RUN sed -i 's/archive.ubuntu.com/mirror.yandex.ru/g' /etc/apt/sources.list
RUN mkdir -p /use/share/binfmts
RUN apt update && apt install -y --no-install-recommends openjdk-11-jre-headless

RUN apt update && apt install -y --no-install-recommends \
    openjdk-8-jdk maven postgresql-client && \
    apt remove -y openjdk-11-jre-headless

COPY . /root

ENTRYPOINT ["./entrypoint.sh"]
