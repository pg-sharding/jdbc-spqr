FROM ubuntu:jammy

WORKDIR /root

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update && apt install -y ca-certificates
RUN sed -i 's/archive.ubuntu.com/mirror.yandex.ru/g' /etc/apt/sources.list

RUN apt update -qq -o Acquire::AllowInsecureRepositories=true && apt install -y --no-install-recommends --allow-unauthenticated \
    openjdk-8-jdk maven postgresql-client && \
    apt remove -y openjdk-11-jre-headless

COPY . /root

ENTRYPOINT ["./entrypoint.sh"]
