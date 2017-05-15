package space.harbour.day11;

import org.bson.types.ObjectId;
import space.harbour.day11.dataset.AssessmentDataSet;
import space.harbour.day11.dataset.UserDataSet;

public interface DBService {
    ObjectId addUser(UserDataSet user);

    void printUser(ObjectId id);

    void shutdown();
}
