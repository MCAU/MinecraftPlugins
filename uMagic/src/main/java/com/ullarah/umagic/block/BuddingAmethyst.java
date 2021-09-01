package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.Collections;
import java.util.List;

public class BuddingAmethyst extends BaseBlock {
    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        BlockFace face = meta.getFace();

        Block newPos = block.getRelative(face.getOppositeFace());
        Material newType = newPos.getType();
        Material oldType = block.getType();

        if (newType.isAir()) {
            block.setType(newType, false);
            newPos.setType(oldType, false);
        }
    }

    public List<Material> getPermittedBlocks() {
        return Collections.singletonList(Material.BUDDING_AMETHYST);
    }
}
