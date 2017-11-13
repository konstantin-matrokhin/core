package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.PlayerModel;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.utils.Log;

public class WhoisCommand extends Command {

    public WhoisCommand() {
        super("whois");
        addAliases(
                "info",
                "about"
        );
    }

    @Override
    protected boolean execute() {
        if (getArgs().length != 1) return false;

        String name = getArg(0);
        /*ServerPlayer player = new PlayerModel(name);*/
        OnlinePlayer player = CoreServer.get().getOnlinePlayers().get(name);
        if (player != null) {
            printOnlineInfo(player);
        } else {
            printOfflineInfo(player.getName());
        }
        return true;
    }

    private void printOfflineInfo(String playerName) {
        ServerPlayer player = new PlayerModel(playerName);
    }

    private void printOnlineInfo(OnlinePlayer player) {
        Log.$("_____________________________________");
        Log.$("ИГРОК: " + player.getName());
        Log.$("ID: " + player.getId());
        Log.$("СЕРВЕР: "  + player.getCurrentServer());
        Log.$("IP: " + player.getIp());
        Log.$("_____________________________________");
    }

}
