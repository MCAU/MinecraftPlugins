package com.ullarah.umagic.function;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionMessage {

    /**
     * Displays a message on the clients action bar
     *
     * @param player  the player object
     * @param message the text of the message
     */
    public void message(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    /**
     * Displays a single-color bolded message
     */
    public void message(Player player, ChatColor color, String message) {
        message(player, "" + color + ChatColor.BOLD + message);
    }
}
