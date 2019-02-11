package com.ullarah.uchest.function;

import org.bukkit.entity.Player;

public class Experience {

    public double getExperience(Player player) {

        int level = player.getLevel();
        double exp = getTotalExpForLevel(level);

        exp += (int) (player.getExp() * getExpWithinLevel(level));

        return exp;

    }

    public double getExpWithinLevel(double level) {

        return level >= 30 ? 112 + (level - 30) * 9 :
                (level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2);

    }

    public double getTotalExpForLevel(double level) {

        return level >= 30 ? (level * (9 * level - 307) + 4124) / 2 :
                (level >= 15 ? (level * (5 * level - 71) + 644) / 2 : level * (level + 8) + 7);

    }

    public double getExactLevelForExp(double exp) {

        return exp >= 1395 ? (Math.sqrt(23562.25 - 18 * (2062 - exp)) + 153.5) / 9.0 :
                (exp >= 315 ? (Math.sqrt(1260.25 - 10 * (322 - exp)) + 35.5) / 5.0 : (Math.sqrt(64 - 4 * (7 - exp)) - 8) / 2.0);

    }

    public int getLevelForExp(int exp) {

        return roundExp(getExactLevelForExp(exp));

    }

    public void setExperience(Player player, double exp) {

        int level = (int) getExactLevelForExp(exp);
        double remaining = exp - getTotalExpForLevel(level);
        double toNext = getExpWithinLevel(level+1);

        player.setLevel(level);
        player.setExp((float) remaining / (float) toNext);

    }

    public void addExperience(Player player, double exp) {

        int current = roundExp(getExperience(player));
        int currentTotal = player.getTotalExperience();

        setExperience(player, current + exp);
        player.setTotalExperience(currentTotal + roundExp(exp));

        if (currentTotal == 2147483647) player.setTotalExperience(roundExp(exp));
        if (currentTotal < 0) player.setTotalExperience(roundExp(exp));

    }

    public void removeExperience(Player player, double exp) {

        int current = roundExp(getExperience(player));
        int currentTotal = player.getTotalExperience();

        setExperience(player, current - exp);
        player.setTotalExperience(currentTotal - roundExp(exp));

        if (currentTotal == 2147483647) player.setTotalExperience(0);
        if (currentTotal < 0) player.setTotalExperience(0);

    }

    private int roundExp(double exp) {

        Long L = Math.round(exp);
        return L.intValue();

    }

}
