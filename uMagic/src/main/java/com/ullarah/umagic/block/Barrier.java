package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Barrier extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Player player = meta.getPlayer();
        Location location = block.getLocation();

        if (magicLocations.containsKey(location) && magicLocations.get(location).equals(metaEmBr)) {
            getCommonString().messageSend(player, "You breathe a sigh of relief");
            block.setType(Material.EMERALD_BLOCK, false);
            removeMetadata(block.getLocation());
        }

    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.BARRIER);
    }
}
