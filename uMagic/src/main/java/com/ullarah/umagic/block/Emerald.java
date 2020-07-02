package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Emerald extends BaseBlock {

    public void process(InteractMeta meta) {
        Block block = meta.getBlock();
        Player player = meta.getPlayer();

        if (player.hasPermission("umagic.danger")) {
            getCommonString().messageSend(player, "Block converted to Bedrock. Be careful!");
            block.setType(Material.BEDROCK, false);
            saveMetadata(block.getLocation(), metaEmBr);
        }
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.EMERALD_BLOCK);
    }
}
