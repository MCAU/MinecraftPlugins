package com.ullarah.umagic.block;

import com.ullarah.umagic.InteractMeta;
import com.ullarah.umagic.ScrollMeta;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class ScrollBlock extends BaseBlock {

    public abstract void process(ScrollMeta meta);

    protected void enableScroll(InteractMeta meta) {
        Player player = meta.getPlayer();
        scrollMap.put(player, meta.getBlock());
        getActionMessage().message(player, ChatColor.GREEN, "Scroll forwards or backwards to change state. Use the hoe again to disable");
    }
}
