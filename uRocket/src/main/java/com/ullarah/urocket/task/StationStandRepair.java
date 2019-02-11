package com.ullarah.urocket.task;

import com.ullarah.urocket.RocketFunctions;
import com.ullarah.urocket.RocketInit;
import com.ullarah.urocket.function.SignText;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class StationStandRepair {

    public void task() {

        Plugin plugin = Bukkit.getPluginManager().getPlugin(RocketInit.pluginName);
        RocketFunctions rocketFunctions = new RocketFunctions();
        SignText signText = new SignText();

        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin,
                () -> plugin.getServer().getScheduler().runTask(plugin, () -> {

                    if (!RocketInit.rocketRepairStand.isEmpty()) {

                        for (Map.Entry<UUID, Location> repairStand : RocketInit.rocketRepairStand.entrySet()) {

                            Chunk chunk = repairStand.getValue().getWorld().getChunkAt(repairStand.getValue());

                            if (chunk.isLoaded()) {

                                Set<LivingEntity> repairStands = new HashSet<>();

                                for (Entity entity : chunk.getEntities())
                                    if (entity instanceof ArmorStand) repairStands.add((LivingEntity) entity);

                                for (LivingEntity stand : repairStands) {

                                    Location beaconSign = new Location(
                                            stand.getLocation().getWorld(),
                                            stand.getLocation().getX(),
                                            (stand.getLocation().getY() - 1),
                                            stand.getLocation().getZ());

                                    BlockState block = stand.getWorld().getBlockAt(
                                            stand.getLocation().getBlockX(),
                                            stand.getLocation().getBlockY() - 2,
                                            stand.getLocation().getBlockZ()).getState();

                                    if (block instanceof Furnace && ((Furnace) block).getBurnTime() > 0) {

                                        ItemStack standBoots = stand.getEquipment().getBoots();

                                        if (standBoots.hasItemMeta()) {

                                            Integer bootRepair = rocketFunctions.getBootRepairRate(standBoots);

                                            short bootDurability = standBoots.getDurability();
                                            int bootMaterialDurability = rocketFunctions.getBootDurability(standBoots);

                                            int bootHealthOriginal = (bootMaterialDurability - bootDurability);
                                            int bootHealthNew = ((bootMaterialDurability - bootDurability) + bootRepair);

                                            String bootType = ChatColor.stripColor(standBoots.getItemMeta().getLore().get(0));

                                            signText.changeAllCheck(beaconSign, 0, "[Repair Status]", false,
                                                    new String[]{
                                                            "[Repair Status]",
                                                            ChatColor.STRIKETHROUGH + "--------------",
                                                            ChatColor.RED + bootType,
                                                            String.valueOf(bootHealthNew) + " / " + bootMaterialDurability});

                                            if (bootHealthOriginal <= (bootMaterialDurability - 1)) {

                                                standBoots.setDurability((short) (bootDurability - bootRepair));
                                                stand.getEquipment().setBoots(standBoots);

                                                if (bootHealthNew > bootMaterialDurability) {

                                                    signText.changeAllCheck(beaconSign, 0, "[Repair Status]", false,
                                                            new String[]{
                                                                    "[Repair Status]",
                                                                    ChatColor.STRIKETHROUGH + "--------------",
                                                                    ChatColor.RED + bootType,
                                                                    "Fully Restored"});

                                                    RocketInit.rocketRepairStand.remove(stand.getUniqueId());

                                                } else {

                                                    float x = (float) stand.getLocation().getX();
                                                    float y = (float) stand.getLocation().getY();
                                                    float z = (float) stand.getLocation().getZ();

                                                    stand.getWorld().spawnParticle(Particle.PORTAL, x, y, z, 500, 0, 0, 0, 1);

                                                }

                                            } else {
                                                signText.clearLine(beaconSign, new Integer[]{2, 3});
                                                RocketInit.rocketRepairStand.remove(stand.getUniqueId());
                                            }

                                        }

                                    } else {
                                        signText.changeLine(beaconSign,
                                                new HashMap<Integer, String>() {{
                                                    put(2, "Repair Tank");
                                                    put(3, "Empty");
                                                }});
                                        RocketInit.rocketRepairStand.remove(stand.getUniqueId());
                                    }

                                }

                            }

                        }
                    }

                }), 0, 600);

    }

}
