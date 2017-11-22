package org.kvlt.core.bukkit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kvlt.core.bukkit.datastorage.LoggedPlayers;
import org.kvlt.core.bukkit.utils.BukkitPlayerAdapter;
import org.kvlt.core.entities.OnlinePlayer;

import java.util.ArrayList;
import java.util.List;

public class AuthEventsListener implements Listener {

    static final String LOGIN_PLEASE = "Пожалуйста, введите пароль или зарегистрируйтесь";

    static List<String> allowedCommands = new ArrayList<String>() {{
        add("register");
        add("reg");
        add("login");
        add("l");
        add("email");
    }};

    @EventHandler
    public void onCommandPreproccess(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        String cmd = event.getMessage();

        if (!LoggedPlayers.isLogged(p) && !isAllowedCommand(cmd)) {
            p.sendMessage(LOGIN_PLEASE);
            p.sendMessage(cmd);
            event.setCancelled(true);
        }
    }

    private boolean isAllowedCommand(String cmd) {
        return allowedCommands.stream().anyMatch(str -> cmd.startsWith("/" + str + " "));
    }

}
