package com.ullarah.urocket.event;

import com.ullarah.urocket.RocketInit;
import com.ullarah.urocket.recipe.RepairStand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class StandBreak implements Listener {

    @EventHandler
    public void RocketArmourStandBreak(EntityDamageByEntityEvent event) {

        if (event.getEntityType().equals(EntityType.ARMOR_STAND)) {

            final LivingEntity standEntity = (LivingEntity) event.getEntity();

            Location entityLocation = standEntity.getLocation();

            int eX = entityLocation.getBlockX();
            int eY = entityLocation.getBlockY();
            int eZ = entityLocation.getBlockZ();

            List<String> standList = RocketInit.getPlugin().getConfig().getStringList("stands");
            List<String> newStandList = standList.stream().map(stand -> stand.replaceFirst(".{37}", "")).collect(Collectors.toList());

            String standOriginal = event.getDamager().getUniqueId().toString() + "|" + standEntity.getWorld().getName() + "|" + eX + "|" + eY + "|" + eZ;
            String standNew = standEntity.getWorld().getName() + "|" + eX + "|" + eY + "|" + eZ;

            if (standList.contains(standOriginal) || (event.getDamager().hasPermission("rocket.remove") && newStandList.contains(standNew))) {

                if (standEntity.getEquipment().getBoots().getAmount() == 1) {
                    ItemStack standBoots = standEntity.getEquipment().getBoots();
                    if (standBoots.getType() != Material.AIR)
                        standEntity.getWorld().dropItemNaturally(entityLocation, standBoots);
                }

                standEntity.getWorld().dropItemNaturally(entityLocation, new RepairStand().stand());

                standList.remove(newStandList.indexOf(standNew));
                standEntity.setHealth(0.0);
                event.setCancelled(true);

                RocketInit.getPlugin().getConfig().set("stands", standList);
                RocketInit.getPlugin().saveConfig();

            }

        }

    }

}
