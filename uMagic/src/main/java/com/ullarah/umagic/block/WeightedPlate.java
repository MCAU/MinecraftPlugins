package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.AnaloguePowerable;

import java.util.Arrays;
import java.util.List;

public class WeightedPlate extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();

        AnaloguePowerable data = (AnaloguePowerable) block.getBlockData();
        if (data.getPower() == 15) {
            data.setPower(0);
            }
        else {
            data.setPower(15);
        }
        block.setBlockData(data, false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(
                Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
    }
}
