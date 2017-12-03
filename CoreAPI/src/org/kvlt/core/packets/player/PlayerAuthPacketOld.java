package org.kvlt.core.packets.player;

import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kvlt.core.CoreServer;
import org.kvlt.core.bukkit.datastorage.LoggedPlayers;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.PacketOld;
import org.kvlt.core.packets.bukkit.ServerMessagePacket;

public class PlayerAuthPacketOld extends PacketOld<Channel> {

    private String playerName;
    private String password;
    private boolean successful;

    public PlayerAuthPacketOld(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
    }

    public PlayerAuthPacketOld(String playerName, boolean successful) {
        this.playerName = playerName;
        this.successful = successful;
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onCore(Channel channel) {
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(playerName);
        String response = null;

        if (op == null) return;
        if (!op.isRegistered()) {
            response = "Вы не зарегистрированы!";
        }
        if (op.isLogged()) {
            response = "Вы уже в игре!";
        } else {
            successful = PlayerDB.correctPassword(op, password);
            if (successful) {
                op.setLogged(true);
            }
        }

        ServerMessagePacket smp = new ServerMessagePacket(playerName, response);
        CoreServer.get().getGameServers().send(this, smp);
        CoreServer.get().getProxies().send(this); //MAYBE NOT NEEDED!
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
