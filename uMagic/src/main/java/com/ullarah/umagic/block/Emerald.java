package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class Emerald extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        block.setType(Material.BARRIER, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.EMERALD_BLOCK);
    }
}
