package com.ullarah.uchest.data;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class MoneyChest extends ChestBase {
    public String getName() {
        return "Money Chest";
    }

    public char getShortcut() {
        return 'm';
    }

    protected List<String> getMenuLore() {
        return Arrays.asList(
                accessLevelRequirement(getType()),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Opens the money chest.",
                ChatColor.WHITE + "Allows you to convert items to money!",
                ChatColor.RESET + "",
                ChatColor.RED + "Some items do not return money!");
    }
}
