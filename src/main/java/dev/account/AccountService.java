package dev.account;

import dev.user.User;

import java.util.*;

public class AccountService {

    private int idCounter;
    private final Map<Integer, Account> accountMap;

    public AccountService() {
        this.idCounter = 0;
        this.accountMap = new HashMap<>();
    }


    public Account createAccount (User user){
        idCounter++;
        Account account = new Account(idCounter, user.getId(), 0); // todo default amount
        accountMap.put(account.getId(), account);
        return account;

    }
    public Optional<Account> findAccountById (int id) {
        return Optional.ofNullable(accountMap.get(id));

    }
    public List<Account> getAllUsersAccounts(int userId) {
        return accountMap.values()
                .stream()
                .filter(account -> account.getUserId() == userId)
                .toList();

    }
    public void depositAccount (int accountId, int moneyToDeposit) {
        var account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт с id=%s не найден.".formatted(accountId)));
        if (moneyToDeposit <= 0) {
            throw new IllegalArgumentException("Вносить на депозит можно только неотрицательные суммы: Вы пытаетесь внести %s рублей".formatted(moneyToDeposit));
        }

        account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);
    }
}
