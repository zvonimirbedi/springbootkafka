# Spring boot kafka publisher - consumer example

### Tech stack:
* Java 11 (tested, also working with 8+)
* Spring Boot
* Gradle

## Local env flow

### docker kafka (goto infra/kafka/)
* docker-compose up
* docker rm schema-registry_container ksqlserver_container kafka_container zookeeper_container kafka-connect_container

### Swagger url
* http://localhost:8080/swagger-ui/

## KSQL FLOW
### enter ksqlserver bash
* docker exec -it ksqlserver_container bash
### enter ksqlserver cli
* /usr/bin/ksql http://localhost:8088
### listen for live topic (consume)
* SHOW TOPICS;
* PRINT 'messagestopic';
### Create stream from topic
* CREATE STREAM MESSAGESSTREAM (id BIGINT, message STRING) WITH (KAFKA_TOPIC='messagestopic', VALUE_FORMAT='JSON', KEY = 'id' );
* SELECT * FROM MESSAGESSTREAM WHERE ID>0 EMIT CHANGES;
