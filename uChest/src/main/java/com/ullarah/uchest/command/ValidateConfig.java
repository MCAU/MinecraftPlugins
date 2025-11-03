package com.ullarah.uchest.command;

import com.ullarah.uchest.ChestInit;
import com.ullarah.uchest.function.CommonString;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class ValidateConfig {

    public void validateMaterialConfig(CommandSender sender, String[] args) {

        CommonString commonString = new CommonString();

        // Console only command, given its verbosity
        if (sender instanceof Player) {
            commonString.messagePermDeny(ChestInit.getPlugin(), sender);
            return;
        }

        // Add every material in the game
        Set<Material> types = new HashSet<>();
        for (Material material : Material.values()) {
            if (material.isItem() && material != Material.AIR)
                types.add(material);
        }

        // Validate the set against every material in the game
        for (ItemStack stack : ChestInit.materialMap.keySet()) {
            types.remove(stack.getType());
        }

        if (types.isEmpty()) {
            commonString.messageSend(ChestInit.getPlugin(), sender,
                    ChatColor.GREEN + "You have all possible materials in your data files!");
        } else {
            // make a new list out of the hash list that we can sort
            List<String> list = new ArrayList<>();
            for (Material type : types) {
                list.add(type.name());
            }
            Collections.sort(list);

            // pad things out to make configs somewhat easier to read (note: names are getting really long now days)
            // WAXED_WEATHERED_CUT_COPPER_STAIRS:      { m: WAXED_WEATHERED_CUT_COPPER_STAIRS,      s: true, d: true, r: true, e: 0.0, x: 0.0 }
            StringBuilder message = new StringBuilder();
            for (String item : list) {
                String rightPad = StringUtils.rightPad(item +":", 40, " ");
                String leftPad = StringUtils.rightPad(item + ",", 40, " ");
                message.append(rightPad + "{ m: " + leftPad +"s: true, d: true, r: true, e: 0.0, x: 0.0 }\n");
            }

            commonString.messageSend(ChestInit.getPlugin(), sender,
                ChatColor.WHITE + "You are missing the following materials from your data files:\n" + message);
        }

    }

}
