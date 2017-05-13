package space.harbour.hw10.dataset;

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

    @Column(length = 32)
    private String city;

    @Column
    private Date regtime;

    @Column
    private boolean is_tester;

    @OneToMany(mappedBy="user")
    private List<AssessmentDataSet> assesments = new ArrayList<>();

    // Important for Hibernate
    public UserDataSet() { }


    public UserDataSet(long id, String email, String name, String city, Date regtime, boolean is_tester) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.city = city;
        this.regtime = regtime;
        this.is_tester = is_tester;
    }

    public UserDataSet(String email, String name, String city, Date regtime, boolean is_tester) {
        this.email = email;
        this.name = name;
        this.city = city;
        this.regtime = regtime;
        this.is_tester = is_tester;
    }

    public void addAssessment(AssessmentDataSet assessment) {
        this.assesments.add(assessment);
        if (assessment.getUser() != this) {
            assessment.setUser(this);
        }
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

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public void setIs_tester(boolean is_tester) {
        this.is_tester = is_tester;
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

    public String getCity() {
        return city;
    }

    public Date getRegtime() {
        return regtime;
    }

    public boolean is_tester() {
        return is_tester;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", regtime=" + regtime +
                ", is_tester=" + is_tester +
                ", assesments=" + assesments +
                '}';
    }
}
