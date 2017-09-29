package net.enot.dartabungee.client.listeners;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartabungee.client.ConnectPlayer;
import net.enot.dartabungee.utils.RegexUtil;
import net.enot.dartasystem.packets.player.PlayerJoinPacket;
import net.enot.dartasystem.packets.player.PlayerQuitPacket;
import net.enot.dartasystem.packets.player.PlayerRedirectPacket;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

/**
 * Created by Енот on 23.04.2017.
 */
public class ConnectionListener implements Listener {

    public ConnectionListener() {
        DartaBungee.getInstance().getProxy().getPluginManager().registerListener(DartaBungee.getInstance(), this);
    }

    /**
     * LOWEST
     * LOW
     * NORMAL
     * HIGH
     * HIGHEST
     */

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPreLogin(PreLoginEvent e) throws InterruptedException {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            e.setCancelled(true);
            e.setCancelReason(new TextComponent(MessageUtil.gui_no_connect_darta));
            return;
        }
        String name = e.getConnection().getName();
        if (name.length() < 3 || name.length() > 16){
            e.setCancelled(true);
            e.setCancelReason(new TextComponent("§cНекорректная длина ника\nДопустимая длина ника от 3 до 16 символов"));
            return;
        }
        if (!RegexUtil.isNick(name)){
            e.setCancelled(true);
            e.setCancelReason(new TextComponent("§cВ нике есть недопустимые символы,\nразрешено использовать только цифры и буквы латинского алфавита"));
            return;
        }
        ConnectPlayer connectPlayer = new ConnectPlayer(name);
        if (!connectPlayer.isCanJoin()) {
            e.setCancelled(true);
            e.setCancelReason(new TextComponent("§cДанный игрок уже на сервере"));
        }
        connectPlayer.remove();
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        String name = e.getPlayer().getName();
        DartaBungee.getInstance().getDartaClient().write(new PlayerJoinPacket(name));
    }

    @EventHandler(priority = Byte.MIN_VALUE)
    public void onServerConnect(ServerConnectEvent e){
        ProxiedPlayer player = e.getPlayer();
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            e.setCancelled(true);
            player.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
        }
    }

    @EventHandler
    public void onServerConnected(ServerConnectedEvent e) {
        String name = e.getPlayer().getName();
        String server = e.getServer().getInfo().getName();
        DartaBungee.getInstance().getDartaClient().write(new PlayerRedirectPacket(name, server));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDisconnect(PlayerDisconnectEvent e) {
        String name = e.getPlayer().getName();
        DartaBungee.getInstance().getDartaClient().write(new PlayerQuitPacket(name));
    }

}
