package space.harbour.hw9.dataSets;

public class StudentDataSet {
    private long id;
    private String name;

    public StudentDataSet(long id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "StudentDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
