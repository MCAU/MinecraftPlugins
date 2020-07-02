package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BlockDamage extends MagicFunctions implements Listener {
    private final Set<String> metas = new HashSet<>(Arrays.asList(metaWool, metaLadd, metaFurn, metaVoid, metaCact));
    public BlockDamage() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(BlockDamageEvent event) {

        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        if (magicLocations.containsKey(location)
                && !usingMagicHoe(player)
                && metas.contains(magicLocations.get(location))) {
            getActionMessage().message(player, "" + ChatColor.AQUA + ChatColor.BOLD
                    + "Magical Block Detected!"
                    + ChatColor.RED + ChatColor.BOLD + " Convert back using Magical Hoe!");
            event.setCancelled(true);
        }
    }

}
