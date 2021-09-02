package com.ullarah.umagic;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ScrollMeta {

    private final Block block;
    private final boolean forward;
    private final Player player;

    public ScrollMeta(Block block, boolean forward, Player player) {
        this.block = block;
        this.forward = forward;
        this.player = player;
    }

    public Block getBlock() {
        return block;
    }

    public boolean isForward() {
        return forward;
    }

    public Player getPlayer() {
        return player;
    }
}
