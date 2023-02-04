FROM java:8
WORKDIR /app
COPY greatEngine-assembly-0.1.jar /
CMD java -jar greatEngine-assembly-0.1.jar