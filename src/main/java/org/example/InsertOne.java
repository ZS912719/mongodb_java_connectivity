package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

import java.util.Date;

public class InsertOne {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");
            Document comment = new Document("_id", "5a9427648b0beebeb694499")
                    .append("name", "Lulu Toto")
                    .append("email", "lulu_toto@fakeemail.com")
                    .append("movie_id", "573a1390f29313caabcd4eaf")
                    .append("text", "This is a new comment inserted via Java!")
                    .append("date", new Date());
            collection.insertOne(comment);
            System.out.println("New document inserted successfully: " + comment);
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
