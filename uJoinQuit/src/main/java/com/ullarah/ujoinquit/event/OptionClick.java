package com.ullarah.ujoinquit.event;

import com.ullarah.ujoinquit.JoinQuitFunctions;
import com.ullarah.ujoinquit.function.PermissionCheck;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OptionClick implements Listener {

    @EventHandler
    public void playerClickOption(InventoryClickEvent event) {

        if (event.getClickedInventory() == null) return;

        Player player = (Player) event.getWhoClicked();
        String inventoryTitle = event.getView().getTitle();

        if (inventoryTitle.matches(".*Extra Options")
                && new PermissionCheck().check(player, "jq.access", "jq.extra")) {

            if (event.getClickedInventory() == null) return;

            if (event.getRawSlot() >= 0 && event.getRawSlot() < 5) {

                if (event.getCurrentItem().getType() != Material.AIR) {

                    JoinQuitFunctions joinQuitFunctions = new JoinQuitFunctions();

                    event.getCursor().setType(Material.AIR);

                    switch (event.getCurrentItem().getType()) {

                        case COMPASS:
                            joinQuitFunctions.setLocation(player);
                            break;

                    }

                    player.closeInventory();

                }

            }

            event.setCancelled(true);

        }

    }

}
