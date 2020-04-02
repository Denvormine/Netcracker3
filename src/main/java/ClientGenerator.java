import java.awt.desktop.ScreenSleepEvent;
import java.util.Random;

public class ClientGenerator extends Thread {

    private Random random = new Random();
    private ClientQueue queue;

    public ClientGenerator(ClientQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        generate();
    }

    private void generate() {
        while (true) {
            String name = "№" + random.nextInt(1000);
            ClientRequest request = random.nextInt(2) == 0 ? ClientRequest.PUT : ClientRequest.TAKE;
            Integer moneyAmount = random.nextInt(100) + 1;
            Client client = new Client(request, moneyAmount, name);
            queue.push(client);
            try {
                Thread.sleep((random.nextInt(3) + 1) * 1000);
            }
            catch (InterruptedException ex) {
                System.err.println(ex);
                System.exit(2);
            }
        }
    }
}
