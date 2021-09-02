package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.ScrollElement;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BedHalf extends ScrollBlock {

    private static final ScrollElement<BlockFace> faces = new ScrollElement<>(Arrays.asList(
            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST));

    private static final ScrollElement<Bed.Part> parts = new ScrollElement<>(Arrays.asList(
            Bed.Part.FOOT, Bed.Part.HEAD));

    private static final ScrollElement<Material> beds = new ScrollElement<>(Arrays.asList(
            Material.RED_BED, Material.ORANGE_BED, Material.YELLOW_BED, Material.LIME_BED,
            Material.GREEN_BED, Material.BLUE_BED, Material.LIGHT_BLUE_BED, Material.CYAN_BED,
            Material.LIGHT_GRAY_BED, Material.GRAY_BED, Material.BLACK_BED, Material.BROWN_BED,
            Material.MAGENTA_BED, Material.PURPLE_BED, Material.PINK_BED, Material.WHITE_BED));

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

        BlockFace facing = data.getFacing();
        Bed.Part part = data.getPart();

        facing = faces.scrollItem(facing, meta.delta());
        if (facing == faces.terminalItem(meta.isForward())) {
            part = parts.scrollItem(part, meta.delta());
            if (part == parts.terminalItem(meta.isForward())) {
                Material next = beds.scrollItem(block.getType(), meta.delta());
                block.setType(next, false);
            }
        }

        data = (Bed) block.getBlockData();
        data.setFacing(facing);
        data.setPart(part);

        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        List<Material> list = new ArrayList<>(beds.getList());
        list.add(Material.HAY_BLOCK);
        return list;
    }
}
