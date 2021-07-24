package com.ullarah.urocket.function;

import com.ullarah.urocket.RocketFunctions;
import com.ullarah.urocket.RocketInit;
import com.ullarah.urocket.init.RocketLanguage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FuelJacket {
    private FuelJacket() {}

    /**
     * Create fuel config file if missing
     * @param player
     */
    public static void create(Player player) {
        RocketFunctions rocketFunctions = new RocketFunctions();
        CommonString commonString = new CommonString();

        File fuelFile = rocketFunctions.getFuelFile(player);
        if (fuelFile.exists()) {
            return;
        }

        fuelFile.getParentFile().mkdirs();
        try {
            fuelFile.createNewFile();
        } catch (IOException e) {
            commonString.messageSend(RocketInit.getPlugin(), player, true, RocketLanguage.RB_JACKET_CREATE_ERROR);
            e.printStackTrace();
            return;
        }

        FileConfiguration fuelConfig = YamlConfiguration.loadConfiguration(fuelFile);
        fuelConfig.set("leather", new ArrayList<>());
        fuelConfig.set("iron", new ArrayList<>());
        fuelConfig.set("gold", new ArrayList<>());
        fuelConfig.set("diamond", new ArrayList<>());
        fuelConfig.set("netherite", new ArrayList<>());

        try {
            fuelConfig.save(fuelFile);
        } catch (IOException e) {
            commonString.messageSend(RocketInit.getPlugin(), player, true, RocketLanguage.RB_JACKET_SAVE_ERROR);
            e.printStackTrace();
        }
    }
}
