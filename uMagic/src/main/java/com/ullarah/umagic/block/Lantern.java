package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class Lantern extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        org.bukkit.block.data.type.Lantern data = (org.bukkit.block.data.type.Lantern) block.getBlockData();

        data.setHanging(!data.isHanging());
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.LANTERN, Material.SOUL_LANTERN);
    }

}
