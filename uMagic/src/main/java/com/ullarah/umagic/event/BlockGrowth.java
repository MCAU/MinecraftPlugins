package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BlockGrowth extends MagicFunctions implements Listener {

    public BlockGrowth() {
        super(false);
    }
    private final Set<String> metas = new HashSet<>(Arrays.asList(metaVine, metaCact, metaCice));
    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(BlockGrowEvent event) {

        Location location = event.getBlock().getLocation();
        if (magicLocations.containsKey(location) && metas.contains(magicLocations.get(location))) {
            event.setCancelled(true);
        }
    }

}
