package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.api.player.GamerEntity;
import org.kvlt.core.bukkit.CorePlugin;

public class TestCommand implements CommandInterface {

    public TestCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("test", this);
    }

    @Override
    public void execute(GamerEntity gamerEntity, String s, String[] args) {
//        String msg = CorePlugin.getAPI().getPlayerInfo("kotebalvrot");
//        gamerEntity.sendMessage(msg);
    }
}
