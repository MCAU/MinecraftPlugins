package com.ullarah.uchest.data;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class EnchantmentChest extends ChestBase {
    public String getName() {
        return "Enchantment Chest";
    }

    public char getShortcut() {
        return 'e';
    }

    protected List<String> getMenuLore() {
        return Arrays.asList(
                accessLevelRequirement(getType()),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Opens the enchantment chest.",
                ChatColor.WHITE + "Enchant weapons or armour with a",
                ChatColor.WHITE + "completely random enchantment!",
                ChatColor.RESET + "",
                ChatColor.RED + "Not all items can be enchanted!");
    }
}
