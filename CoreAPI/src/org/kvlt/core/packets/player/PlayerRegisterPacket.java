package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.packets.bukkit.ServerMessagePacket;
import org.kvlt.core.utils.Log;

public class PlayerRegisterPacket extends Packet {

    private static final String SUCCESSFUL_REGISRATION = "Вы успешно зарегистрировались";

    private String playerName;
    private String password;

    public PlayerRegisterPacket(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
    }

    @Override
    protected void onCore() {
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(playerName);
        if (op == null) return;

        String response;

        if (!op.isLogged()) {
            if (op.getPassword() == null || op.getPassword().isEmpty()) {
                PlayerDB.register(op, password);
                op.setLogged(true);
                op.setPassword(password);
                op.setRegistered(true);

                PlayerAuthPacket pap  = new PlayerAuthPacket(playerName, true);
                ServerMessagePacket smp = new ServerMessagePacket(playerName, SUCCESSFUL_REGISRATION);
                op.getCurrentServer().send(pap, smp);
                Log.$(playerName + " registered / " + password);

                return;
            } else {
                response = "Вы уже были зарегистрированы!";
            }
        } else {
            response = "Вы уже в игре!";
        }

        ServerMessagePacket respMsg = new ServerMessagePacket(playerName, response);
        op.getCurrentProxy().send(respMsg);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }
}
