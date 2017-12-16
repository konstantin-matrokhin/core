package org.kvlt.core.commands;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandListener {

    private List<Command> commands;
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

        commands = new ArrayList<>();

        registerCommand(new WhoCommand());
        registerCommand(new BroadcastCommand());
        registerCommand(new DieCommand());
        registerCommand(new WhoisCommand());
        registerCommand(new ReloadCommand());
        registerCommand(new PluginsCommand());

    }

    public void listen() {
        while (true) {
            try {
                String line = reader.readLine("> ");
                listenCommands(line);
            } catch (Exception e) {
                e.printStackTrace();
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
