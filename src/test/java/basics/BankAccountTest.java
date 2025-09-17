package basics;

import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BankAccountTest {

    @Test
    public void deposit_and_withdraw_amount() {
        BankAccount ba = new BankAccount();
        ba.deposit(new BigDecimal("100.00"));
        ba.withdraw(new BigDecimal("30.00"));
        assertThat(ba.getBalance()).isEqualByComparingTo("70.00");
    }

    @Test
    public void deposit_negative_amount_throws_exception() {
        BankAccount ba = new BankAccount();
        try {
            ba.deposit(new BigDecimal("-50.00"));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Deposit amount must be non-negative");
        }
    }

    @Test
    public void withdraw_more_than_balance_throws_exception() {
        BankAccount ba = new BankAccount();
        ba.deposit(new BigDecimal("50.00"));
        try {
            ba.withdraw(new BigDecimal("100.00"));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Insufficient funds for withdrawal");
        }
    }

    @Test
    public void withdraw_negative_amount_throws_exception() {
        BankAccount ba = new BankAccount();
        ba.deposit(new BigDecimal("50.00"));
        try {
            ba.withdraw(new BigDecimal("-20.00"));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Withdrawal amount must be non-negative");
        }
    }


}

