package dev.operations.processors;


import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;

public class ExitProcessor implements OperationCommandProcessor {
    @Override
    public boolean processOperation() {
        System.out.println("Программа завершает свою работу...");
        return false;
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.Завершение_работы;
    }
}
