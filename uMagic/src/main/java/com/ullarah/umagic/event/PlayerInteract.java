package com.ullarah.umagic.event;

import com.ullarah.umagic.InteractMeta;
import com.ullarah.umagic.MagicFunctions;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteract extends MagicFunctions implements Listener {

    public PlayerInteract() {
        super(false);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            return; // Don't trigger any events for off-hand, too much trouble.
        }

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        // Have to be able to lock state even if not using a magic hoe, as it can break or be dropped
        if (tryLockState(event, player)) { return; }

        if (!usingMagicHoe(player))
            return;

        if (!checkHoeInteract(event, player, block))
            return;

        event.setCancelled(true);

        BlockFace face = event.getBlockFace();
        InteractMeta meta = new InteractMeta(block, face, player);
        getBlock(block.getType()).process(meta);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();

        if (usingMagicHoe(player)) {
            getActionMessage().message(player, ChatColor.RED, "Cannot be used in off-hand slot!");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (!usingMagicHoe(player))
            return;

        // This needs further refactoring to work correctly.
        //if (!checkHoeInteract(event, player, block))
        //    return;

        if (entity instanceof ItemFrame) {
            ItemFrame itemframe = (ItemFrame) entity;
            Block block = itemframe.getLocation().getBlock();

            // so we'll manually check they have build permission, build access and are in survival mode
            if (player.hasPermission("umagic.usage") && checkBlock(player, block) && player.getGameMode().equals(GameMode.SURVIVAL)) {
                if (player.isSneaking()) {
                    itemframe.setFixed(!itemframe.isFixed());
                    getActionMessage().message(player, ChatColor.RED, "Item Frame Fixed: " + itemframe.isFixed());
                } else {
                    itemframe.setVisible(!itemframe.isVisible());
                    getActionMessage().message(player, ChatColor.RED, "Item Frame Visible: " + itemframe.isVisible());
                }

                // then just make our own particles
                double bX = block.getX();
                double bY = block.getY();
                double bZ = block.getZ();

                block.getWorld().spawnParticle(Particle.DRAGON_BREATH, bX, bY, bZ, 30);
                block.getWorld().playSound(block.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 0.5f, 0.5f);
            }
            event.setCancelled(true);
        }
    }

}
