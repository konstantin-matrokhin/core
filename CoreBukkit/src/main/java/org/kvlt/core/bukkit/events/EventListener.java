package org.kvlt.core.bukkit.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.json.PlayerInfo;
import org.kvlt.core.json.ServerInfo;

import java.util.List;

public class EventListener implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (e.getMessage().startsWith("@")) {
            Bukkit.getScheduler().runTaskAsynchronously(CorePlugin.getPlugin(), () -> {
                List<ServerInfo> list = CorePlugin.getAPI().getServers(e.getMessage());
                list.forEach(i -> {
                    String name = i.getName();
                    int port = i.getPort();
                    String set = i.getPlayers().toString();
                    player.sendMessage(String.format("%s | %d | %s",
                            name, port, set));
                });
            });
        }
    }

    @EventHandler
    public void onTouch(PlayerInteractEvent e) {
        CorePlugin.getAPI().getPacketResolver().responses().forEach((k, v) -> {
            e.getPlayer().sendMessage(k + " | " + v);
        });
    }

}
