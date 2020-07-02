package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Lightable;

import java.util.Arrays;
import java.util.List;

public class Lamp extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Location location = block.getLocation();
        Lightable data = (Lightable) block.getBlockData();

        if (magicLocations.containsKey(location) && magicLocations.get(location).equals(metaLamp)) {
            data.setLit(false);
            removeMetadata(location);
        } else {
            data.setLit(true);
            saveMetadata(location, metaLamp);
        }

        block.setBlockData(data, false);

    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.REDSTONE_LAMP);
    }

}
