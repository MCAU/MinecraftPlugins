package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class Ladder extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Location location = block.getLocation();

        if (magicLocations.containsKey(location) && magicLocations.get(location).equals(metaLadd)) {
            block.setType(Material.OAK_PLANKS, false);
            removeMetadata(block.getLocation());
        }
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.LADDER);
    }

}
