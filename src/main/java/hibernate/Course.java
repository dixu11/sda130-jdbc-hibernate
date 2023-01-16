package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "course_details_id") //update problem!
    private CourseDetails courseDetails;

    private Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    //no getters and setters?

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseDetails(CourseDetails courseDetails) {
        this.courseDetails = courseDetails;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
