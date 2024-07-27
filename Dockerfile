FROM ubuntu:latest
LABEL authors="alierendasdemir"

RUN apt-get update && apt-get install -y \
    curl \
    zip \
    apt-transport-https \
    gnupg2 \
    ca-certificates \
    maven \
    wget \
    openjdk-11-jdk

RUN curl -SsL https://downloads.gauge.org/stable | sh

RUN gauge install java && \
    gauge install screenshot

WORKDIR /app
COPY . /app

COPY gauge.properties /app/gauge.properties

# Config
COPY configService.properties /app/configService.properties

# Environment variables
ENV PATH=$HOME/.gauge:$PATH

CMD ["mvn", "gauge:execute", "-DspecsDir=specs"]