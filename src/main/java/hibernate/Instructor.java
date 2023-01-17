package hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "instructor")
    private List<Course> courses = new ArrayList<>();

    public Instructor(String name) {
        this.name = name;
    }

    Instructor() {
    }



    public void addCourse(Course course) {
        courses.add(course);
        course.setInstructor(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
