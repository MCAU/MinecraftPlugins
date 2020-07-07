package com.ullarah.umagic.event;

import com.ullarah.umagic.InteractMeta;
import com.ullarah.umagic.MagicFunctions;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerInteract extends MagicFunctions implements Listener {

    public PlayerInteract() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        BlockFace face = event.getBlockFace();

        if (!usingMagicHoe(player))
            return;

        if (!checkHoeInteract(event, player, block))
            return;

        event.setCancelled(true);

        InteractMeta meta = new InteractMeta(block, face, player);
        getBlock(block.getType()).process(meta);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerSwapHandItemsEvent event) {

        Player player = event.getPlayer();

        if (usingMagicHoe(player)) {

            getActionMessage().message(player, "" + ChatColor.RED + ChatColor.BOLD
                    + "Cannot be used in off-hand slot!");
            event.setCancelled(true);

        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (!usingMagicHoe(player))
            return;

        /* eh its kinda hacked on and doesn't work with existing checks yet, just check for admin perms for now */
        if (player.hasPermission("umagic.danger")) {

            //if (!checkHoeInteract(event, player, block))
            //    return;

            if (entity instanceof ItemFrame) {
                ItemFrame itemframe = (ItemFrame) entity;
                Block block = itemframe.getLocation().getBlock();

                if (player.isSneaking()) {
                    itemframe.setFixed(!itemframe.isFixed());
                    getActionMessage().message(player, "" + ChatColor.RED + ChatColor.BOLD
                            + "Item Frame Fixed: " + itemframe.isFixed());
                } else {
                    itemframe.setVisible(!itemframe.isVisible());
                    getActionMessage().message(player, "" + ChatColor.RED + ChatColor.BOLD
                            + "Item Frame Visible: " + itemframe.isVisible());
                }

                double bX = block.getX();
                double bY = block.getY();
                double bZ = block.getZ();

                block.getWorld().spawnParticle(Particle.DRAGON_BREATH, bX, bY, bZ, 30);
                block.getWorld().playSound(block.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 0.5f, 0.5f);
                event.setCancelled(true);
            }
        }

    }

}
