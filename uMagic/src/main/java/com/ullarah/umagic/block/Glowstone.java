package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;

import java.util.Arrays;
import java.util.List;

public class Glowstone extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        block.setType(Material.LIGHT, false);

        Levelled data = (Levelled) block.getBlockData();
        data.setLevel(15);
        block.setBlockData(data,false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.GLOWSTONE);
    }

}
