package problem1_task1;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class NewsMongoDBConnection {
    public MongoClient getConnection() {
        String MONGO_CONNECTION_URI = "mongodb+srv://purvisha99:purvisha99@mymongonews.eqq5b.mongodb.net/test?retryWrites=true&w=majority";
        try {
            return MongoClients.create(MONGO_CONNECTION_URI);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
