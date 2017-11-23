package org.kvlt.core.packets.player;

import org.bukkit.Bukkit;
import org.kvlt.core.CoreServer;
import org.kvlt.core.bukkit.datastorage.LoggedPlayers;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class PlayerAuthPacket extends Packet {

    private String playerName;
    private String password;
    private boolean successful;

    public PlayerAuthPacket(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
    }

    public PlayerAuthPacket(String playerName, boolean successful) {
        this.playerName = playerName;
        this.successful = successful;
    }

    @Override
    protected void onCore() {
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(playerName);
        successful = PlayerDB.correctPassword(op, password);
        if (successful) {
            op.setLogged(true);
        }
        op.getCurrentServer().send(this);
    }

    @Override
    protected void onServer() {
        if (successful) {
            LoggedPlayers.logIn(playerName);
            Bukkit.getPlayer(playerName).sendMessage("Вы успешно вошли!");
        } else {
            Bukkit.getPlayer(playerName).sendMessage("Неверный пароль!");
        }
    }

    @Override
    protected void onProxy() {

    }
}
