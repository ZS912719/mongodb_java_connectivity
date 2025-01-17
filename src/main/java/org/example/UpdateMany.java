package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

import java.util.Arrays;

public class UpdateMany {
        public static void main(String[] args) {
            MongoConnection conn = new MongoConnection();
            MongoClientSettings settings = conn.getClientSettings();

            try (MongoClient client = MongoClients.create(settings)) {
                MongoDatabase database = client.getDatabase("sample_mflix");
                MongoCollection<Document> collection = database.getCollection("comments");

                Document query = new Document("name", new Document("$in", Arrays.asList("Thomas Morris", "Yolanda Owen")));

                Document updateFields = new Document("text", "This comment has been updated via updateMany.");
                Document updateOperation = new Document("$set", updateFields);

                collection.updateMany(query, updateOperation);

                System.out.println("Comments updated successfully!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }