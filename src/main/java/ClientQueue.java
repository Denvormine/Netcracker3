import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ClientQueue {
    Queue<Client> queue = new ArrayDeque<Client>();
    Semaphore semaphore = new Semaphore(0, true);

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public Client poll() {
        try {
            semaphore.acquire();
        }
        catch (InterruptedException ex) {
            System.err.println(ex);
            System.exit(1);
        }
        return queue.poll();
    }

    public boolean push(Client client) {
        if (queue.add(client)) {
            semaphore.release();
            return true;
        }
        System.err.println("Client " + client.getName() + "wasn't added to the queue");
        return false;
    }
}
