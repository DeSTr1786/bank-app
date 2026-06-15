package dev;

import dev.account.AccountService;
import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;
import dev.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
    @Bean
    public OperationConsoleListener operationConsoleListener(Scanner scanner,
                                                             List<OperationCommandProcessor> commandProcessorList) {
        Map<ConsoleOperationType, OperationCommandProcessor> map = commandProcessorList
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        processor -> processor.getOperationType(),
                                        processor -> processor
                                )
                        );
        return new OperationConsoleListener(scanner, map);
    }
    @Bean
    public UserService userService(AccountService accountService) {
        return new UserService(accountService);

    }
    @Bean
    public AccountService accountService(
            @Value("${account.default-amount}") int defaultAmount,
            @Value("${account.transfer-commission}") double transferCommission
    ) {

        return new AccountService(defaultAmount, transferCommission);
    }
}
