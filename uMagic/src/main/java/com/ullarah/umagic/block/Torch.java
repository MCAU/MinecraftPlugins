package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

import java.util.Arrays;
import java.util.List;

public class Torch extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();

        Directional data = (Directional) block.getBlockData();
        BlockFace facing = data.getFacing();
        int index = (facing.getOppositeFace().ordinal() + 1) % 4;
        data.setFacing(BlockFace.values()[index]);

        saveMetadata(block.getLocation(), metaTrch);
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.WALL_TORCH, Material.SOUL_WALL_TORCH, Material.REDSTONE_WALL_TORCH);
    }

}
