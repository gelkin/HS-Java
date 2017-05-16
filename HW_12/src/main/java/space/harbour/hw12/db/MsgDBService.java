package space.harbour.hw12.db;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import space.harbour.hw12.db.dataset.ChatMsgDataSet;

public interface MsgDBService {
    ObjectId addMsg(ChatMsgDataSet msg);
    FindIterable<Document> getMsgById(ObjectId id);
    void shutdown();
}
