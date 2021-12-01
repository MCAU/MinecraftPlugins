package com.ullarah.uchest.data;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class SwapChest extends ChestBase {
    public String getName() {
        return "Swap Chest";
    }

    public char getShortcut() {
        return 'w';
    }

    protected List<String> getMenuLore() {
        return Arrays.asList(
                accessLevelRequirement(getType()),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Opens the swapping chest.",
                ChatColor.WHITE + "Put random items in, get random items out!",
                ChatColor.RESET + "",
                ChatColor.RED + "This chest is player supported!");
    }
}
