package dev.operations;

import dev.account.AccountService;
import dev.operations.processors.*;
import dev.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OperationProcessorConfiguration {

    @Bean
    public CreateUserProcessor createUserProcessor(
            Scanner scanner,
            UserService userService
    ) {
        return new CreateUserProcessor(scanner,userService);
    }
    @Bean
    public CreateAccountProcessor createAccountProcessor(
            Scanner scanner,
            UserService userService,
            AccountService accountService
    ) {
        return new CreateAccountProcessor(scanner, userService, accountService);
    }
    @Bean
    public ShowAllUsersProcessor showAllUsersProcessor(
            UserService userService
    ) {
        return new ShowAllUsersProcessor(userService);
    }

    @Bean
    public DepositAccountProcessor depositAccountProcessor(Scanner scanner, AccountService accountService) {
        return new DepositAccountProcessor(scanner, accountService);
    }

    @Bean
    public AccountWithdrawProcessor accountWithdrawProcessor (Scanner scanner, AccountService accountService) {
        return new AccountWithdrawProcessor(scanner, accountService);
    }

    @Bean
    public CloseAccountProcessor closeAccountProcessor (Scanner scanner, AccountService accountService, UserService userService) {
        return new CloseAccountProcessor(scanner, accountService, userService);
    }
    @Bean
    public AccountTransferProcessor accountTransferProcessor (Scanner scanner, AccountService accountService) {
        return new AccountTransferProcessor(scanner,accountService);
    }

    @Bean
    public ExitProcessor exitProcessor () {
        return new ExitProcessor();
    }
}
