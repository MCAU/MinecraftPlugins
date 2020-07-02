package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;

import java.util.Arrays;
import java.util.List;

public class Magma extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Location location = block.getLocation();

        block.setType(Material.LAVA, false);
        Levelled data = (Levelled) block.getBlockData();
        data.setLevel(7);
        block.setBlockData(data, false);
        saveMetadata(block.getLocation(), metaLava);

    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.MAGMA_BLOCK);
    }

}
