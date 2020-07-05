package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BlockPhysics extends MagicFunctions implements Listener {
    private final Set<String> metas = new HashSet<>(Arrays.asList(metaSand, metaWool, metaLadd, metaRail, metaCact,
            metaLava, metaSign, metaTrch, metaBanr, metaVine, metaBeds, metaFire, metaSnow, metaCice, metaWate, metaReds));
    public BlockPhysics() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(BlockPhysicsEvent event) {

        BlockFace[] faces = new BlockFace[]{BlockFace.SELF, BlockFace.UP, BlockFace.DOWN,
                BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
        Block block = event.getBlock();

        for (BlockFace face : faces) {
            Location location = block.getRelative(face).getLocation();
            if (magicLocations.containsKey(location)
            && metas.contains(magicLocations.get(location))) {
                event.setCancelled(true);
            }
        }
    }
}
