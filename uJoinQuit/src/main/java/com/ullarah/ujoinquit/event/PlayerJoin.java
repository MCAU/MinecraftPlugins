package com.ullarah.ujoinquit.event;

import com.ullarah.ujoinquit.JoinQuitFunctions;
import com.ullarah.ujoinquit.JoinQuitInit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void event(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        String joinMessage = event.getJoinMessage();

        if (player.hasPermission("jq.silentjoin")) {
            event.setJoinMessage("");
        } else if (JoinQuitInit.playerJoinMessage.containsKey(playerUUID)
                && joinMessage != null && !joinMessage.isEmpty()) {

            JoinQuitFunctions joinQuitFunctions = new JoinQuitFunctions();
            String selected = joinQuitFunctions.getMessage(player, JoinQuitFunctions.Message.JOIN);

            if (selected != null) {
                String message = joinQuitFunctions.replacePlayerString(player, selected);
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', JoinQuitInit.joinChar + message));
                JoinQuitInit.lastPlayer = player.getPlayerListName();
            }
        }

        Location joinLocation = JoinQuitInit.playerJoinLocation.get(playerUUID);
        if (joinLocation == null) return;

        player.teleport(joinLocation);
    }
}
