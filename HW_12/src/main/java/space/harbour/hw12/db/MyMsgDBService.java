package space.harbour.hw12.db;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import space.harbour.hw12.db.dataset.ChatMsgDataSet;

public class MyMsgDBService implements MsgDBService {
    private final MongoClient mongo;
    private final MongoDatabase mongoDatabase;
    private static final String USERS_TABLE = "messages";

    public MyMsgDBService() {
        mongo = new MongoClient("localhost", 27017);
        mongoDatabase = mongo.getDatabase("testdb"); //create if not exist
    }

    public ObjectId addMsg(ChatMsgDataSet msg) {
        Document document = new Document("login", msg.getLogin()).append("msg", msg.getMsg());
        return insert(USERS_TABLE, document);
    }

    public FindIterable<Document> getMsgById(ObjectId id) {
        return getById(USERS_TABLE, id);
    }

    private FindIterable<Document> getById(String collectionName, ObjectId id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", id);
        return collection.find(searchQuery);
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
