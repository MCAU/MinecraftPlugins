package com.ullarah.uchest.data;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class DonationChest extends ChestBase {
    public String getName() {
        return "Donation Chest";
    }

    public char getShortcut() {
        return 'd';
    }

    protected List<String> getMenuLore() {
        return Arrays.asList(
                accessLevelRequirement(getType()),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Opens the donation chest.",
                ChatColor.WHITE + "Give what you can, take what you need!",
                ChatColor.RESET + "",
                ChatColor.RED + "This chest is player supported!");
    }
}
