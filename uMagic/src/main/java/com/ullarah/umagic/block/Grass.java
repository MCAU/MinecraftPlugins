package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Snowable;

import java.util.Arrays;
import java.util.List;

public class Grass extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Snowable data = (Snowable) block.getBlockData();
        if (data.isSnowy()) {
            data.setSnowy(false);
        } else {
            data.setSnowy(true);
        }
        block.setBlockData(data,false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.GRASS_BLOCK);
    }

}
