package dev.operations.processors;

import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;
import dev.user.User;
import dev.user.UserService;

import java.util.Scanner;

public class CreateUserProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final UserService userService;

    public CreateUserProcessor(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public boolean processOperation() {
        System.out.println("Введите логин для нового пользователя:");
        String login = scanner.nextLine();
        User user = userService.createUser(login);
        System.out.println("Пользователь успешно создан: " + user.toString());
        return true;
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.Создать_пользователя;
    }
}
