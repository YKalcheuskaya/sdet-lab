package basics;
import java.math.BigDecimal;



public class BankAccount {

    private BigDecimal balance = BigDecimal.ZERO;

    public BigDecimal getBalance(){
        return balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Deposit amount must be non-negative");
        }
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Withdrawal amount must be non-negative");
        }
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        balance = balance.subtract(amount);
    }

}
