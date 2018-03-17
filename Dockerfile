FROM java:openjdk-8
RUN mkdir app
ADD target/entity-retriever-0.0.1-SNAPSHOT.jar app/
ENTRYPOINT java -jar app/entity-retriever-0.0.1-SNAPSHOT.jar