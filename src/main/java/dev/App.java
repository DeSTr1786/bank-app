package dev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("dev");
        context.getBean(OperationConsoleListener.class).listenUpdates();
    }
}

