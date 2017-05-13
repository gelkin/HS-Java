package space.harbour.hw10.dao;

import org.hibernate.Session;
import space.harbour.hw10.dataset.AssessmentDataSet;


public class AssessmentDataSetDAO {
    private Session session;

    public AssessmentDataSetDAO(Session session) {
        this.session = session;
    }

    public void save(AssessmentDataSet assessment) {
        session.save(assessment);
        session.close();
    }

    public AssessmentDataSet read(long id) {
        return session.load(AssessmentDataSet.class, id);
    }
}
