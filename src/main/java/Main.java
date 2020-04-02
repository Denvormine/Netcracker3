import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        ClientQueue queue = new ClientQueue();
        int workersAmount = 4;

        ArrayList<Worker> workers = new ArrayList<>();
        for (int i = 0; i < workersAmount; i++) {
            Worker worker = new Worker(queue, bankAccount);
            worker.start();
            workers.add(worker);
        }

        ClientGenerator generator = new ClientGenerator(queue);
        generator.start();
        try {
            generator.join();
        }
        catch (InterruptedException ex) {
            System.err.println(ex);
            System.exit(3);
        }

    }
}
