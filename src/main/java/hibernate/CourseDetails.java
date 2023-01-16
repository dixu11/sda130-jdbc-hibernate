package hibernate;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "course_details")
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_description")
    private String fullDescription;
    @OneToOne(mappedBy = "courseDetails")
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.DETACH, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.REFRESH})
    private Course course;

    CourseDetails() {
    }

    public CourseDetails(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseDetails{" +
                "id=" + id +
                ", fullDescription='" + fullDescription + '\'' +
                '}';
    }
}
