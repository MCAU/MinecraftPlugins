package com.ullarah.uchest.data;

import com.ullarah.uchest.ChestInit;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class RandomChest extends ChestBase {
    public String getName() {
        return "Random Chest";
    }

    public char getShortcut() {
        return 'r';
    }

    protected List<String> getMenuLore() {
        int rChestTimer = ChestInit.getPlugin().getConfig().getInt("rchest.timer");
        return Arrays.asList(
                accessLevelRequirement(getType()),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Opens the random chest.",
                ChatColor.WHITE + "Random items in random slots,",
                ChatColor.WHITE + "at short random intervals!",
                ChatColor.RESET + "",
                ChatColor.RED + "You have to be quick to grab them!",
                ChatColor.RED + "You have " + ChatColor.YELLOW + rChestTimer + " seconds" + ChatColor.RED + "!");
    }
}
