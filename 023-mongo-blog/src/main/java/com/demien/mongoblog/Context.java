package com.demien.mongoblog;

import com.demien.mongoblog.dao.SessionDAO;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Created by demien on 1/31/16.
 */
public class Context {
    private final String MONGO_URL="mongodb://localhost";
    private SessionDAO sessionDAO;

    public Context() {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URL));
        final MongoDatabase blogDatabase = mongoClient.getDatabase("blog");
        sessionDAO = new SessionDAO(blogDatabase);
    }

    public SessionDAO getSessionDAO() {
        return sessionDAO;
    }
}
