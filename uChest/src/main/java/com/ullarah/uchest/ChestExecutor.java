package com.ullarah.uchest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class ChestExecutor implements CommandExecutor {

    private static final Map<String, String> CHEST_COMMANDS = new HashMap<String, String>() {{
        put("Menu", "");
        put("Donation", "d");
        put("Enchantment", "e");
        put("Hold", "h");
        put("Money", "m");
        put("Random", "r");
        put("Shuffle", "s");
        put("Vault", "v");
        put("Swap", "w");
        put("Experience", "x");
    }};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        CHEST_COMMANDS.entrySet().stream().filter(
                newCommand -> command.getName().equalsIgnoreCase(
                        newCommand.getValue() + "chest")).forEach(newCommand -> {

            try {

                Class<?> commandClass = Class.forName("com.ullarah.uchest.command.Chest" + newCommand.getKey());
                Method commandMethod = commandClass.getMethod("runCommand", CommandSender.class, String[].class);
                commandMethod.setAccessible(true);
                commandMethod.invoke(commandClass.newInstance(), sender, args);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        return true;

    }
}
