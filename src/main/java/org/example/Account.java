package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

public class Account {
    private String accountNumber;
    private BigDecimal accountBalance;
    private List<Client> customers;

    public Account(){

    }
    public Account(String accountNumber, BigDecimal accountBalance, List<Client> customers){
        this.accountNumber = accountNumber;
        this.accountBalance=accountBalance;
        this.customers = customers;
    }

    public void deposit(BigDecimal depositAmount){
        this.accountBalance = this.accountBalance.add(depositAmount).setScale(2, RoundingMode.DOWN);
    }
    public void withdrawMoney(BigDecimal withdrawAmount){
        this.accountBalance = this.accountBalance.subtract(withdrawAmount);
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public List<Client> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                ", customer=" + customers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(accountBalance, account.accountBalance) && Objects.equals(customers, account.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, accountBalance, customers);
    }
}
