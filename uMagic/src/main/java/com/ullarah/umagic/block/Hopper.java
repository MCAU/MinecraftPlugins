package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.blockdata.DirectionalData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.Collections;
import java.util.List;

public class Hopper extends ScrollBlock {

    private static final DirectionalData DATA = new DirectionalData(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.DOWN);

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        DATA.process(meta);
        saveMetadata(block.getLocation(), metaSign);
    }

    public List<Material> getPermittedBlocks() {
        return Collections.singletonList(Material.HOPPER);
    }
}
