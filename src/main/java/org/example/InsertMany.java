package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsertMany {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");
            List<Document> comments = new ArrayList<>();
            comments.add(new Document("_id", "unique_id_12346")
                    .append("name", "Jane Smith")
                    .append("email", "jane_smith@fakeemail.com")
                    .append("movie_id", "573a1390f29313caabcd4eaf")
                    .append("text", "Great movie, I loved it!")
                    .append("date", new Date()));
            comments.add(new Document("_id", "unique_id_12347")
                    .append("name", "Michael Johnson")
                    .append("email", "michael_johnson@fakeemail.com")
                    .append("movie_id", "573a1390f29313caabcd4eaf")
                    .append("text", "The acting was fantastic.")
                    .append("date", new Date()));
            comments.add(new Document("_id", "unique_id_12348")
                    .append("name", "Alice Brown")
                    .append("email", "alice_brown@fakeemail.com")
                    .append("movie_id", "573a1390f29313caabcd4eaf")
                    .append("text", "Not my favorite, but still good.")
                    .append("date", new Date()));
            collection.insertMany(comments);
            System.out.println("Documents inserted successfully!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}