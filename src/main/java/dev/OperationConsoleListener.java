package dev;

import dev.account.AccountService;
import dev.user.User;
import dev.user.UserService;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class OperationConsoleListener {
    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;

    public OperationConsoleListener(Scanner scanner, UserService userService, AccountService accountService) {
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;
    }

    public void listenUpdates() {
        System.out.println("Пожалуйста, выберите тип желаемой операции:\n");
        while (true) {
            var operationType = listenNextOperation();
            try {
                processNextOperation(operationType);
            } catch (Exception e) {
                System.out.printf(
                        "Ошибка выполнения команды %s: error = %s%n", operationType, e.getMessage()
                );
            }

        }


    }

    private String listenNextOperation() {
        System.out.println("Пожалуйста, выберите следующий тип желаемой операции:\n");
        return scanner.nextLine();
    }
    private void processNextOperation(String operation) {

        if (operation.equals("Создать пользователя")) {

            System.out.println("Введите логин для нового пользователя:");
            String login = scanner.nextLine();
            User user = userService.createUser(login);
            System.out.println("Пользователь успешно создан: " + user.toString());
        }
        else if (operation.equals("Список пользователей")) {

            List<User> users = userService.getAllUsers();
            System.out.println("Список всех пользователей:");
            users.forEach(System.out::println);
        } else if (operation.equals("Создать аккаунт")) {

            System.out.println("Введите id пользователя для которого хотите создать аккаунт:");
            int userId = Integer.parseInt(scanner.nextLine());
            var user = userService.findUserById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Пользователя с id=%s не существует."
                            .formatted(userId)));
            var account = accountService.createAccount(user);
            user.getAccountList().add(account);
            System.out.printf("Аккаунт для пользователя %s с id=%s успешно создан!", user.getLogin(), account.getId());
        }
    }

}


