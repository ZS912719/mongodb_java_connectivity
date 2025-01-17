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

public class FindAnd {
    public static void main(String[] args) {
        MongoConnection conn = new MongoConnection();
        MongoClientSettings settings = conn.getClientSettings();

        try (MongoClient client = MongoClients.create(settings)) {
            MongoDatabase database = client.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            Document query = new Document("$and", Arrays.asList(
                    new Document("genres", "Short"),
                    new Document("genres", "Animation")
            ));

            Document result = collection.find(query).first();

            if (result != null) {
                System.out.println("Found movie: " + result.toJson());
            } else {
                System.out.println("No movies found with genres 'Short' and 'Animation'.");
            }

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
