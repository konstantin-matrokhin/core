package org.kvlt.core.commands;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CommandListener {

    private Set<Command> commands;
    private LineReader reader;

    public CommandListener() {
        try {
            Terminal terminal = TerminalBuilder.builder()
                    .dumb(true)
                    .build();

            reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .appName("Core CLI")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        commands = new HashSet<>();

        registerCommand(new WhoCommand());
        registerCommand(new BroadcastCommand());
        registerCommand(new DieCommand());
        registerCommand(new WhoisCommand());
        registerCommand(new ReloadCommand());
        registerCommand(new PluginsCommand());
        registerCommand(new ReloadEmailsCommand());
        registerCommand(new KickCommand());
        registerCommand(new BanCommand());
        registerCommand(new MotdCommand());

    }

    public void listen() {
        while (true) {
            try {
                String line = reader.readLine(">");
                listenCommands(line);
            } catch (UserInterruptException ignored) {
            } catch (EndOfFileException e) {
                return;
            }
        }
    }

    public void registerCommand(Command command) {
        commands.add(command);
    }

    private void listenCommands(String inputLine) {
        String cmd;
        String[] args = {};

        if (inputLine.indexOf(' ') == -1) {
            cmd = inputLine;
        } else {
            String[] strArr = inputLine.split("\\s");
            cmd = strArr[0];
            args = Arrays.copyOfRange(strArr, 1, strArr.length);
        }

        for (Command c: commands) {
            c.passInput(cmd, args);
        }
    }

}
