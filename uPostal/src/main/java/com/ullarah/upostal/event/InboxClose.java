package com.ullarah.upostal.event;

import com.ullarah.upostal.PostalInit;
import com.ullarah.upostal.command.inbox.Update;
import com.ullarah.upostal.function.PlayerProfile;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.File;
import java.util.UUID;

public class InboxClose implements Listener {

    @EventHandler
    public void event(final InventoryCloseEvent event) {

        if (event.getInventory().getName().matches(ChatColor.DARK_RED + "Inbox: " + ChatColor.DARK_AQUA + "(.*)")) {

            String inboxOwner = ChatColor.stripColor(event.getInventory().getTitle().replace("Inbox: ", ""));
            UUID inboxUUID = new PlayerProfile().lookup(inboxOwner).getId();

            File inboxConfigFile = new File(PostalInit.getInboxDataPath(), inboxUUID.toString() + ".yml");
            FileConfiguration inboxConfig = YamlConfiguration.loadConfiguration(inboxConfigFile);

            UUID inboxViewerUUID = event.getInventory().getViewers().get(0).getUniqueId();
            UUID inboxOwnerUUID = UUID.fromString(inboxConfig.getString("uuid"));

            new Update().run(inboxViewerUUID, inboxOwnerUUID, event.getInventory());

            PostalInit.inboxViewerBusy.remove(inboxViewerUUID);
            PostalInit.inboxViewerBusy.remove(inboxOwnerUUID);
            PostalInit.inboxOwnerBusy.remove(inboxViewerUUID);
            PostalInit.inboxOwnerBusy.remove(inboxOwnerUUID);
            PostalInit.inboxModification.remove(inboxViewerUUID);
            PostalInit.inboxModification.remove(inboxOwnerUUID);

            if (PostalInit.inboxChanged.containsKey(inboxUUID)) {
                PostalInit.inboxChanged.get(inboxUUID).cancel();
                PostalInit.inboxChanged.remove(inboxUUID);
            }

        }

    }

}
