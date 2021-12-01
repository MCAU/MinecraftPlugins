package com.ullarah.uchest.command;

import com.ullarah.uchest.ChestFunctions;
import com.ullarah.uchest.ChestInit;
import com.ullarah.uchest.data.ChestType;
import com.ullarah.uchest.function.CommonString;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChestMenu {

    private void showChestMenu(CommandSender sender) {
        Player player = (Player) sender;
        Inventory chestGUI = ChestInit.getPlugin().getServer().createInventory(null, 9, "" + ChatColor.GOLD + ChatColor.BOLD + "Mixed Chests");

        chestGUI.setItem(0, ChestType.DONATION.instance.getMenuItem());
        chestGUI.setItem(1, ChestType.ENCHANTMENT.instance.getMenuItem());
        chestGUI.setItem(2, ChestType.HOLD.instance.getMenuItem());
        chestGUI.setItem(3, ChestType.MONEY.instance.getMenuItem());
        chestGUI.setItem(4, ChestType.RANDOM.instance.getMenuItem());
        chestGUI.setItem(5, ChestType.SHUFFLE.instance.getMenuItem());
        chestGUI.setItem(6, ChestType.VAULT.instance.getMenuItem());
        chestGUI.setItem(7, ChestType.SWAP.instance.getMenuItem());
        chestGUI.setItem(8, ChestType.EXPERIENCE.instance.getMenuItem());

        player.openInventory(chestGUI);
    }

    public void runCommand(CommandSender sender, String[] args) {
        CommonString commonString = new CommonString();

        String consoleTools = commonString.pluginPrefix(ChestInit.getPlugin()) + ChatColor.WHITE + "toggle | validate";

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(consoleTools);
                return;
            }

            showChestMenu(sender);
        } else try {

            switch (ChestFunctions.validCommands.valueOf(args[0].toUpperCase())) {
                case HELP:
                    if (!(sender instanceof Player)) commonString.messageNoConsole(ChestInit.getPlugin(), sender);
                    else new DisplayHelp().runHelp(sender);
                    break;

                case TOGGLE:
                    new ToggleAccess().toggleChestAccess(sender, args);
                    break;

                case VALIDATE:
                    new ValidateConfig().validateMaterialConfig(sender, args);
                    break;

                default:
                    if (!(sender instanceof Player)) sender.sendMessage(consoleTools);
                    else showChestMenu(sender);
            }

        } catch (IllegalArgumentException e) {
            if (!(sender instanceof Player))
                sender.sendMessage(consoleTools);
            else
                new DisplayHelp().runHelp(sender);
        }
    }
}
