package dev;


import dev.operations.ConsoleOperationType;
import dev.operations.OperationCommandProcessor;


import java.util.Map;
import java.util.Scanner;

public class OperationConsoleListener {
    private final Scanner scanner;
    private final Map<ConsoleOperationType, OperationCommandProcessor> processorMap;


    public OperationConsoleListener(Scanner scanner, Map<ConsoleOperationType, OperationCommandProcessor> processorMap) {
        this.scanner = scanner;
        this.processorMap = processorMap;

    }

    public void listenUpdates() {
        boolean isRunning = true;
        while (isRunning) {
            var operationType = listenNextOperation();
            isRunning = processNextOperation(operationType);

        }
    }

    private ConsoleOperationType listenNextOperation() {
        System.out.println("\nПожалуйста, выберите следующий тип желаемой операции:");
        printAllAvailableOperations();
        System.out.println();
        while (true) {
            var nextOperation = scanner.nextLine();
            try {
                return ConsoleOperationType.valueOf(nextOperation);
            } catch (IllegalArgumentException e) {
                System.out.println("Команда не найдена.");
            }
        }
    }

    private void printAllAvailableOperations() {
        processorMap.keySet()
                    .forEach(System.out::println);
    }

    private boolean processNextOperation(ConsoleOperationType operation) {
        try {
            var processor = processorMap.get(operation);
            return processor.processOperation();
        } catch (Exception e) {
            System.out.printf(
                    "Ошибка выполнения команды %s: error = %s%n", operation, e.getMessage()
            );
            return true;
        }


    }
    public void start() {
        System.out.println("Запуск программы...");
    }
    public void endListen() {
        System.out.println("Программа завершила работу.");

    }


}


