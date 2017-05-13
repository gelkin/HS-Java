package space.harbour.hw10;

import space.harbour.hw10.dataset.AssessmentDataSet;
import space.harbour.hw10.dataset.UserDataSet;

import java.util.List;

public interface DBService {
    String getLocalStatus();

    void saveUser(UserDataSet user);

    void saveAssessment(AssessmentDataSet assessment);

    UserDataSet readUser(long id);

    AssessmentDataSet readAssessment(long id);

    void shutdown();
}
