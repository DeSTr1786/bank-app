package dev.operations.processors;

import dev.account.AccountService;
import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;

import java.util.Scanner;

public class DepositAccountProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final AccountService accountService;
    public DepositAccountProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }



    @Override
    public boolean processOperation() {
        System.out.println("Введите id аккаунта, который хотите пополнить:");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите сумму для пополнения:");
        int amountToDeposit = Integer.parseInt(scanner.nextLine());
        accountService.depositAccount(accountId,amountToDeposit);
        System.out.println("Выполнено успешное пополнение аккаунта id=%s на сумму %s"
                .formatted(accountId,amountToDeposit));
        return true;
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.Пополнение_счета;
    }
}