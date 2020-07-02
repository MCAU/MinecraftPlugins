package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockRedstone extends MagicFunctions implements Listener {

    public BlockRedstone() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(BlockRedstoneEvent event) {
        Location location = event.getBlock().getLocation();
        if (magicLocations.containsKey(location) && magicLocations.get(location).equals(metaLamp)) {
            event.setNewCurrent(15);
        }
    }

}
