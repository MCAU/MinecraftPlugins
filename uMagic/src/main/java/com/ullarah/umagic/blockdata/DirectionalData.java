package com.ullarah.umagic.blockdata;

import com.ullarah.umagic.ScrollMeta;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;

import java.util.Arrays;

public class DirectionalData {

    private static final BlockFace[] DEFAULT = new BlockFace[] { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };

    private final ScrollElement<BlockFace> directions;

    public DirectionalData() {
        this(DEFAULT);
    }

    public DirectionalData(BlockFace... faces) {
        directions = new ScrollElement<>(Arrays.asList(faces));
    }

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        Directional data = (Directional) block.getBlockData();
        BlockFace facing = data.getFacing();

        facing = directions.scrollItem(facing, meta);
        data.setFacing(facing);
        block.setBlockData(data, false);
    }
}
