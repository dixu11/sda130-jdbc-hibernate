package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "course_details")
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_description")
    private String fullDescription;

    private CourseDetails() {
    }

    public CourseDetails(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    @Override
    public String toString() {
        return "CourseDetails{" +
                "id=" + id +
                ", fullDescription='" + fullDescription + '\'' +
                '}';
    }
}
