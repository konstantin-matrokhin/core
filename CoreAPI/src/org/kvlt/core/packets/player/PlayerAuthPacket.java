package org.kvlt.core.packets.player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kvlt.core.CoreServer;
import org.kvlt.core.bukkit.datastorage.LoggedPlayers;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class PlayerAuthPacket extends Packet<Channel> {

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

    }

    @Override
    protected void onCore(Channel channel) {
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(playerName);

        if (op == null) return;
        if (op.isLogged()) return;

        successful = PlayerDB.correctPassword(op, password);
        if (successful) {
            op.setLogged(true);
        }

        CoreServer.get().getGameServers().send(this);
        CoreServer.get().getProxies().send(this);
    }

    @Override
    protected void onServer() {
        Player p = Bukkit.getPlayer(playerName);

        if (p == null) return;
        if (LoggedPlayers.isLogged(playerName)) {
            p.sendMessage("Сессия активна.");
            return;
        }

        if (successful) {
            LoggedPlayers.logIn(playerName);
            p.sendMessage("Вы успешно вошли!");
        } else {
            p.sendMessage("Неверный пароль!");
        }
    }

    @Override
    protected void onProxy() {
        if (successful) {
            ProxyLoggedPlayers.logIn(playerName);
        }
    }
}
