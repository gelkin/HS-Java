package space.harbour.day11;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hibernate.cfg.Configuration;
import space.harbour.day11.dataset.AssessmentDataSet;
import space.harbour.day11.dataset.UserDataSet;

public class MyDBService implements DBService {
    private final MongoClient mongo;
    private final MongoDatabase mongoDatabase;
    private static final String USERS_TABLE = "users";

    public MyDBService() {
        mongo = new MongoClient("localhost", 27017);
        mongoDatabase = mongo.getDatabase("testdb"); //create if not exist
    }

    public ObjectId addUser(UserDataSet user) {
        Document document = new Document("email", user.getEmail()).append("name", user.getName());
        StringBuilder sb = new StringBuilder();
        for (AssessmentDataSet assessment: user.getAssesments()) {
            sb.append(assessment.toString() + " ");
        }
        document.append("assessments", sb.toString());
        return insert(USERS_TABLE, document);
    }

    public void printUser(ObjectId id) {
        printById(USERS_TABLE, id);
    }

    private void printById(String collectionName, ObjectId id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", id);
        FindIterable<Document> documents = collection.find(searchQuery);
        for (Document document : documents) {
            System.out.println(document);
        }
    }

    private void printUserByName(String userName) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(USERS_TABLE);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", userName);
        FindIterable<Document> documents = collection.find(searchQuery);
        for (Document document : documents) {
            System.out.println(document);
        }
    }

    private ObjectId insert(String collectionName, Document doc) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName); //create if not exist
        collection.insertOne(doc);
        return (ObjectId) doc.get("_id");
    }

    public void shutdown() {
        mongo.close();
    }

}
