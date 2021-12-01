package com.ullarah.uchest.data;

import com.ullarah.uchest.ChestInit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HoldChest extends ChestBase {
    public String getName() {
        return "Hold Chest";
    }

    public char getShortcut() {
        return 'h';
    }

    protected List<String> getMenuLore() {
        List<String> holdChestMessage = new ArrayList<>();

        List<String> holdChestStartMessage = Arrays.asList(
                accessLevelRequirement("hchest"),
                ChatColor.RESET + "",
                ChatColor.WHITE + "Opens your personal hold chest.",
                ChatColor.WHITE + "Store more items on the go!"
        );

        List<String> holdChestDeathMessage = Arrays.asList(
                ChatColor.RESET + "",
                ChatColor.RED + "Chest will reset back to 9 slots,",
                ChatColor.RED + "and drop it's contents on death!"
        );

        List<String> holdChestEndMessage = Arrays.asList(
                ChatColor.RESET + "",
                ChatColor.GREEN + "Hold chest will upgrade",
                ChatColor.GREEN + "automatically as you gain XP!",
                ChatColor.RESET + "",
                "" + ChatColor.AQUA + ChatColor.ITALIC + "Upgrade Levels: 15, 25, 50, 75, 100"
        );

        holdChestMessage.addAll(holdChestStartMessage);
        if (ChestInit.getPlugin().getConfig().getBoolean("hchest.keepondeath")) {
            holdChestMessage.addAll(holdChestDeathMessage);
        }
        holdChestMessage.addAll(holdChestEndMessage);
        return holdChestMessage;
    }
}
