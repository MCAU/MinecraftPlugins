package com.ullarah.uchest.data;

import com.ullarah.uchest.ChestFunctions;
import com.ullarah.uchest.ChestInit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ChestBase {
    protected final ConfigurationSection config;

    protected ChestBase() {
        config = ChestInit.getPlugin().getConfig().getConfigurationSection(getType());
    }

    public abstract String getName();
    public abstract char getShortcut();

    protected abstract List<String> getMenuLore();

    public final String getType() {
        return getShortcut() + "chest";
    }

    public final boolean isEnabled() {
        return ChestInit.chestTypeEnabled.get(getType());
    }

    public final ItemStack getMenuItem() {
        ChestFunctions chestFunctions = new ChestFunctions();
        String menuColour = "" + ChatColor.GREEN + ChatColor.BOLD;
        Material icon = isEnabled() ? getMenuIcon() : Material.BARRIER;
        List<String> lore = isEnabled() ? getMenuLore() : disabledLore();

        return chestFunctions.createItemStack(icon, menuColour + getName(), lore);
    }

    private Material getMenuIcon() {
        Material material = Material.getMaterial(config.getString( "icon").toUpperCase());
        if (material == null) {
            return Material.CHEST;
        }
        return material;
    }

    protected final String accessLevelRequirement(String type) {
        int accessLevel = config.getInt("access");
        boolean accessRemoveLevel = config.getBoolean( "removelevel");

        if (accessLevel > 0) {
            String s = accessLevel > 1 ? "s" : "";
            if (accessRemoveLevel) {
                return ChatColor.YELLOW + "[ Will remove " + accessLevel + " level" + s + " ]";
            } else {
                return ChatColor.YELLOW + "[ Minimum of " + accessLevel + " level" + s + " ]";
            }
        } else {
            return ChatColor.YELLOW + "[ No requirements ]";
        }
    }

    private List<String> disabledLore() {
        return Collections.singletonList(ChatColor.RED + "This chest is disabled");
    }
}
