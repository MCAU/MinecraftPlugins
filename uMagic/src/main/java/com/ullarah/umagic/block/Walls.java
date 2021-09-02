package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.ScrollElement;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Wall;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Walls extends ScrollBlock {

    private static final ScrollElement<Boolean> ups = new ScrollElement<>(Arrays.asList(true, false));
    private static final ScrollElement<Wall.Height> heights = new ScrollElement<>(Arrays.asList(Wall.Height.values()));
    private static final List<BlockFace> faces = Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST);

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();

        Wall data = (Wall) block.getBlockData();
        Map<BlockFace, Wall.Height> heightMap = new EnumMap<>(BlockFace.class);
        for (BlockFace face : faces) {
            heightMap.put(face, data.getHeight(face));
        }
        Boolean up = data.isUp();

        up = ups.scrollItem(up, meta);
        if (up.equals(ups.terminalItem(meta))) {
            for (BlockFace face : faces) {
                Wall.Height next = heights.scrollItem(heightMap.get(face), meta);
                heightMap.put(face, next);
                if (next != heights.terminalItem(meta)) {
                    break;
                }
            }
        }



        boolean allNone = true;
        for (BlockFace face : faces) {
            data.setHeight(face, heightMap.get(face));
            allNone = allNone && heightMap.get(face) == Wall.Height.NONE;
        }
        data.setUp(up || allNone); // Cannot have all walls as NONE and up as false
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.ANDESITE_WALL, Material.BLACKSTONE_WALL, Material.BRICK_WALL,
                Material.COBBLED_DEEPSLATE_WALL, Material.COBBLESTONE_WALL, Material.DEEPSLATE_BRICK_WALL,
                Material.DEEPSLATE_TILE_WALL, Material.DIORITE_WALL, Material.END_STONE_BRICK_WALL,
                Material.GRANITE_WALL, Material.MOSSY_COBBLESTONE_WALL, Material.MOSSY_STONE_BRICK_WALL,
                Material.NETHER_BRICK_WALL, Material.POLISHED_BLACKSTONE_WALL, Material.POLISHED_BLACKSTONE_BRICK_WALL,
                Material.POLISHED_DEEPSLATE_WALL, Material.PRISMARINE_WALL, Material.RED_NETHER_BRICK_WALL,
                Material.RED_SANDSTONE_WALL, Material.SANDSTONE_WALL, Material.STONE_BRICK_WALL
        );
    }

}
