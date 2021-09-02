package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.RotatableData;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class Banner extends ScrollBlock {

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        new RotatableData().process(meta);
        saveMetadata(block.getLocation(), metaBanr);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.BLACK_BANNER, Material.BLUE_BANNER, Material.BROWN_BANNER, Material.CYAN_BANNER,
                Material.GRAY_BANNER, Material.GREEN_BANNER, Material.LIGHT_BLUE_BANNER, Material.LIGHT_GRAY_BANNER,
                Material.LIME_BANNER, Material.MAGENTA_BANNER, Material.ORANGE_BANNER, Material.PINK_BANNER,
                Material.PURPLE_BANNER, Material.RED_BANNER, Material.WHITE_BANNER, Material.YELLOW_BANNER);
    }
}
