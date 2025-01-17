package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class FindBySubdocument {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();


        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            Document query = new Document("runtime", new Document("$gt", 80))
                    .append("awards.nominations", 1);

            try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
                System.out.println("Movies with runtime > 80 and 1 nomination:");
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
