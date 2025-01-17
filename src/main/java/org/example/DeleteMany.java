package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class DeleteMany {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");

            Document query = new Document("name", "Yolanda Owen");

            long deletedCount = collection.deleteMany(query).getDeletedCount();

            System.out.println("Deleted " + deletedCount + " comments by Yolanda Owen!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}