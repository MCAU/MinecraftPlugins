package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.DirectionalData;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class SignWall extends ScrollBlock {

    private static final DirectionalData DATA = new DirectionalData();

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        DATA.process(meta);
        saveMetadata(block.getLocation(), metaSign);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.ACACIA_WALL_SIGN, Material.BIRCH_WALL_SIGN, Material.DARK_OAK_WALL_SIGN,
                Material.JUNGLE_WALL_SIGN, Material.MANGROVE_WALL_SIGN, Material.OAK_WALL_SIGN,
	       	Material.SPRUCE_WALL_SIGN, Material.CRIMSON_WALL_SIGN, Material.WARPED_WALL_SIGN);
    }

}
