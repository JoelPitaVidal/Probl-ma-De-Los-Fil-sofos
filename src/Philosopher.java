public class Philosopher implements Runnable {

    // Los tenedores a ambos lados de este Filósofo
    private Object leftFork;
    private Object rightFork;

    // Constructor que recibe los tenedores izquierdo y derecho
    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    // Método para realizar una acción con una pausa aleatoria
    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        // Pausa aleatoria entre 0 y 99 milisegundos
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Pensando
                doAction(System.nanoTime() + ": Pensando");
                // Intentar tomar el tenedor izquierdo
                synchronized (leftFork) {
                    doAction(System.nanoTime() + ": Tomó el tenedor izquierdo");
                    // Intentar tomar el tenedor derecho
                    synchronized (rightFork) {
                        // Comiendo
                        doAction(System.nanoTime() + ": Tomó el tenedor derecho - Comiendo");
                        // Soltar el tenedor derecho
                        doAction(System.nanoTime() + ": Soltó el tenedor derecho");
                    }
                    // Volver a pensar
                    doAction(System.nanoTime() + ": Soltó el tenedor izquierdo. Volver a pensar");
                }
            }
        } catch (InterruptedException e) {
            // Interrumpir el hilo actual si se produce una excepción
            Thread.currentThread().interrupt();
            return;
        }
    }
}

