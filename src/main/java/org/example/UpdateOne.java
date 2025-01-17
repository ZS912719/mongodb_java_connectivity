package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class UpdateOne {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings = conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");

            Document query = new Document("_id", "5a9427648b0beebeb694499");

            Document updateFields = new Document()
                    .append("name", "Updated User")
                    .append("text", "This comment has been updated!");
            Document updateOperation = new Document("$set", updateFields);

            collection.updateOne(query, updateOperation);

            System.out.println("Document updated successfully!");

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
