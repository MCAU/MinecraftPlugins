package com.ullarah.urocket.function;

import org.bukkit.Location;

public class AreaCheck {

    /**
     * Cuboid location check
     *
     * @param location the location of the object
     * @param start    the cuboid start location
     * @param end      the cuboid end location
     * @return is the location of the object within the cuboid
     */
    public boolean cuboid(Location location, Location start, Location end) {

        int x1 = Math.min(start.getBlockX(), end.getBlockX());
        int y1 = Math.min(start.getBlockY(), end.getBlockY());
        int z1 = Math.min(start.getBlockZ(), end.getBlockZ());

        int x2 = Math.max(start.getBlockX(), end.getBlockX());
        int y2 = Math.max(start.getBlockY(), end.getBlockY());
        int z2 = Math.max(start.getBlockZ(), end.getBlockZ());

        return location.getBlockX() >= x1 && location.getBlockX() <= x2
                && location.getBlockY() >= y1 && location.getBlockY() <= y2
                && location.getBlockZ() >= z1 && location.getBlockZ() <= z2;

    }

    /**
     * Check if location is above the highest block for the X-Z location
     * @param location Player location
     * @return True if above the highest block
     */
    public boolean aboveHighestBlock(Location location) {
        return location.getWorld().getHighestBlockYAt(location) < location.getBlockY();
    }
}
