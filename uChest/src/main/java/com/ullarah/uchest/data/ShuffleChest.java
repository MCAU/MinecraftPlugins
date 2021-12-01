package com.ullarah.uchest.data;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class ShuffleChest extends ChestBase {
    public String getName() {
        return "Shuffle Chest";
    }

    public char getShortcut() {
        return 's';
    }

    protected List<String> getMenuLore() {
        return Arrays.asList(
                accessLevelRequirement(getType()),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Displays a random assortment of items.",
                ChatColor.WHITE + "Shuffles them around very quickly!",
                ChatColor.RESET + "",
                ChatColor.GREEN + "You gotta be quick with this one!",
                ChatColor.GREEN + "Item will drop at your location.");
    }
}
