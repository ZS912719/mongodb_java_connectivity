package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class FindFirst10 {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");

            try (MongoCursor<Document> cursor = collection.find().limit(10).iterator()) {
                System.out.println("The first 10 documents:\n");
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }
            }
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
