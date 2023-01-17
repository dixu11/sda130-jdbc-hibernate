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
    @Cascade(CascadeType.SAVE_UPDATE)
    private CourseDetails courseDetails;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CourseDetails getCourseDetails() {
        return courseDetails;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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
