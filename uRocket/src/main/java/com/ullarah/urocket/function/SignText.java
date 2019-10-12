package com.ullarah.urocket.function;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

import java.util.HashMap;
import java.util.Map;

public class SignText {

    /**
     * Checks for signs around a single location
     * @param location the location surrounded by signs
     * @return a HashMap containing valid sign faces
     */
    private HashMap<String, Boolean> signFaceMap(final Location location) {
        HashMap<String, Boolean> signMap = new HashMap<>();
        for (String face : new String[]{"NORTH", "EAST", "SOUTH", "WEST"}) {

            switch (location.getBlock().getRelative(BlockFace.valueOf(face)).getType()) {
                case ACACIA_SIGN:
                case ACACIA_WALL_SIGN:
                case BIRCH_SIGN:
                case BIRCH_WALL_SIGN:
                case DARK_OAK_SIGN:
                case DARK_OAK_WALL_SIGN:
                case JUNGLE_SIGN:
                case JUNGLE_WALL_SIGN:
                case OAK_SIGN:
                case OAK_WALL_SIGN:
                case SPRUCE_SIGN:
                case SPRUCE_WALL_SIGN:
                    signMap.put(face, true);
                    break;
            }
        }

        return signMap;
    }

    /**
     * Change all sign text to given text array
     * @param location the location surrounded by signs
     * @param text     the text array, max 4 entries
     */
    public void changeAll(final Location location, String[] text) {
        HashMap<String, Boolean> signMap = signFaceMap(location);
        for (Map.Entry<String, Boolean> sign : signMap.entrySet()) {

            if (sign.getValue()) {
                Sign signText = (Sign) location.getBlock().getRelative(BlockFace.valueOf(sign.getKey())).getState();

                for (int i = 0; i < text.length; ++i) {
                    signText.setLine(i, text[i]);
                }
                signText.update();
            }
        }
    }

    /**
     * Change all sign(s) text to given text array checking a single line
     * @param location the location surrounded by signs
     * @param line     the specific line to check
     * @param extract  the extract of text to check
     * @param cs       to check the case sensitivity
     * @param text     a string array of sign line text to change
     */
    public void changeAllCheck(final Location location, int line, String extract, boolean cs, String[] text) {
        HashMap<String, Boolean> signMap = signFaceMap(location);
        for (Map.Entry<String, Boolean> sign : signMap.entrySet()) {

            if (sign.getValue()) {

                Sign signText = (Sign) location.getBlock().getRelative(BlockFace.valueOf(sign.getKey())).getState();
                String signLine = signText.getLine(line);

                if (!cs) {
                    signLine = signLine.toLowerCase();
                    extract = extract.toLowerCase();
                }

                if (signLine.equals(extract)) {
                    for (int i = 0; i < text.length; ++i) {
                        signText.setLine(i, text[i]);
                    }
                    signText.update();
                }
            }
        }
    }

    /**
     * Change specific sign text to given text array
     * @param location the location surrounded by signs
     * @param lines    a map of the sign lines to change
     */
    public void changeLine(final Location location, Map<Integer, String> lines) {

        HashMap<String, Boolean> signMap = signFaceMap(location);

        signMap.entrySet().stream().filter(Map.Entry::getValue).forEach(sign -> {

            Sign signText = (Sign) location.getBlock().getRelative(BlockFace.valueOf(sign.getKey())).getState();

            for (Map.Entry entry : lines.entrySet()) signText.setLine((int) entry.getKey(), (String) entry.getValue());

            signText.update();

        });

    }

    /**
     * Clear a specific line from a sign(s)
     *
     * @param location the location surrounded by signs
     * @param line     an array of the sign lines to clear
     */
    public void clearLine(final Location location, Integer[] line) {

        HashMap<String, Boolean> signMap = signFaceMap(location);

        signMap.entrySet().stream().filter(Map.Entry::getValue).forEach(sign -> {

            Sign signText = (Sign) location.getBlock().getRelative(BlockFace.valueOf(sign.getKey())).getState();

            for (int l : line) signText.setLine(l, "");

            signText.update();

        });

    }

    /**
     * Clears all lines of the given sign(s)
     *
     * @param location the location surrounded by signs
     */
    public void clearAll(final Location location) {

        HashMap<String, Boolean> signMap = signFaceMap(location);

        signMap.entrySet().stream().filter(Map.Entry::getValue).forEach(sign -> {

            Sign signText = (Sign) location.getBlock().getRelative(BlockFace.valueOf(sign.getKey())).getState();

            signText.update();

            for (int l : new Integer[]{0, 1, 2, 3}) signText.setLine(l, "");

        });

    }

}
