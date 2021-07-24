package com.ullarah.urocket.event;

import com.ullarah.urocket.RocketFunctions;
import com.ullarah.urocket.function.FuelJacket;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitJoinDeath implements Listener {

    private final RocketFunctions rocketFunctions = new RocketFunctions();

    @EventHandler
    public void rocketRemoveOnQuit(PlayerQuitEvent event) {
        rocketFunctions.disableRocketBoots(event.getPlayer(), false);
    }

    @EventHandler
    public void rocketRemoveOnJoin(PlayerJoinEvent event) {
        FuelJacket.create(event.getPlayer());
        rocketFunctions.disableRocketBoots(event.getPlayer(), false);
    }

    @EventHandler
    public void rocketRemoveOnDeath(PlayerDeathEvent event) {
        rocketFunctions.disableRocketBoots(event.getEntity(), false);
    }

}
