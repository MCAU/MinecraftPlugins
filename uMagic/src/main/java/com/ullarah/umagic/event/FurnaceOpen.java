package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.FurnaceInventory;

public class FurnaceOpen extends MagicFunctions implements Listener {

    public FurnaceOpen() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(InventoryOpenEvent event) {

        if (event.getInventory() instanceof FurnaceInventory && usingMagicHoe((Player) event.getPlayer())) {
            event.setCancelled(true);
            return;
        }
    }

}
