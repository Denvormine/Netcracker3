public class Client {
    private ClientRequest request;
    private Integer moneyAmount;
    private String name;

    public Client(ClientRequest request, Integer moneyAmount, String name) {
        this.request = request;
        this.moneyAmount = moneyAmount;
        this.name = name;
    }

    public ClientRequest getRequest() {
        return request;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public String getName() {
        return name;
    }
}
