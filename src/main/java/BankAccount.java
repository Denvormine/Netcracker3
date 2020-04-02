
public class BankAccount {
    private Integer moneyAmount;

    public BankAccount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public synchronized Integer getMoneyAmount() {
        return moneyAmount;
    }

    public synchronized boolean takeMoney(Integer takeAmount) {
        if (moneyAmount < takeAmount) {
            return false;
        }
        moneyAmount -= takeAmount;
        System.out.println("Money amount: " + moneyAmount);
        return true;
    }

    // Скорее всего synchronized здесь не нужен, но оставил на всякий случай
    public synchronized void putMoney(Integer amount) {
        moneyAmount += amount;
        System.out.println("Money amount: " + moneyAmount);
    }
}
