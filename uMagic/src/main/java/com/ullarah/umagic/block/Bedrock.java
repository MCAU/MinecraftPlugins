package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Bedrock extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Player player = meta.getPlayer();
        Location location = block.getLocation();

        if (magicLocations.containsKey(location) && magicLocations.get(location).equals(metaEmBr)) {
            getCommonString().messageSend(player, "Block converted to Barrier. Be careful!");
            block.setType(Material.BARRIER, false);
        }

    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.BEDROCK);
    }

}
