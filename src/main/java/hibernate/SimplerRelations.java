package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SimplerRelations {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Game.class)
                .addAnnotatedClass(GameMachine.class)
                .buildSessionFactory();

        Session currentSession = factory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Game game = new Game("Tetris");
        GameMachine machine = new GameMachine();
        game.setMachine(machine);
        currentSession.save(machine);
        currentSession.save(game);
        transaction.commit();
        currentSession.close();
    }
}
