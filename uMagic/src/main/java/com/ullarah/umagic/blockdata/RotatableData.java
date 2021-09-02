package com.ullarah.umagic.blockdata;

import com.ullarah.umagic.ScrollMeta;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rotatable;

import java.util.Arrays;

public class RotatableData {

    private static final ScrollElement<BlockFace> directions = new ScrollElement<>(Arrays.asList(
            BlockFace.NORTH, BlockFace.NORTH_NORTH_EAST, BlockFace.NORTH_EAST, BlockFace.EAST_NORTH_EAST,
            BlockFace.EAST, BlockFace.EAST_SOUTH_EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH_SOUTH_EAST,
            BlockFace.SOUTH, BlockFace.SOUTH_SOUTH_WEST, BlockFace.SOUTH_WEST, BlockFace.WEST_SOUTH_WEST,
            BlockFace.WEST, BlockFace.WEST_NORTH_WEST, BlockFace.NORTH_WEST, BlockFace.NORTH_NORTH_WEST));

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        Rotatable data = (Rotatable) block.getBlockData();
        BlockFace rotation = data.getRotation();

        rotation = directions.scrollItem(rotation, meta);
        data.setRotation(rotation);
        block.setBlockData(data, false);
    }
}
