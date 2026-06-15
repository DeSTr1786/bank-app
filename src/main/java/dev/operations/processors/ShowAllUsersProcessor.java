package dev.operations.processors;

import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;
import dev.user.User;
import dev.user.UserService;

import java.util.List;

public class ShowAllUsersProcessor implements OperationCommandProcessor {
    private final UserService userService;

    public ShowAllUsersProcessor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean processOperation() {
        List<User> users = userService.getAllUsers();
        System.out.println("Список всех пользователей:");
        users.forEach(System.out::println);
        return true;
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.Список_пользователей;
    }
}
