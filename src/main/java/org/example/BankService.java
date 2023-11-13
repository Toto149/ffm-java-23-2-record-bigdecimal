package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BankService {
    List<Account> accounts = new ArrayList<Account>();

    public BankService(){

    }

    public BankService(List<Account> accounts){
        this.accounts = accounts;

    }

    public String openAccount(List<Client> customer){
        String newAccountNumber = generateAccountNumber();
        Account newAccount= new Account(newAccountNumber,new BigDecimal("0"), customer);
        accounts.add(newAccount);
        return newAccountNumber;
    }


    public void transfer(BigDecimal transferAmount, String senderAccountNumber, String receiverAccountNumber){
        for(Account account : accounts){
            if(account.getAccountNumber().equals(senderAccountNumber)){
                account.withdrawMoney(transferAmount);
            }
            if(account.getAccountNumber().equals(receiverAccountNumber)){
                account.deposit(transferAmount);
            }
        }
    }

    private static String generateAccountNumber(){
        Random random = new Random();


       //char[] numerals = "0123456789".toCharArray();
        String[] strArray = new String[12];
        for(int i =0; i<12;i++){
           if(i>0 && i%3 ==0) {
               strArray[i]=String.valueOf('-');
            }
           else{

               int randomInt = random.nextInt(10);
               strArray[i] = String.valueOf(randomInt);
           }
        }
        return String.join("",strArray);
    }
    public List<String> split(String accountNumber){
        Account account = null;
        for(Account account1 : accounts){
            if(account1.getAccountNumber().equals(accountNumber)){
                account = account1;
            }
        }
        accounts.remove(account);
        assert account != null;
        int amountOfAccountHolders = account.getCustomers().size();
        BigDecimal splitBalance = account.getAccountBalance().divide(new BigDecimal(amountOfAccountHolders), RoundingMode.DOWN);
        List<String> accountNumbers = new ArrayList<>();
        for(int i = 0; i<amountOfAccountHolders; i++){
            String newAccountNumber = generateAccountNumber();
            Account newAccount = new Account(newAccountNumber,splitBalance.setScale(2,RoundingMode.DOWN),List.of(account.getCustomers().get(i)));
            accountNumbers.add(newAccountNumber);
            accounts.add(newAccount);
        }

        return  accountNumbers;

    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<BigDecimal> interest(double interestRate){
        List<BigDecimal> interestForCustomers = new ArrayList<>();
        for(Account account : this.accounts){
            interestForCustomers.add(account.getAccountBalance().multiply(new BigDecimal(interestRate/100)).setScale(2,RoundingMode.DOWN));
        }
        return interestForCustomers;
    }
}
