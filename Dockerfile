FROM openjdk:11
COPY ./java/progresssoft-assignment/build/libs/progresssoft-assignment-1.0-SNAPSHOT-all.jar /usr/jar/
WORKDIR /usr/jar
ENTRYPOINT ["java","-jar","progresssoft-assignment-1.0-SNAPSHOT-all.jar"]
