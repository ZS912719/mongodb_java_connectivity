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

public class RevertInsertMany {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings = conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");

            Document query = new Document("_id", new Document("$in", Arrays.asList(
                    "unique_id_12346",
                    "unique_id_12347",
                    "unique_id_12348"
            )));

            long deletedCount = collection.deleteMany(query).getDeletedCount();

            System.out.println("Deleted " + deletedCount + " comments!");

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
