package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dao.MongoConnection;

public class FindAndModify {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings= conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            Document query = new Document("genres", "Drama")
                    .append("directors", "Winchell Smith");

            Document updateOperation = new Document("$inc", new Document("year", 1));

            long modifiedCount = collection.updateMany(query, updateOperation).getModifiedCount();

            System.out.println("Updated year for " + modifiedCount + " movies with genre 'Drama' and director 'Winchell Smith'!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}