import java.util.Queue;
import java.util.Random;

public class Worker extends Thread {
    Random random = new Random();
    ClientQueue queue;
    BankAccount bankAccount;

    public Worker(ClientQueue queue, BankAccount bankAccount) {
        this.queue = queue;
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        work();
    }

    private void work() {
        while (true) {
            Client client = queue.poll();

            try {
                Thread.sleep((random.nextInt(3) + 1) * 1000);
            }
            catch (InterruptedException ex) {
                System.err.println(ex);
                System.exit(2);
            }

            if (client.getRequest() == ClientRequest.PUT) {
                bankAccount.putMoney(client.getMoneyAmount());
                System.out.println("Client " + client.getName() + " put " + client.getMoneyAmount());
            }
            else if (client.getRequest() == ClientRequest.TAKE) {
                if (bankAccount.takeMoney(client.getMoneyAmount())) {
                    System.out.println("Client " + client.getName() + " took " + client.getMoneyAmount());
                }
                else {
                    System.out.println("Client " + client.getName() +
                                    " tried to take " + client.getMoneyAmount() + " money, but there was not enough in BankAccount"
                    );
                }
            }
        }
    }

}
