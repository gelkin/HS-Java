package space.harbour.day11;

import org.bson.types.ObjectId;
import space.harbour.day11.dataset.AssessmentDataSet;
import space.harbour.day11.dataset.UserDataSet;

public class Main {

    public static void main(String[] args) {
        //org.apache.log4j.BasicConfigurator.configure();

        DBService service = new MyDBService();
        example(service);
    }

    private static void example(DBService service) {
        // create users
        UserDataSet userFirst = new UserDataSet("ivan@mail.com", "ivan");
        UserDataSet userSecond = new UserDataSet("petr@mail.com", "petr");

        // create assessments
        AssessmentDataSet assessmentFirst = new AssessmentDataSet(100);
        AssessmentDataSet assessmentSecond = new AssessmentDataSet(60);
        AssessmentDataSet assessmentThird = new AssessmentDataSet(80);

        userFirst.addAssessment(assessmentFirst);
        userFirst.addAssessment(assessmentSecond);
        userSecond.addAssessment(assessmentThird);

        // add users
        ObjectId userFirstId = service.addUser(userFirst);
        ObjectId userSecondId = service.addUser(userSecond);

        service.printUser(userFirstId);
        service.printUser(userSecondId);
    }
}
