package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.storages.PremiumQueue;

public class LicenseCommand extends Command {

    public LicenseCommand() {
        super("license");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) return;
        ProxiedPlayer player = (ProxiedPlayer) sender;
        String name = player.getName();

        if (!PremiumQueue.has(name)) {
            PremiumQueue.add(name);
            player.sendMessage(new TextComponent("У тебя 5 минут, чтобы перезайти с лицухи, я жду."));
        }
    }
}
