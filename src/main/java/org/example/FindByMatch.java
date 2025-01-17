package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;
import org.example.dao.MongoConnection;

import java.util.Arrays;

public class FindByMatch {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            Document query = new Document("genres", new Document("$in", Arrays.asList("Comedy", "Short")));

            try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
                System.out.println("Movies with genres 'Comedy' or 'Short':");
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    System.out.println(doc.toJson());
                }
            }
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}