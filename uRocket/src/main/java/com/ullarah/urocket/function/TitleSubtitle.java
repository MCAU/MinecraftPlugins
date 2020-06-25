package com.ullarah.urocket.function;

import org.bukkit.entity.Player;

public class TitleSubtitle {

    /**
     * Displays a title in front of players client
     *
     * @param player  the player object
     * @param seconds the amount of seconds to display
     * @param title   the text of the title
     */
    public void title(Player player, int seconds, String title) {
        both(player, seconds, title, "");
    }

    /**
     * Displays a title in front of players client
     *
     * @param player   the player object
     * @param seconds  the amount of seconds to display
     * @param subtitle the text of the subtitle
     */
    public void subtitle(Player player, int seconds, String subtitle) {
        both(player, seconds, "", subtitle);
    }

    /**
     * Displays a title in front of players client
     *
     * @param player   the player object
     * @param seconds  the amount of seconds to display
     * @param title    the text of the title
     * @param subtitle the text of the subtitle
     */
    public void both(Player player, int seconds, String title, String subtitle) {
        player.sendTitle(title, subtitle, 20, 20 * seconds, 20);
    }
}
