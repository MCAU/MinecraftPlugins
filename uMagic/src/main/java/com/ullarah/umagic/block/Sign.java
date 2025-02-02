package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.RotatableData;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class Sign extends ScrollBlock {

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        new RotatableData().process(meta);
        saveMetadata(block.getLocation(), metaSign);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.ACACIA_SIGN, Material.BIRCH_SIGN, Material.DARK_OAK_SIGN,
                Material.JUNGLE_SIGN, Material.MANGROVE_SIGN, Material.OAK_SIGN, Material.SPRUCE_SIGN, Material.CRIMSON_SIGN, Material.WARPED_SIGN,
                Material.BAMBOO_SIGN, Material.CHERRY_SIGN);
    }
}
