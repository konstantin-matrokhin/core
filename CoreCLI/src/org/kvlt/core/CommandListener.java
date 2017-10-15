package org.kvlt.core;

import io.netty.channel.Channel;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.net.ClientManager;
import org.kvlt.core.packets.PlayerJoinPacket;
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
            for (ServerPlayer player: CoreServer.get().getServerPlayers()) {
                Log.$(player.getName() + " | " + player.getUUID().toString());
            }
        }
    }

}
