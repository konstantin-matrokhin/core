package org.kvlt.core.bukkit.corehandlers;

import net.lastcraft.api.LastCraft;
import net.lastcraft.api.inventory.DInventory;
import net.lastcraft.api.inventory.DItem;
import net.lastcraft.api.inventory.InventoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class MyLife {

    private static InventoryAPI api = LastCraft.getInventoryAPI();

    public MyLife() {
        DInventory dinv = api.createInventory("Toster", 1);
        DItem item = api.createItem(new ItemStack(Material.BED), (player, clickType, i) -> {
            player.setGameMode(GameMode.CREATIVE);
            if (clickType.isKeyboardClick()) {
                player.sendMessage("ketyboard");
            }
        });
        dinv.setItem(5, item);
        dinv.openInventory(Bukkit.getPlayer("kvlt"));
    }
}
