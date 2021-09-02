package com.ullarah.umagic.blockdata;

import com.ullarah.umagic.ScrollMeta;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

import java.util.Arrays;
import java.util.List;

public class DirectionalData {

    private static final ScrollElement<BlockFace> directions = new ScrollElement<>(Arrays.asList(
            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST));

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        Directional data = (Directional) block.getBlockData();
        BlockFace facing = data.getFacing();

        facing = directions.scrollItem(facing, meta);
        data.setFacing(facing);
        block.setBlockData(data, false);
    }
}
