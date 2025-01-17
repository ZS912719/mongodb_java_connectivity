package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class ReplaceOne {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings = conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");

            Document query = new Document("_id", "5a9427648b0beebeb694499");

            Document newDocument = new Document("_id", "5a9427648b0beebeb694499")
                    .append("name", "New User")
                    .append("email", "new_user@fakeemail.com")
                    .append("movie_id", "573a1390f29313caabcd4ebf")
                    .append("text", "This comment has been replaced!")
                    .append("date", new Document("$date", "2025-01-17T12:00:00.000Z"));

            collection.replaceOne(query, newDocument);

            System.out.println("Document replaced successfully!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
