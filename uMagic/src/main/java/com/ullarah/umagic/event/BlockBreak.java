package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockBreak extends MagicFunctions implements Listener {

    private static final List<Material> backingBlocks = Arrays.asList(
            Material.BLACK_STAINED_GLASS, Material.BLUE_STAINED_GLASS, Material.BROWN_STAINED_GLASS, Material.CYAN_STAINED_GLASS,
            Material.GRAY_STAINED_GLASS, Material.GREEN_STAINED_GLASS, Material.LIGHT_BLUE_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS,
            Material.LIME_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.PINK_STAINED_GLASS,
            Material.PURPLE_STAINED_GLASS, Material.RED_STAINED_GLASS, Material.WHITE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS,
            Material.GLASS
    );

    private final Set<String> metas = new HashSet<>(Arrays.asList(metaSand, metaLamp, metaWool, metaEmBr, metaVine, metaFurn,
            metaReds, metaLadd, metaRail, metaSign, metaTrch, metaBanr, metaBeds, metaFire, metaSnow, metaCice));

    public BlockBreak() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(BlockBreakEvent event) {

        if (usingMagicHoe(event.getPlayer())) {
            event.setCancelled(true);
            return;
        }

        Block block = event.getBlock();
        Location location = block.getLocation();

        if (magicLocations.containsKey(location)) {

            if (metas.contains(magicLocations.get(location))) {
                if (magicLocations.get(location).equalsIgnoreCase(metaBeds)) {
                    block.setType(Material.HAY_BLOCK);
                }
                removeMetadata(block.getLocation());
            }

        }

        if (backingBlocks.contains(block.getType())) {
            for (BlockFace face : new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST}) {

                Block blockNext = block.getRelative(face);
                Location blockNextLocation = blockNext.getLocation();

                if (magicLocations.containsKey(blockNextLocation)
                        && magicLocations.get(blockNextLocation).contains(metaLadd)) {
                    blockNext.setType(Material.OAK_PLANKS);
                    removeMetadata(blockNextLocation);
                }
            }
        }
    }
}