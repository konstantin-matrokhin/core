package org.kvlt.core;

import java.util.Scanner;

public class CommandListener {

    public CommandListener() {
        Scanner scanner = new Scanner(System.in);

        try {
            while (scanner.hasNextLine()) {
                String cmd = scanner.nextLine();
                listenCommands(cmd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void listenCommands(String cmd) {
        if (cmd.equalsIgnoreCase("who")) {

        }
    }

}
