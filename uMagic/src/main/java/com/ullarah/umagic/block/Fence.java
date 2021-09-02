package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.MultipleFacingData;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import java.util.Arrays;
import java.util.List;

public class Fence extends ScrollBlock {

    private static final MultipleFacingData multipleFacingData;

    static {
        multipleFacingData = new MultipleFacingData(true, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST);
    }

    public void process(ScrollMeta meta) {
        multipleFacingData.process(meta);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.OAK_FENCE, Material.SPRUCE_FENCE, Material.BIRCH_FENCE,
                Material.JUNGLE_FENCE, Material.ACACIA_FENCE, Material.DARK_OAK_FENCE,
                Material.CRIMSON_FENCE, Material.WARPED_FENCE, Material.NETHER_BRICK_FENCE);
    }
}
