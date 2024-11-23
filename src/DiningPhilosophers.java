public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {
        // Crear un array de 5 filósofos
        final Philosopher[] philosophers = new Philosopher[5];
        // Crear un array de objetos que representan los tenedores
        Object[] forks = new Object[philosophers.length];

        // Inicializar cada tenedor como un nuevo objeto
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        // Asignar tenedores a cada filósofo
        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i]; // Tenedor a la izquierda
            Object rightFork = forks[(i + 1) % forks.length]; // Tenedor a la derecha (circular)

            if (i == philosophers.length - 1) {
                // El último filósofo toma primero el tenedor derecho para evitar un posible bloqueo mutuo (deadlock)
                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else {
                // Otros filósofos toman primero el tenedor izquierdo
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }

            // Crear un nuevo hilo para cada filósofo y comenzar la ejecución
            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}
