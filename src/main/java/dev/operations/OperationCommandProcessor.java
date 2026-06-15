package dev.operations;

public interface OperationCommandProcessor {

boolean processOperation();

ConsoleOperationType getOperationType();
}
