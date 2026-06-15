package dev.operations.processors;

import dev.account.AccountService;
import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;
import dev.user.UserService;

import java.util.Scanner;

public class CreateAccountProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;

    public CreateAccountProcessor(Scanner scanner, UserService userService, AccountService accountService) {
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public boolean processOperation() {
        System.out.println("Введите id пользователя для которого хотите создать аккаунт:");
        int userId = Integer.parseInt(scanner.nextLine());
        var user = userService.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователя с id=%s не существует."
                        .formatted(userId)));
        var account = accountService.createAccount(user);
        user.getAccountList().add(account);
        System.out.printf("Аккаунт для пользователя %s с id=%s успешно создан!", user.getLogin(), account.getId());
        return true;
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.Создать_аккаунт;
    }
}
