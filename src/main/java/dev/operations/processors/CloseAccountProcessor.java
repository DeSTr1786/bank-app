package dev.operations.processors;

import dev.account.Account;
import dev.account.AccountService;
import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;
import dev.user.User;
import dev.user.UserService;

import java.util.Scanner;

public class CloseAccountProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final AccountService accountService;
    private final UserService userService;

    public CloseAccountProcessor(Scanner scanner, AccountService accountService, UserService userService) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public boolean processOperation() {
        System.out.println("Введите id аккаунта, который хотите закрыть:");
        int accountId = Integer.parseInt(scanner.nextLine());
        Account account = accountService.closeAccount(accountId);
        User user = userService.findUserById(account.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Аккаунта с id=%s не найдено.".formatted(account.getUserId())));
        user.getAccountList().remove(account);
        System.out.println("Операция выполнена успешно! Счет с id=%s закрыт."
                .formatted(accountId));
        return true;
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.Закрытие_счета;
    }
}
