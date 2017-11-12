package org.kvlt.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandListener {

    private String command;
    private String[] args;

    private List<Command> commands;

    public CommandListener() {
        commands = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        registerCommand(new WhoCommand());
        registerCommand(new BroadcastCommand());
        registerCommand(new StopCommand());
        registerCommand(new WhoisCommand());

        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                listenCommands(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public String[] getArgs() {
        return args;
    }

    public String getCommand() {
        return command;
    }
}
