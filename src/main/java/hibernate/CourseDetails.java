package hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullDescription;

    private CourseDetails() {
    }

    public CourseDetails(int id, String fullDescription) {
        this.id = id;
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
