package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BlockForm extends MagicFunctions implements Listener {
    private final Set<String> metas = new HashSet<>(Arrays.asList(metaWate, metaLava, metaCice));
    public BlockForm() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(BlockFormEvent event) {
        Location location = event.getBlock().getLocation();
        if (magicLocations.containsKey((location))
                && metas.contains(magicLocations.get(location))) {
            event.setCancelled(true);
        }
    }

}
