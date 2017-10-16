package org.kvlt.core;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.net.ClientManager;
import org.kvlt.core.packets.ProxyPingDataPacket;
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

    protected void listenCommands(String cmd) {
        if (cmd.equalsIgnoreCase("who")) {
            ClientManager.getClients().writeAndFlush(new ProxyPingDataPacket("test2"));
            Log.$("Отсылаю MOTD: test2");
        }
    }

}
