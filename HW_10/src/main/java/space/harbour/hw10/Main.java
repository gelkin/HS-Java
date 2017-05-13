package space.harbour.hw10;

import space.harbour.hw10.dataset.AssessmentDataSet;
import space.harbour.hw10.dataset.UserDataSet;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new MyDBService();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        // create users
        UserDataSet userFirst = new UserDataSet("ivan@mail.com", "ivan", "Moscow", new Date(), false);
        UserDataSet userSecond = new UserDataSet("petr@mail.com", "petr", "SPb", new Date(), true);

        // create assessments
        AssessmentDataSet assessmentFirst = new AssessmentDataSet(userFirst, 100, MyStatus.OK);
        AssessmentDataSet assessmentSecond = new AssessmentDataSet(userFirst, 60, MyStatus.RUNNING);
        AssessmentDataSet assessmentThird = new AssessmentDataSet(userSecond, 80, MyStatus.OK);

        // save
        dbService.saveUser(userFirst);
        dbService.saveUser(userSecond);
        dbService.saveAssessment(assessmentFirst);
        dbService.saveAssessment(assessmentSecond);
        dbService.saveAssessment(assessmentThird);

        // read
        System.out.println("\n\n####################\n");
        System.out.println("Read users:");
        UserDataSet user = dbService.readUser(1);
        System.out.println(user);
        user = dbService.readUser(2);
        System.out.println(user);

        System.out.println("\n\n####################\n");
        System.out.println("Read assessments:");
        AssessmentDataSet assessment = dbService.readAssessment(1);
        System.out.println(assessment);
        assessment = dbService.readAssessment(2);
        System.out.println(assessment);
        assessment = dbService.readAssessment(3);
        System.out.println(assessment);

        System.out.println("\n");


        dbService.shutdown();
    }
}
