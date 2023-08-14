package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class Barrier extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        block.setType(Material.EMERALD_BLOCK, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.BARRIER);
    }
}
