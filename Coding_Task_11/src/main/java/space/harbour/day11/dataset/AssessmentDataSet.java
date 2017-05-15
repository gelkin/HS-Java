package space.harbour.day11.dataset;

import javax.persistence.*;

@Entity
@Table(name = "assessments")
public class AssessmentDataSet {
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 11, nullable = false)
    private long grade;

    public AssessmentDataSet() {}

    public AssessmentDataSet(long id, long grade) {
        this.id = id;
        this.grade = grade;
    }

    public AssessmentDataSet(long grade) {
        this.grade = grade;
    }

    public long getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "AssessmentDataSet{" +
                "grade=" + grade +
                '}';
    }
}
