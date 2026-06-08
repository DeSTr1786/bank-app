package dev.account;

import dev.user.User;

import java.util.*;

public class AccountService {

    private int idCounter;

    public AccountService(int idCounter, Map<Integer, Account> accountMap) {
        this.idCounter = 0;
        this.accountMap = new HashMap<>();
    }

    private final Map<Integer, Account> accountMap;

    public Account createAccount (User user){
        idCounter++;
        Account account = new Account(idCounter, user.getId(), 0); // todo default amount
        accountMap.put(account.getId(), account);
        return account;

    }
    public Optional<Account> findAccountById (int id) {

    }
    public List<Account> getAllUsersAccounts(int userId) {

    }
    public void depositAccount (int accountId int moneyToDeposit) {

    }
}
