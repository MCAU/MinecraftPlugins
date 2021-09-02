package com.ullarah.umagic.event;

import com.ullarah.umagic.MagicFunctions;
import com.ullarah.umagic.ScrollMeta;
import com.ullarah.umagic.block.ScrollBlock;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeld extends MagicFunctions implements Listener {

    public PlayerItemHeld() {
        super(false);
    }

    private static final int SIZE = 9;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void event(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Block block = scrollMap.get(player);

        if (block == null) return;

        event.setCancelled(true);

        boolean forward;
        int prev = event.getPreviousSlot();
        int next = event.getNewSlot();

        if ((next - prev + SIZE) % SIZE < 3) {
            forward = false;
        } else if ((prev - next + SIZE) % SIZE < 3) {
            forward = true; // forward-scroll goes left on the hotbar (towards zero)
        } else {
            getActionMessage().message(player, ChatColor.RED, "You're scrolling too fast");
            return;
        }

        ScrollBlock scrollBlock = (ScrollBlock) getBlock(block.getType());
        if (scrollBlock == null) {
            scrollMap.remove(player);
            getActionMessage().message(player, ChatColor.RED, "Block missing. Re-enabling scrolling");
            return;
        }

        ScrollMeta meta = new ScrollMeta(block, forward, player);
        scrollBlock.process(meta);
    }
}
