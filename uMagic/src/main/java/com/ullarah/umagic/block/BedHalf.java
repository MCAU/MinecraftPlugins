package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import com.ullarah.umagic.ScrollMeta;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BedHalf extends ScrollBlock {

    private static final List<Material> beds = Arrays.asList(
            Material.RED_BED, Material.ORANGE_BED, Material.YELLOW_BED, Material.LIME_BED,
            Material.GREEN_BED, Material.BLUE_BED, Material.LIGHT_BLUE_BED, Material.CYAN_BED,
            Material.LIGHT_GRAY_BED, Material.GRAY_BED, Material.BLACK_BED, Material.BROWN_BED,
            Material.MAGENTA_BED, Material.PURPLE_BED, Material.PINK_BED, Material.WHITE_BED
    );

    private static final List<BlockFace> faces = Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST);

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Location location = block.getLocation();

        if (block.getType() == Material.HAY_BLOCK) {
            block.setType(Material.RED_BED, false);
            saveMetadata(location, metaBeds);
            return;
        }

        if (magicLocations.get(location).equals(metaBeds)) {
            enableScroll(meta);
        }
    }

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        Bed data = (Bed) block.getBlockData();

        int delta = meta.isForward() ? 1 : -1;
        BlockFace terminalFacing = meta.isForward() ? BlockFace.NORTH : BlockFace.WEST;
        Bed.Part terminalPart = meta.isForward() ? Bed.Part.FOOT : Bed.Part.HEAD;

        BlockFace facing = data.getFacing();
        Bed.Part part = data.getPart();

        facing = scrollFacing(facing, delta);
        if (facing == terminalFacing) {
            part = scrollPart(part);

            if (part == terminalPart) {
                Material next = scrollMaterial(block.getType(), delta);
                block.setType(next, false);
            }
        }

        data = (Bed) block.getBlockData();
        data.setFacing(facing);
        data.setPart(part);

        block.setBlockData(data, false);
    }

    private BlockFace scrollFacing(BlockFace facing, int delta) {
        int size = faces.size();
        return faces.get((faces.indexOf(facing) + delta + size) % size);
    }

    private Bed.Part scrollPart(Bed.Part part) {
        return (part == Bed.Part.HEAD) ? Bed.Part.FOOT : Bed.Part.HEAD;
    }

    private Material scrollMaterial(Material type, int delta) {
        int size = beds.size();
        return beds.get((beds.indexOf(type) + delta + size) % size);
    }

    public List<Material> getPermittedBlocks() {
        List<Material> list = new ArrayList<>(beds);
        list.add(Material.HAY_BLOCK);
        return list;
    }
}
