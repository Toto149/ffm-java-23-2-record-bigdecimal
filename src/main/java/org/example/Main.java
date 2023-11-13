package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client customer1 = new Client("Hans", "Meier", "1123");
        Client customer2 = new Client("Max", "Mustermann", "2534");
        Client customer3 = new Client("Maria", "Musterfrau", "2547");

        BankService bankService = new BankService();
        bankService.openAccount(List.of(customer1));
        List<Client> customers = List.of(customer2,customer3);
        bankService.openAccount(customers);
        System.out.println(bankService.getAccounts().get(1));
        bankService.getAccounts().get(1).deposit(new BigDecimal(10000.23));
        System.out.println(bankService.split(bankService.getAccounts().get(1).getAccountNumber()));
        System.out.println(bankService.getAccounts().get(1));
        System.out.println(bankService.getAccounts().get(2));
        bankService.transfer(new BigDecimal(1000), bankService.getAccounts().get(1).getAccountNumber(),bankService.accounts.get(0).getAccountNumber() );
        System.out.println(bankService.getAccounts().get(0));
        System.out.println(bankService.getAccounts().get(1));
        System.out.println(bankService.interest(5));

    }
}