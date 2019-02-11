package com.ullarah.urocket.task;

import com.ullarah.urocket.RocketInit;
import com.ullarah.urocket.init.RocketEnhancement;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.UUID;

public class RocketSolarCheck {

    public void task() {

        Plugin plugin = Bukkit.getPluginManager().getPlugin(RocketInit.pluginName);

        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin,
                () -> plugin.getServer().getScheduler().runTask(plugin, () -> {

                    if (!RocketInit.rocketEnhancement.isEmpty())
                        for (Map.Entry<UUID, RocketEnhancement.Enhancement> entry : RocketInit.rocketEnhancement.entrySet()) {

                            Player player = Bukkit.getPlayer(entry.getKey());
                            RocketEnhancement.Enhancement enhancement = entry.getValue();

                            if (enhancement.equals(RocketEnhancement.Enhancement.SOLAR) && player.isFlying()) {

                                boolean showSolarParticles = false;

                                long time = player.getWorld().getTime();
                                int flyBase = RocketInit.rocketPower.get(player.getUniqueId());

                                if (time >= 0 && time <= 12000) {

                                    switch ((int) time) {

                                        case 0:
                                        case 12000:
                                            player.setFlySpeed(flyBase * 0.025f);
                                            break;

                                        case 500:
                                        case 11500:
                                            player.setFlySpeed(flyBase * 0.0275f);
                                            break;

                                        case 1000:
                                        case 11000:
                                            player.setFlySpeed(flyBase * 0.03f);
                                            break;

                                        case 1500:
                                        case 10500:
                                            player.setFlySpeed(flyBase * 0.0325f);
                                            break;

                                        case 2000:
                                        case 10000:
                                            player.setFlySpeed(flyBase * 0.035f);
                                            break;

                                        case 2500:
                                        case 9500:
                                            player.setFlySpeed(flyBase * 0.0375f);
                                            break;

                                        case 3000:
                                        case 9000:
                                            player.setFlySpeed(flyBase * 0.04f);
                                            break;

                                        case 3500:
                                        case 8500:
                                            player.setFlySpeed(flyBase * 0.0425f);
                                            break;

                                        case 4000:
                                        case 8000:
                                            player.setFlySpeed(flyBase * 0.045f);
                                            break;

                                        case 4500:
                                        case 7500:
                                            player.setFlySpeed(flyBase * 0.0475f);
                                            break;

                                        case 5000:
                                        case 7000:
                                            player.setFlySpeed(flyBase * 0.05f);
                                            break;

                                        case 5500:
                                        case 6500:
                                            player.setFlySpeed(flyBase * 0.0525f);
                                            break;

                                        case 6000:
                                            player.setFlySpeed(flyBase * 0.055f);
                                            break;

                                        default:
                                            showSolarParticles = true;
                                            break;

                                    }

                                }

                                if (time >= 12001 && time <= 24000) {
                                    showSolarParticles = false;
                                    player.setFlySpeed(flyBase * 0.001f);
                                }

                                if (showSolarParticles) {

                                    float x = (float) player.getLocation().getX();
                                    float y = (float) (player.getLocation().getY() - 1);
                                    float z = (float) player.getLocation().getZ();

                                    float oX = (float) 0.125;
                                    float oY = (float) -0.25;
                                    float oZ = (float) 0.125;

                                    player.getWorld().spawnParticle(Particle.CLOUD, x, y, z, 10, oX, oY, oZ, 0);

                                }

                            }

                        }

                }), 20, 20);

    }

}