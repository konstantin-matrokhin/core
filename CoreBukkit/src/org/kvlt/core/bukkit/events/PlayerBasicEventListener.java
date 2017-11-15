package org.kvlt.core.bukkit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.bukkit.utils.BukkitPlayerAdapter;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.player.PlayerQuitServerPacket;

public class PlayerBasicEventListener implements Listener {

   @EventHandler
   public void onJoin(PlayerJoinEvent event) {
       Player p = event.getPlayer();
       OnlinePlayer op = BukkitPlayerAdapter.asOnlinePlayer(p);

       //PlayerJoinPacket joinPacket = new PlayerJoinPacket(op, ConfigManager.getClientName());
       //CorePlugin.get().getCoreServer().writeAndFlush(joinPacket);
   }

   @EventHandler
    public void onLeave(PlayerQuitEvent event) {
       Player p = event.getPlayer();
       OnlinePlayer op = BukkitPlayerAdapter.asOnlinePlayer(p);

//       PlayerQuitServerPacket leavePacket = new PlayerQuitServerPacket(op);
//       CorePlugin.get().getCoreServer().writeAndFlush(leavePacket);
   }

}
