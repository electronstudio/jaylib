FROM gradle:6.7-jdk11

# Configure the workspace
RUN mkdir /jaylib
WORKDIR /jaylib

# Install Linux compile tools
RUN apt-get update -y
RUN apt-get install build-essential -y
RUN apt-get install mingw-w64 -y
RUN apt-get install libx11-dev -y

# Notes:
# docker build -t jaylib_toolchain .
# docker run -v "$PWD":/jaylib jaylib_toolchain ./gradlew build