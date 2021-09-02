package com.ullarah.umagic.blockdata;

import com.ullarah.umagic.ScrollMeta;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MultipleFacingData {

    private final int minimum;
    private final HashMap<BlockFace, Integer> faces;

    public MultipleFacingData(boolean allowNone, BlockFace... permitted) {
        this.minimum = allowNone ? 0 : 1;
        this.faces = new LinkedHashMap<>();

        int val = 1;
        // Add each successive face with values 1, 2, 4, 8...
        for (BlockFace face : permitted) {
            faces.put(face, val);
            val *= 2;
        }
    }

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        MultipleFacing data = (MultipleFacing) block.getBlockData();

        int sum = 0;
        for (BlockFace face : faces.keySet()) {
            if (data.hasFace(face))
                sum += faces.get(face);
        }

        // Increment, wrap-around 2^n - 1
        int max = (int) Math.pow(2, faces.size()) - 1;
        sum += meta.delta();
        if (sum > max) {
            sum = minimum;
        } else if (sum < minimum) {
            sum = max;
        }

        for (BlockFace face : faces.keySet()) {
            data.setFace(face, sum % 2 == 1);
            sum /= 2;
        }

        block.setBlockData(data, false);
    }
}
