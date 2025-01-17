package org.example.dao;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import io.github.cdimascio.dotenv.Dotenv;

public class MongoConnection {
    public MongoClientSettings getClientSettings() {

        Dotenv env = Dotenv.load();
        String connectionString = env.get("CONNECTION_STRING");

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        assert connectionString != null;

        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
    }
}