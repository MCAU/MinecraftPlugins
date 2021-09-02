package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.ScrollElement;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.Arrays;
import java.util.List;

public class Stairs extends ScrollBlock {

    private static final ScrollElement<org.bukkit.block.data.type.Stairs.Shape> shapes = new ScrollElement<>(Arrays.asList(
            org.bukkit.block.data.type.Stairs.Shape.values()));

    private static final ScrollElement<BlockFace> faces = new ScrollElement<>(Arrays.asList(
            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST));

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();

        org.bukkit.block.data.type.Stairs data = (org.bukkit.block.data.type.Stairs) block.getBlockData();
        BlockFace facing = data.getFacing();
        org.bukkit.block.data.type.Stairs.Shape shape = data.getShape();

        shape = shapes.scrollItem(shape, meta);
        if (shape == shapes.terminalItem(meta)) {
            facing = faces.scrollItem(facing, meta);
        }

        data.setShape(shape);
        data.setFacing(facing);
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.ACACIA_STAIRS, Material.ANDESITE_STAIRS, Material.BIRCH_STAIRS,
                Material.BLACKSTONE_STAIRS, Material.BRICK_STAIRS, Material.COBBLED_DEEPSLATE_STAIRS,
                Material.COBBLESTONE_STAIRS, Material.CRIMSON_STAIRS, Material.CUT_COPPER_STAIRS,
                Material.DARK_OAK_STAIRS, Material.DARK_PRISMARINE_STAIRS, Material.DEEPSLATE_BRICK_STAIRS,
                Material.DEEPSLATE_TILE_STAIRS, Material.DIORITE_STAIRS, Material.END_STONE_BRICK_STAIRS,
                Material.EXPOSED_CUT_COPPER_STAIRS, Material.GRANITE_STAIRS, Material.JUNGLE_STAIRS,
                Material.MOSSY_COBBLESTONE_STAIRS, Material.MOSSY_STONE_BRICK_STAIRS, Material.NETHER_BRICK_STAIRS,
                Material.OAK_STAIRS, Material.OXIDIZED_CUT_COPPER_STAIRS, Material.POLISHED_ANDESITE_STAIRS,
                Material.POLISHED_BLACKSTONE_BRICK_STAIRS, Material.POLISHED_BLACKSTONE_STAIRS, Material.POLISHED_DEEPSLATE_STAIRS,
                Material.POLISHED_DIORITE_STAIRS, Material.POLISHED_GRANITE_STAIRS, Material.PRISMARINE_BRICK_STAIRS,
                Material.PRISMARINE_STAIRS, Material.PURPUR_STAIRS, Material.QUARTZ_STAIRS,
                Material.RED_NETHER_BRICK_STAIRS, Material.RED_SANDSTONE_STAIRS, Material.SANDSTONE_STAIRS,
                Material.SMOOTH_QUARTZ_STAIRS, Material.SMOOTH_RED_SANDSTONE_STAIRS, Material.SMOOTH_SANDSTONE_STAIRS,
                Material.SPRUCE_STAIRS, Material.STONE_BRICK_STAIRS, Material.STONE_STAIRS,
                Material.WARPED_STAIRS, Material.WAXED_CUT_COPPER_STAIRS, Material.WAXED_EXPOSED_CUT_COPPER_STAIRS,
                Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS, Material.WAXED_WEATHERED_CUT_COPPER_STAIRS, Material.WEATHERED_CUT_COPPER_STAIRS);
    }

}
