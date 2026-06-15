package dev.operations.processors;

import dev.account.AccountService;
import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;

import java.util.Scanner;

public class AccountWithdrawProcessor implements OperationCommandProcessor {
    private final Scanner scanner;
    private final AccountService accountService;
    public AccountWithdrawProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }
    @Override
    public boolean processOperation() {
        System.out.println("Введите id аккаунта, с которого хотите списать средства:");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите сумму для списания:");
        int amountToWithdraw = Integer.parseInt(scanner.nextLine());
        accountService.withdrawFromAccount(accountId,amountToWithdraw);
        System.out.println("Выполнено успешное списание с аккаунта id=%s на сумму %s"
                .formatted(accountId,amountToWithdraw));
        return true;
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.Снятие_средств;
    }
}