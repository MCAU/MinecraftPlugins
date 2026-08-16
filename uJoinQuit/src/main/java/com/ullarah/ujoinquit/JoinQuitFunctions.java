package com.ullarah.ujoinquit;

import com.ullarah.ujoinquit.function.CommonString;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

public class JoinQuitFunctions {

    public static final int MESSAGE_GUI_SIZE = 54;
    public static final String EXTRA_GUI_TITLE = "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Extra Options";

    private final CommonString commonString = new CommonString();

    public static String messageGuiTitle(Message type) {
        return "" + ChatColor.DARK_AQUA + ChatColor.BOLD + type.getDisplayName() + " Message";
    }

    void listMessages(Player player, Message type) {

        String typeString = type.getDisplayName();
        Inventory chestGUI = JoinQuitInit.getPlugin().getServer().createInventory(
                null, MESSAGE_GUI_SIZE, messageGuiTitle(type));

        int selected = getMessageIndex(player, type);
        int count = Math.min(type.getList().size(), MESSAGE_GUI_SIZE);

        for (int i = 0; i < count; i++) {
            String message = replacePlayerString(player, type.getList().get(i));
            String messageArray = ChatColor.translateAlternateColorCodes('&', ChatColor.WHITE + message);

            ItemStack paperItem = message.length() >= 2 && message.charAt(0) == '&'
                    ? translateChatToPane(ChatColor.getByChar(message.charAt(1)))
                    : new ItemStack(Material.GLASS_PANE, 1);

            // Apply meta
            ItemMeta paperMeta = paperItem.getItemMeta();
            if (paperMeta != null) {
                paperMeta.setDisplayName(messageArray);
                paperMeta.setLore(Collections.singletonList("" + ChatColor.YELLOW + ChatColor.ITALIC
                        + "Click to set " + typeString + " Message"));
                if (i == selected) {
                    paperMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
                    paperMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                paperItem.setItemMeta(paperMeta);
            }

            chestGUI.setItem(i, paperItem);
        }

        player.openInventory(chestGUI);
    }

    public String replacePlayerString(Player player, String message) {
        String playerName = player.getPlayerListName();

        if (JoinQuitInit.lastPlayer.equals(playerName)) JoinQuitInit.lastPlayer = "nobody";

        message = message.replace("{player}", playerName);
        message = message.replace("{l_player}", playerName.toLowerCase());
        message = message.replace("{u_player}", playerName.toUpperCase());
        message = message.replace("{p_player}", JoinQuitInit.lastPlayer);

        if (message.contains("{r_player}")) {
            List<Player> otherPlayers = new ArrayList<>();

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (!onlinePlayer.getUniqueId().equals(player.getUniqueId())) otherPlayers.add(onlinePlayer);
            }

            String randomName = otherPlayers.isEmpty() ? "nobody"
                    : otherPlayers.get(new Random().nextInt(otherPlayers.size())).getPlayerListName();
            message = message.replace("{r_player}", randomName);
        }
        return message;
    }

    void updateMessageHashMap() {
        for (Message type : Message.values()) {
            type.getList().clear();
            type.getList().addAll(JoinQuitInit.getPlugin().getConfig().getStringList(type.getType()));

            if (type.getList().size() > MESSAGE_GUI_SIZE) {
                JoinQuitInit.getPlugin().getLogger().log(Level.WARNING, "Only the first " + MESSAGE_GUI_SIZE
                        + " " + type.getType() + " are selectable in the menu.");
            }
        }

        JoinQuitInit.getPlugin().getLogger().log(Level.INFO,
                "Joins: " + JoinQuitInit.joinMessages.size() + " | " + "Quits: " + JoinQuitInit.quitMessages.size());
    }

    File updatePlayerConfigFile() {

        File file = new File(JoinQuitInit.getPlugin().getDataFolder() + File.separator + "player.yml");

        if (!file.exists()) try {

            if (file.createNewFile()) return file;

        } catch (IOException e) {

            JoinQuitInit.getPlugin().getLogger().log(Level.SEVERE, "Could not create player.yml", e);

        }

        return file;

    }

    void updatePlayerMessageIndex() {

        for (String uuid : JoinQuitInit.getPlayerConfig().getKeys(false)) {

            if (JoinQuitInit.getPlayerConfig().contains(uuid + ".join"))
                JoinQuitInit.playerJoinMessage.put(UUID.fromString(uuid),
                        JoinQuitInit.getPlayerConfig().getInt(uuid + ".join"));

            if (JoinQuitInit.getPlayerConfig().contains(uuid + ".quit"))
                JoinQuitInit.playerQuitMessage.put(UUID.fromString(uuid),
                        JoinQuitInit.getPlayerConfig().getInt(uuid + ".quit"));

            if (JoinQuitInit.getPlayerConfig().contains(uuid + ".location"))
                JoinQuitInit.playerJoinLocation.put(UUID.fromString(uuid),
                        (Location) JoinQuitInit.getPlayerConfig().get(uuid + ".location"));

        }

    }

    private int getMessageIndex(Player player, Message type) {
        String path = player.getUniqueId().toString() + "." + type.getKey();
        return JoinQuitInit.getPlayerConfig().getInt(path, -1);
    }

    public String getMessage(Player player, Message type) {
        int index = getMessageIndex(player, type);
        List<String> messages = type.getList();
        return index >= 0 && index < messages.size() ? messages.get(index) : null;
    }

    public void setMessage(Player player, Message type, int index) {
        try {

            UUID playerUUID = player.getUniqueId();
            YamlConfiguration config = JoinQuitInit.getPlayerConfig();

            config.set(playerUUID.toString() + "." + type.getKey(), index);
            config.save(JoinQuitInit.getPlayerConfigFile());

            switch (type) {

                case JOIN:
                    JoinQuitInit.playerJoinMessage.put(playerUUID, index);
                    break;

                case QUIT:
                    JoinQuitInit.playerQuitMessage.put(playerUUID, index);
                    break;

            }

            commonString.messageSend(JoinQuitInit.getPlugin(), player, true,
                    type.getDisplayName() + " message changed!");

        } catch (IOException e) {

            commonString.messageSend(JoinQuitInit.getPlugin(), player, true, ChatColor.RED + "Error saving changes!");
            JoinQuitInit.getPlugin().getLogger().log(Level.SEVERE, "Could not save player.yml", e);

        }

    }

    public void setLocation(Player player) {

        try {

            UUID playerUUID = player.getUniqueId();
            YamlConfiguration config = JoinQuitInit.getPlayerConfig();

            config.set(playerUUID.toString() + ".location", player.getEyeLocation());
            config.save(JoinQuitInit.getPlayerConfigFile());

            JoinQuitInit.playerJoinLocation.put(playerUUID, player.getEyeLocation());

            commonString.messageSend(JoinQuitInit.getPlugin(), player, true, "Join location changed!");

        } catch (IOException e) {

            commonString.messageSend(JoinQuitInit.getPlugin(), player, true, ChatColor.RED + "Error saving changes!");
            JoinQuitInit.getPlugin().getLogger().log(Level.SEVERE, "Could not save player.yml", e);

        }

    }

    void showExtra(Player player) {
        Inventory options = Bukkit.createInventory(null, InventoryType.HOPPER, EXTRA_GUI_TITLE);

        ItemStack locationItem = new ItemStack(Material.COMPASS, 1);
        ItemMeta locationItemMeta = locationItem.getItemMeta();

        locationItemMeta.setDisplayName(ChatColor.WHITE + "Set Join Location");
        locationItemMeta.setLore(Arrays.asList(
                ChatColor.YELLOW + "Set your current location as",
                ChatColor.YELLOW + "your join location.")
        );

        locationItem.setItemMeta(locationItemMeta);

        options.addItem(locationItem);
        player.openInventory(options);
    }

    void clearMessage(Player player) {

        try {

            UUID playerUUID = player.getUniqueId();
            YamlConfiguration config = JoinQuitInit.getPlayerConfig();

            config.set(playerUUID.toString(), null);
            config.save(JoinQuitInit.getPlayerConfigFile());

            JoinQuitInit.playerJoinMessage.remove(playerUUID);
            JoinQuitInit.playerQuitMessage.remove(playerUUID);
            JoinQuitInit.playerJoinLocation.remove(playerUUID);

            commonString.messageSend(JoinQuitInit.getPlugin(), player, true, "All settings cleared!");

        } catch (IOException e) {

            commonString.messageSend(JoinQuitInit.getPlugin(), player, true,
                    ChatColor.RED + "Error saving changes!");
            JoinQuitInit.getPlugin().getLogger().log(Level.SEVERE, "Could not save player.yml", e);

        }

    }

    void displayHelp(Player player) {
        commonString.messageSend(JoinQuitInit.getPlugin(), player, true, "Custom Join and Quit Messages");

        TextComponent allOptions = new TextComponent(commonString.pluginPrefix(
                JoinQuitInit.getPlugin()) + "Click an option: ");

        TextComponent joinOption = new TextComponent(
                ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "Set Join" + ChatColor.AQUA + "] ");

        TextComponent quitOption = new TextComponent(
                ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "Set Quit" + ChatColor.AQUA + "] ");

        TextComponent extraOption = new TextComponent(
                ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "Extra" + ChatColor.GREEN + "] ");

        TextComponent clearOption = new TextComponent(
                ChatColor.RED + "[" + ChatColor.DARK_RED + "Clear" + ChatColor.RED + "] ");

        joinOption.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jq join"));
        quitOption.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jq quit"));
        extraOption.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jq extra"));
        clearOption.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jq clear"));

        joinOption.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(ChatColor.YELLOW + "Click to view and set a Join Message\n"
                        + ChatColor.GOLD + "/jq join").create()));

        quitOption.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(ChatColor.YELLOW + "Click to view and set a Quit Message\n"
                        + ChatColor.GOLD + "/jq quit").create()));

        extraOption.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(ChatColor.YELLOW + "Click to view Extra Options\n"
                        + ChatColor.GOLD + "/jq extra").create()));

        clearOption.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(ChatColor.YELLOW + "Click to clear everything\n"
                        + ChatColor.GOLD + "/jq clear").create()));

        allOptions.addExtra(joinOption);
        allOptions.addExtra(quitOption);
        allOptions.addExtra(extraOption);
        allOptions.addExtra(clearOption);

        player.spigot().sendMessage(allOptions);

    }

    private ItemStack translateChatToPane(ChatColor chatColor) {
        Material pane;

        if (chatColor == null) return new ItemStack(Material.GLASS_PANE, 1);

        switch (chatColor) {

            case BLACK:
                pane = Material.BLACK_STAINED_GLASS_PANE;
                break;

            case DARK_BLUE:
                pane = Material.BLUE_STAINED_GLASS_PANE;
                break;

            case DARK_GREEN:
                pane = Material.GREEN_STAINED_GLASS_PANE;
                break;

            case DARK_AQUA:
                pane = Material.CYAN_STAINED_GLASS_PANE;
                break;

            case DARK_RED:
                pane = Material.RED_STAINED_GLASS_PANE;
                break;

            case DARK_PURPLE:
                pane = Material.PURPLE_STAINED_GLASS_PANE;
                break;

            case GOLD:
                pane = Material.ORANGE_STAINED_GLASS_PANE;
                break;

            case GRAY:
                //pane = Material.LIGHT_GRAY_STAINED_GLASS_PANE;
                pane = Material.WHITE_STAINED_GLASS_PANE;
                break;

            case DARK_GRAY:
                pane = Material.GRAY_STAINED_GLASS_PANE;
                break;

            case BLUE:
                pane = Material.LIGHT_BLUE_STAINED_GLASS_PANE;
                break;

            case GREEN:
                pane = Material.LIME_STAINED_GLASS_PANE;
                break;

            case AQUA:
                pane = Material.LIGHT_BLUE_STAINED_GLASS_PANE;
                break;

            case RED:
                pane = Material.RED_STAINED_GLASS_PANE;
                break;

            case LIGHT_PURPLE:
                pane = Material.PINK_STAINED_GLASS_PANE;
                break;

            case YELLOW:
                pane = Material.YELLOW_STAINED_GLASS_PANE;
                break;

            case WHITE:
            default:
                pane = Material.WHITE_STAINED_GLASS_PANE;
                break;
        }

        return new ItemStack(pane, 1);
    }

    public enum Message {
        JOIN(JoinQuitInit.joinMessages, "joinMessages", "join", "Join"),
        QUIT(JoinQuitInit.quitMessages, "quitMessages", "quit", "Quit");

        private final List<String> list;
        private final String type;
        private final String key;
        private final String displayName;

        Message(List<String> getList, String getType, String getKey, String getDisplayName) {
            this.list = getList;
            this.type = getType;
            this.key = getKey;
            this.displayName = getDisplayName;
        }

        public List<String> getList() {
            return list;
        }

        public String getType() {
            return type;
        }

        public String getKey() {
            return key;
        }

        public String getDisplayName() {
            return displayName;
        }

    }

}
