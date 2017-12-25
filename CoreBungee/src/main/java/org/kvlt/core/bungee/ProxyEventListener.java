package org.kvlt.core.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.packets.*;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

public class ProxyEventListener implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer p = event.getPlayer();

        String name = p.getName();

//        try {
//            PreparedStatement statId= CoreDB.get().getConnection()
//                    .prepareStatement("SELECT id FROM identifier WHERE player_name = ?");
//
//            statId.setString(1, name);
//            ResultSet result = statId.executeQuery();
//            int id = result.getInt("id");
//            IdMap.setId(p, id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        PlayerJoinPacket playerJoinPacket = new PlayerJoinPacket(name);
        playerJoinPacket.send();

    }

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        PendingConnection c = event.getConnection();

        PreLoginPacket plp = new PreLoginPacket(c.getName(), c.getAddress().getHostName());
        plp.send();
    }

    @EventHandler
    public void onLogin(LoginEvent event) {
        PendingConnection c = event.getConnection();

        LoginPacket lp = new LoginPacket(c.getName(), c.getUniqueId().toString());
        lp.send();
    }

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String to = player.getServer().getInfo().getName();
        player.getPendingConnection().isOnlineMode();
        SwitchServerPacket ssp = new SwitchServerPacket(player.getName(), to);
        ssp.send();
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer p = event.getPlayer();
        String name = p.getName();

        if (ProxyLoggedPlayers.isLogged(name)) {
            ProxyLoggedPlayers.logOut(name);
        }

        PlayerQuitPacket pqp = new PlayerQuitPacket(p.getName());
        pqp.send();
    }

}
