package dev.operations.processors;

import dev.account.AccountService;
import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;

import java.util.Scanner;

public class AccountTransferProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountTransferProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public boolean processOperation() {
        System.out.println("Введите id счета, с которого необходимо списать средства:");
        int fromAccountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите id счета, на который необходимо начислить средства:");
        int toAccountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите сумму перевода:");
        int amountToTransfer = Integer.parseInt(scanner.nextLine());
        accountService.transfer(fromAccountId, toAccountId, amountToTransfer);
        System.out.println("Перевод на сумму %s со счета с id=%s на счет с id=%s выполнен успешно!"
                .formatted(amountToTransfer, fromAccountId,toAccountId));
        return true;
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.Перевод_средств;
    }
}