package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Gate;

import java.util.Arrays;
import java.util.List;

public class FenceGate extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Gate data = (Gate) block.getBlockData();
        data.setInWall(!data.isInWall());
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.OAK_FENCE_GATE, Material.SPRUCE_FENCE_GATE, Material.BIRCH_FENCE_GATE,
                Material.JUNGLE_FENCE_GATE, Material.ACACIA_FENCE_GATE, Material.DARK_OAK_FENCE_GATE,
                Material.CRIMSON_FENCE_GATE, Material.WARPED_FENCE_GATE, Material.BAMBOO_FENCE_GATE,
                Material.CHERRY_FENCE_GATE, Material.PALE_OAK_FENCE_GATE);
    }
}
