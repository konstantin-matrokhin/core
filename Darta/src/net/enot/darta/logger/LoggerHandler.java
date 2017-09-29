package net.enot.darta.logger;

import jline.console.ConsoleReader;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LoggerHandler extends Handler {

    private final ConsoleReader console;

    public LoggerHandler(ConsoleReader console) {
        this.console = console;
    }

    @Override
    public void publish(LogRecord record) {
        try {
            console.print(Ansi.ansi().eraseLine(Ansi.Erase.ALL).toString() + ConsoleReader.RESET_LINE + getFormatter().format(record) + Ansi.ansi().reset().toString());
            console.drawLine();
            console.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }
}
