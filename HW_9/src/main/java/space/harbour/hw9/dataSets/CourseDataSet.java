package space.harbour.hw9.dataSets;

public class CourseDataSet {
    private long id;
    private String name;
    private int year;

    public CourseDataSet(long id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "CourseDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
