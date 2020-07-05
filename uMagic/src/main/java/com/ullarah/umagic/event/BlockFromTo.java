package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BlockFromTo extends MagicFunctions implements Listener {
    private final Set<String> metas = new HashSet<>(Arrays.asList(metaCice, metaWate, metaLava));

    public BlockFromTo() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(BlockFromToEvent event) {

        Location locationFrom = event.getBlock().getLocation();
        Location locationTo = event.getToBlock().getLocation();

        if (magicLocations.containsKey(locationFrom) && metas.contains(magicLocations.get(locationFrom))) {
            event.setCancelled(true);
        }

        if (magicLocations.containsKey(locationTo) && metas.contains(magicLocations.get(locationTo))) {
            event.setCancelled(true);
        }
    }

}