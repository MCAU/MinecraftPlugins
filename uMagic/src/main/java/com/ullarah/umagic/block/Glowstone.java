package com.ullarah.umagic.block;

import com.ullarah.umagic.ScrollMeta;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Glowstone extends ScrollBlock {

    public void process(ScrollMeta meta) {
        Block block = meta.getBlock();
        Player player = meta.getPlayer();

        if (block.getType() == Material.GLOWSTONE) {
            block.setType(Material.LIGHT, false);
        }

        Levelled data = (Levelled) block.getBlockData();
        int level = data.getLevel();
        int mod = data.getMaximumLevel() + 1;
        level = (level + meta.delta() + mod) % mod;
        getActionMessage().message(player, ChatColor.RED, "Light level: " + level);

        data.setLevel(level);
        block.setBlockData(data,false);
    }

    public List<Material> getPermittedBlocks() {
        return Arrays.asList(Material.GLOWSTONE, Material.LIGHT);
    }

}
