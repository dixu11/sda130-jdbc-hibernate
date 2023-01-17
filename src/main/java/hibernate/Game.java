package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_of_machine", nullable = false)
    private GameMachine machine;

    public Game(String name) {
        this.name = name;
    }

    public void setMachine(GameMachine machine) {
        this.machine = machine;
    }

    public Game() {
    }
}
