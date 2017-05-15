package space.harbour.day11.dataset;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDataSet {
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 64, unique = true)
    private String email;

    @Column(length = 32)
    private String name;

    @OneToMany(mappedBy="user")
    private List<AssessmentDataSet> assesments = new ArrayList<>();
    // Important for Hibernate
    public UserDataSet() { }


    public UserDataSet(long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public UserDataSet(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void addAssessment(AssessmentDataSet assessment) {
        this.assesments.add(assessment);
    }

    public List<AssessmentDataSet> getAssesments() {
        return assesments;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", assesments=" + assesments +
                '}';
    }
}
