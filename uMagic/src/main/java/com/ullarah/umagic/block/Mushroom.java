package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.MultipleFacingData;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import java.util.Arrays;
import java.util.List;

public class Mushroom extends ScrollBlock {

    private static final MultipleFacingData multipleFacingData;

    static {
        multipleFacingData = new MultipleFacingData(true, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN);
    }

    public void process(ScrollMeta meta) {
        multipleFacingData.process(meta);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.RED_MUSHROOM_BLOCK, Material.BROWN_MUSHROOM_BLOCK, Material.MUSHROOM_STEM);
    }

}
