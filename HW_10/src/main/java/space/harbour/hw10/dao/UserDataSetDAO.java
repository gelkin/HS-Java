package space.harbour.hw10.dao;

import org.hibernate.Session;
import space.harbour.hw10.dataset.UserDataSet;

public class UserDataSetDAO {
    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public void save(UserDataSet user) {
        session.save(user);
        session.close();
    }

    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }
}
