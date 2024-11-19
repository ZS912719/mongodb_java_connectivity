package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class FindOne {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings = conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {

            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");
            database.runCommand(new Document("ping", 1));
            Document document = collection.find().first();
            System.out.println("First document: " + document);
        } catch (MongoException e) {
                e.printStackTrace();
            }
    }
}

