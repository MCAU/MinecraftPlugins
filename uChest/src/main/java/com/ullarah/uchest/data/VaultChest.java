package com.ullarah.uchest.data;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class VaultChest extends ChestBase {
    public String getName() {
        return "Vault Chest";
    }

    public char getShortcut() {
        return 'v';
    }

    protected List<String> getMenuLore() {
        return Arrays.asList(
                accessLevelRequirement(getType()),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Opens your personal vault chest.",
                ChatColor.WHITE + "Safely store your items away at a price!",
                ChatColor.RESET + "",
                ChatColor.DARK_AQUA + "Upgrade your vault using " + ChatColor.AQUA + "/vchest upgrade");
    }
}
