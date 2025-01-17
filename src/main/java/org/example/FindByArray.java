package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class FindByArray {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            Document query = new Document("title", "The Italian");

            Document result = collection.find(query).first();

            if (result != null) {
                System.out.println("Found movie: " + result.toJson());
            } else {
                System.out.println("No movie found with title: The Italian");
            }
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}