package hibernate;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;


@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_details_id") //update problem!
    @Cascade(CascadeType.ALL)
    private CourseDetails courseDetails;

    private Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course( String name, CourseDetails courseDetails) {
        this.name = name;
        this.courseDetails = courseDetails;
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

    public CourseDetails getDetails() {
        return courseDetails;
    }
}
