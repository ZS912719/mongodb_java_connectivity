package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FindByOperator{
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings = conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse("2000-01-01");

            Document query = new Document("released", new Document("$gte", startDate));

            try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
                System.out.println("Movies released after 2000:");
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    System.out.println(doc.toJson());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
