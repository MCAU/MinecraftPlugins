package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.TrapDoor;

import java.util.Arrays;
import java.util.List;

public class Trapdoor extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();

        TrapDoor data = (TrapDoor) block.getBlockData();
        data.setOpen(!data.isOpen());

        block.setBlockData(data,true);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.ACACIA_TRAPDOOR, Material.BIRCH_TRAPDOOR, Material.DARK_OAK_TRAPDOOR,
                Material.JUNGLE_TRAPDOOR, Material.MANGROVE_TRAPDOOR, Material.OAK_TRAPDOOR,
	       	    Material.SPRUCE_TRAPDOOR, Material.IRON_TRAPDOOR, Material.CRIMSON_TRAPDOOR,
	       	    Material.WARPED_TRAPDOOR, Material.BAMBOO_TRAPDOOR, Material.CHERRY_TRAPDOOR,
                Material.PALE_OAK_TRAPDOOR, Material.COPPER_TRAPDOOR, Material.EXPOSED_COPPER_TRAPDOOR,
                Material.OXIDIZED_COPPER_TRAPDOOR, Material.WAXED_COPPER_TRAPDOOR, Material.WAXED_EXPOSED_COPPER_TRAPDOOR,
                Material.WAXED_OXIDIZED_COPPER_TRAPDOOR, Material.WAXED_WEATHERED_COPPER_TRAPDOOR,
                Material.WEATHERED_COPPER_TRAPDOOR);
    }

}
