package com.ullarah.uchest.data;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class ExperienceChest extends ChestBase {
    public String getName() {
        return "Experience Chest";
    }

    public char getShortcut() {
        return 'x';
    }

    protected List<String> getMenuLore() {
        return Arrays.asList(
                accessLevelRequirement(getType()),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Opens the experience chest.",
                ChatColor.WHITE + "Allows you to convert items to XP!",
                ChatColor.RESET + "",
                ChatColor.RED + "Some items do not return XP!");
    }
}
