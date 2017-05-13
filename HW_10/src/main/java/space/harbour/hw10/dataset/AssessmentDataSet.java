package space.harbour.hw10.dataset;

import space.harbour.hw10.MyStatus;
import javax.persistence.*;

@Entity
@Table(name = "assessments")
public class AssessmentDataSet {
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserDataSet user;

    @Column(length = 11, nullable = false)
    private long grade;

    @Column
    @Enumerated(EnumType.STRING)
    private MyStatus status;

    public AssessmentDataSet() {}

    public AssessmentDataSet(long id, UserDataSet user, long grade, MyStatus status) {
        this.id = id;
        setUser(user);
        this.grade = grade;
        this.status = status;
    }

    public AssessmentDataSet(UserDataSet user, long grade, MyStatus status) {
        setUser(user);
        this.grade = grade;
        this.status = status;
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
        if (!user.getAssesments().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            user.getAssesments().add(this);
        }
    }

    @Override
    public String toString() {
        return "AssessmentDataSet{" +
                "id=" + id +
                ", user_id=" + user.getId() +
                ", grade=" + grade +
                ", status=" + status +
                '}';
    }
}
