package net.enot.dartabungee.auth;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

/**
 * Created by Енот on 30.04.2017.
 */
public class AuthListener implements Listener {

    public AuthListener() {
        ProxyServer.getInstance().getPluginManager().registerListener(DartaBungee.getInstance(), this);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPreLogin(PreLoginEvent e) {
        PendingConnection pendingConnection = e.getConnection();
        String name = pendingConnection.getName();
        String ip = pendingConnection.getAddress().getAddress().getHostAddress();
        if (DartaBungee.getInstance().containsProtectedAcconts(name)) {
            if (!DartaBungee.getInstance().isProtected(name, ip)) {
                e.setCancelled(true);
                e.setCancelReason(new TextComponent("§cДанный аккаунт вам не принадлежит"));
                return;
            }
        }

        AuthPlayer authPlayer = new AuthPlayer(name, ip);
        if (!DartaBungee.getInstance().getAuthSQL().loadAuthPlayer(authPlayer)) {
            e.setCancelled(true);
            e.setCancelReason(new TextComponent(MessageUtil.no_connect_mysql));
            authPlayer.remove();
            return;
        }
        if (authPlayer.isLicense()){
            authPlayer.setAuthorized(true);
            pendingConnection.setOnlineMode(true);
        }
    }

    @EventHandler(priority = Byte.MAX_VALUE)
    public void onPreLoginCheck(PreLoginEvent e){
        if (e.isCancelled()){
            String name = e.getConnection().getName();
            AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(name);
            authPlayer.remove();
        }
    }

    @EventHandler(priority = Byte.MAX_VALUE)
    public void onLoginCheck(LoginEvent e){
        if (e.isCancelled()){
            String name = e.getConnection().getName();
            AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(name);
            authPlayer.remove();
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onServerConnect(ServerConnectEvent e) {
        ProxiedPlayer player = e.getPlayer();
        Server server = player.getServer();
        AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(player.getName());

        if (!authPlayer.isAuthorized()) {

            if (authPlayer.isRegistered()) {
                if (authPlayer.isSession()) {
                    authPlayer.setAuthorized(true);
                    //e.setTarget();
                    return;
                }
            }

            if (server == null){
                //e.setTarget();
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChat(ChatEvent e) {
        ProxiedPlayer player = (ProxiedPlayer) e.getSender();
        AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(player.getName());
        if (!authPlayer.isAuthorized()) {
            if (e.isCommand()) {
                String[] args = e.getMessage().split(" ");
                String command = args[0].toLowerCase();
                if (command.equals("/email")){
                    if (args.length > 1){
                        if (args[1].equalsIgnoreCase("recovery")){
                            return;
                        }
                    }
                } else if (command.equals("/l") || command.equals("/login") || command.equals("/reg") || command.equals("/register") || command.equals("/code") || command.equals("/password")) {
                    return;
                }
                player.sendMessage(new TextComponent("§cЭту команду можно использовать, только авторизовавшись"));
                e.setCancelled(true);
            } else {
                DartaBungee.getInstance().getDartaClient().write(new TestOUTPacket());
                player.sendMessage(new TextComponent("§cВы еще не авторизовались"));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = Byte.MAX_VALUE)
    public void onPlayerDisconnect(PlayerDisconnectEvent e){
        AuthPlayer.getAuthPlayer(e.getPlayer().getName()).remove();
    }

}
