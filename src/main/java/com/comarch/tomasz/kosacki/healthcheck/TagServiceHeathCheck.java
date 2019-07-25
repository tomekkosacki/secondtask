package com.comarch.tomasz.kosacki.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagServiceHeathCheck extends HealthCheck {

    private MongoClient mongoClient;
    private Logger log = LoggerFactory.getLogger(getClass());

    public TagServiceHeathCheck(MongoClient mongoClient) {

        this.mongoClient = mongoClient;
    }

    @Override
    protected Result check() throws Exception {
        MongoDatabase database = mongoClient.getDatabase("morphia_user");
        try {
            log.info("Get serviceHealthCheck");
            database.runCommand(new Document("serverStatus", 1));
        } catch (Exception ex) {
            log.error("No connection to data base");
            return Result.unhealthy("No connection to data base");
        }

        return Result.healthy();
    }
}
