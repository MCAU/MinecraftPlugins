package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Powerable;

import java.util.Arrays;
import java.util.List;

public class Plate extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();

        Powerable data = (Powerable) block.getBlockData();
        data.setPowered(!data.isPowered());
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.ACACIA_PRESSURE_PLATE, Material.BIRCH_PRESSURE_PLATE, Material.DARK_OAK_PRESSURE_PLATE,
                Material.JUNGLE_PRESSURE_PLATE, Material.MANGROVE_PRESSURE_PLATE, Material.OAK_PRESSURE_PLATE,
	       	    Material.SPRUCE_PRESSURE_PLATE, Material.STONE_PRESSURE_PLATE, Material.CRIMSON_PRESSURE_PLATE,
	       	    Material.WARPED_PRESSURE_PLATE, Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
                Material.BAMBOO_PRESSURE_PLATE, Material.CHERRY_PRESSURE_PLATE);
    }
}
