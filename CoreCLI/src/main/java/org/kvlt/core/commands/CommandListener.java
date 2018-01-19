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
        commands = new HashSet<>();
        try {
            Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();
            reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .appName("Core CLI")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Command[] commandsArray = {
                new WhoCommand(),
                new BroadcastCommand(),
                new DieCommand(),
                new WhoisCommand(),
                new ReloadCommand(),
                new PluginsCommand(),
                new ReloadEmailsCommand(),
                new KickCommand(),
                new BanCommand(),
                new MotdCommand(),
        };
        registerCommands(commandsArray);
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

    public void registerCommands(Command[] commandArray) {
        commands.addAll(Arrays.asList(commandArray));
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
