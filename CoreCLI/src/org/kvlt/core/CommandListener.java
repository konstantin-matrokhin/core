package org.kvlt.core;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.utils.Log;

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

    private void listenCommands(String cmd) {
        if (cmd.equalsIgnoreCase("who")) {
            for (OnlinePlayer op: CoreServer.get().getOnlinePlayers()) {
                String data = op.getName()
                        + " | "
                        + op.getCurrentServer()
                        + " | "
                        + op.getUUID();

                Log.$(data);
            }
        }
    }

}
