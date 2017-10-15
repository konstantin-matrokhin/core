package net.enot.darta.logger;

import jline.console.ConsoleReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DartaLogger extends Logger {

    private ConsoleReader consoleReader;

    public DartaLogger() throws IOException {
        super("Darta", null);

        consoleReader = new ConsoleReader();
        consoleReader.setExpandEvents(false);
        consoleReader.setPrompt(">");

        setLevel(Level.ALL);
        LoggerFormatter loggerFormatter = new LoggerFormatter();
        LoggerHandler loggerHandler = new LoggerHandler(consoleReader);
        loggerHandler.setLevel(Level.INFO);
        loggerHandler.setFormatter(loggerFormatter);
        addHandler(loggerHandler);

        System.setErr(new PrintStream(new LoggingOutputStream(this, Level.WARNING), true));
        System.setOut(new PrintStream(new LoggingOutputStream(this, Level.INFO), true));

        reader();

    }

    private void reader(){
        new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    consoleReader.readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
