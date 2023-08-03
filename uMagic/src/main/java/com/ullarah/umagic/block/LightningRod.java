package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Powerable;

import java.util.Collections;
import java.util.List;

public class LightningRod extends BaseBlock {
    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Powerable data = (Powerable) block.getBlockData();
        data.setPowered(!data.isPowered());
        block.setBlockData(data, true);
    }

    public List<Material> getPermittedBlocks() {
        return Collections.singletonList(Material.LIGHTNING_ROD);
    }
}
