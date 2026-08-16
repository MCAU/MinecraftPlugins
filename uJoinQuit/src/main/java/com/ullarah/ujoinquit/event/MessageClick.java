package com.ullarah.ujoinquit.event;

import com.ullarah.ujoinquit.JoinQuitFunctions;
import com.ullarah.ujoinquit.function.PermissionCheck;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MessageClick implements Listener {
    @EventHandler
    public void playerClickMessage(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        Player player = (Player) event.getWhoClicked();
        String inventoryTitle = event.getView().getTitle();

        if (inventoryTitle.matches(".*(Join|Quit) Message")) {
            JoinQuitFunctions.Message type = inventoryTitle.matches(".*Join Message")
                    ? JoinQuitFunctions.Message.JOIN
                    : JoinQuitFunctions.Message.QUIT;

            event.setCancelled(true);

            if (!new PermissionCheck().check(player, "jq.access", "jq." + type.getKey())) return;

            if (event.getRawSlot() >= 0 && event.getRawSlot() < JoinQuitFunctions.MESSAGE_GUI_SIZE) {
                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                    new JoinQuitFunctions().setMessage(player, type, event.getRawSlot());
                    event.getCursor().setType(Material.AIR);
                    player.closeInventory();
                }
            }
        }
    }
}
