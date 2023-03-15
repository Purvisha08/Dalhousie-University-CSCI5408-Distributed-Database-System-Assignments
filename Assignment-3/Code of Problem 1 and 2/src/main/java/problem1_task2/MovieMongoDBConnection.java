package problem1_task2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MovieMongoDBConnection {
    public MongoClient getConnection() {
        String MONGO_CONNECTION_URI = "mongodb+srv://purvisha99:purvisha99@mymongomoviedb.9qmr0vm.mongodb.net/test?retryWrites=true&w=majority";
        try {
            return MongoClients.create(MONGO_CONNECTION_URI);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
