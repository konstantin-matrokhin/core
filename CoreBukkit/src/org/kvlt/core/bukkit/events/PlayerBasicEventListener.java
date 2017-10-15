package org.kvlt.core.bukkit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kvlt.core.bukkit.ConfigManager;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.entities.CorePlayer;
import org.kvlt.core.packets.PlayerJoinPacket;

public class PlayerBasicEventListener implements Listener {

   @EventHandler
   public void onJoin(PlayerJoinEvent event) {
       Player p = event.getPlayer();
       CorePlayer cp = new CorePlayer();

       cp.setName(p.getName());
       cp.setUuid(p.getUniqueId());

       PlayerJoinPacket joinPacket = new PlayerJoinPacket(cp, ConfigManager.getClientName());

       CorePlugin.get().getCoreServer().writeAndFlush(joinPacket);
   }

}
