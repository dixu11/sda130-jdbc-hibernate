package hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "machines")
public class GameMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "machine")
    private List<Game> games;

    public GameMachine(String name) {
        this.name = name;
    }

    public GameMachine() {
    }
}
