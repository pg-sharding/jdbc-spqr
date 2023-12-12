FROM ubuntu:jammy

WORKDIR /root

RUN apt update -qq && apt install -y --no-install-recommends \
    openjdk-8-jdk maven && \
    apt remove  -y openjdk-11-jre-headless

COPY . /root

RUN mvn dependency:copy-dependencies

ENTRYPOINT ["mvn", "test"]
