FROM openjdk:11-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven curl unzip

# Install Gauge
RUN curl -SsL https://downloads.gauge.org/stable | sh
RUN gauge install java && gauge install screenshot

# Set working directory
WORKDIR /app

# Copy application files
COPY . /app

# Copy configuration file
COPY configService.properties /app/configService.properties

# Set environment variables
ENV PATH=$HOME/.gauge:$PATH

# Run tests
CMD ["mvn", "gauge:execute", "-DspecsDir=specs"]
