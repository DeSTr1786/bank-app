package dev.account;

import dev.user.User;

import java.util.*;

public class AccountService {

    private int idCounter;
    private final Map<Integer, Account> accountMap;
    private final int defaultAccountAmount;
    private final double transferCommission;

    public AccountService(int defaultAccountAmount, double transferCommission) {
        this.defaultAccountAmount = defaultAccountAmount;
        this.transferCommission = transferCommission;
        this.idCounter = 0;
        this.accountMap = new HashMap<>();
    }


    public Account createAccount (User user){
        boolean hasAccounts = user.getAccountList() != null && !user.getAccountList().isEmpty();
        int initialBalance = hasAccounts ? 0 : defaultAccountAmount;
        idCounter++;
        Account account = new Account(idCounter, user.getId(), initialBalance);
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
            throw new IllegalArgumentException("Вносить на депозит можно только положительные суммы: Вы пытаетесь внести %s рублей".formatted(moneyToDeposit));
        }

        account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);
    }

    public void withdrawFromAccount(int accountId, int amountToWithdraw) {
        var account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт с id=%s не найден.".formatted(accountId)));
        if (amountToWithdraw <= 0) {
            throw new IllegalArgumentException("Снимать со счета можно только положительные суммы: Вы пытаетесь снять %s рублей".formatted(amountToWithdraw));
        }

        if (account.getMoneyAmount() < amountToWithdraw) {
            throw new IllegalArgumentException("Недостаточно средств на счете: id=%s, баланс=%s, сумма списания=%s"
                    .formatted(accountId, account.getMoneyAmount(), amountToWithdraw));
        }

        account.setMoneyAmount(account.getMoneyAmount() - amountToWithdraw);
    }

    public Account closeAccount(int accountId) {
        var accountToRemove = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт с id=%s не найден.".formatted(accountId)));
        List<Account> accountList = getAllUsersAccounts(accountToRemove.getUserId());
        if (accountList.size() == 1) {
            throw new IllegalArgumentException("Недопустимая операция. Нельзя закрыть единственный аккаунт");
        }
        Account accountToDeposit =  accountList.stream()
                .filter(it -> it.getId() != accountId)
                .findFirst()
                .orElseThrow();

        accountToDeposit.setMoneyAmount(accountToDeposit.getMoneyAmount() + accountToRemove.getMoneyAmount());

        accountMap.remove(accountId);
        return accountToRemove;
    }

    public void transfer(int fromAccountId, int toAccountId, int amountToTransfer) {
        var accountFrom = findAccountById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт с id=%s не найден.".formatted(fromAccountId)));
        var accountTo = findAccountById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Аккаунт с id=%s не найден.".formatted(toAccountId)));

        if (amountToTransfer <= 0) {
            throw new IllegalArgumentException("Переводить со счета можно только положительные суммы: Вы пытаетесь перевести %s рублей".formatted(amountToTransfer));
        }
        if (accountFrom.getMoneyAmount() < amountToTransfer) {
            throw new IllegalArgumentException("Недостаточно средств на счете: id=%s, баланс=%s, сумма перевода=%s"
                    .formatted(accountFrom, accountFrom.getMoneyAmount(), amountToTransfer));
        }

        int totalAmountToDeposit = accountTo.getUserId() != accountFrom.getUserId()
                ? (int) (amountToTransfer * (1 - transferCommission))
                : amountToTransfer;

        accountFrom.setMoneyAmount(accountFrom.getMoneyAmount() - amountToTransfer);
        accountTo.setMoneyAmount(accountTo.getMoneyAmount() + totalAmountToDeposit);
    }
}
