package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.MultipleFacingData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.Arrays;
import java.util.List;

public class Vines extends ScrollBlock {

    private static final MultipleFacingData multipleFacingData;

    static {
        multipleFacingData = new MultipleFacingData(false, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP);
    }

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        multipleFacingData.process(meta);
        saveMetadata(block.getLocation(), metaVine);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.VINE);
    }
}
