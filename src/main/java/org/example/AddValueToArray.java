package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class AddValueToArray {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            Document query = new Document("_id", "573a1391f29313caabcd6ea2");

            Document updateOperation = new Document("$addToSet", new Document("genres", "Drama"));

            collection.updateOne(query, updateOperation);

            System.out.println("Genre 'Drama' added successfully!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}