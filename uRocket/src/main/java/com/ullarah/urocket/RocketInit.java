package com.ullarah.urocket;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.ullarah.urocket.function.PluginRegisters;
import com.ullarah.urocket.init.RocketEnhancement;
import com.ullarah.urocket.init.RocketVariant;
import com.ullarah.urocket.recipe.*;
import net.milkbowl.vault.economy.Economy;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public class RocketInit extends JavaPlugin {

    public static final String pluginName = "uRocket";

    public static final HashSet<UUID> rocketUsage = new HashSet<>();
    public static final HashSet<UUID> rocketWater = new HashSet<>();
    public static final HashSet<UUID> rocketZones = new HashSet<>();
    public static final HashSet<UUID> rocketJacket = new HashSet<>();
    public static final HashSet<UUID> rocketEffects = new HashSet<>();
    public static final HashSet<UUID> rocketTimeout = new HashSet<>();
    public static final HashSet<HashSet<Location>> rocketFire = new HashSet<>();
    public static final ConcurrentHashMap<Location, Material> rocketGlow = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, EntityType> rocketEntity = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, String> rocketSprint = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, Integer> rocketPower = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, RocketVariant.Variant> rocketVariant = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, RocketEnhancement.Enhancement> rocketEnhancement = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, Location> rocketRepair = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, Location> rocketRepairStand = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<UUID, ConcurrentHashMap<Location, Location>> rocketZoneLocations = new ConcurrentHashMap<>();

    public static final HashMap<String, Integer> registerMap = new HashMap<>();

    private static Plugin plugin;
    private static PluginManager pluginManager;
    private static WorldGuardPlugin worldGuard;
    private static Economy vaultEconomy;

    public static Plugin getPlugin() {
        return plugin;
    }

    private void setPlugin(Plugin plugin) {
        RocketInit.plugin = plugin;
    }

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    private void setPluginManager(PluginManager pluginManager) {
        RocketInit.pluginManager = pluginManager;
    }

    public static WorldGuardPlugin getWorldGuard() {
        return worldGuard;
    }

    private void setWorldGuard(WorldGuardPlugin worldGuard) {
        RocketInit.worldGuard = worldGuard;
    }

    public static Economy getVaultEconomy() {
        return vaultEconomy;
    }

    private void setVaultEconomy(Economy vaultEconomy) {
        RocketInit.vaultEconomy = vaultEconomy;
    }

    public void onEnable() {

        setPluginManager(Bukkit.getPluginManager());
        setPlugin(getPluginManager().getPlugin(pluginName));

        Plugin pluginWorldGuard = getPluginManager().getPlugin("WorldGuard");
        Plugin pluginVault = getPluginManager().getPlugin("Vault");

        PluginRegisters pluginRegisters = new PluginRegisters();

        registerMap.put(PluginRegisters.RegisterType.RECIPE.toString(),
                pluginRegisters.register(getPlugin(), PluginRegisters.RegisterType.RECIPE,
                new RocketBooster("I", Material.REDSTONE_BLOCK),
                new RocketBooster("II", Material.IRON_BLOCK),
                new RocketBooster("III", Material.GOLD_BLOCK),
                new RocketBooster("IV", Material.DIAMOND_BLOCK),
                new RocketBooster("V", Material.EMERALD_BLOCK),
                new RocketControls(),
                new RepairStation(),
                new RepairTank(),
                new RepairStand(),
                new RocketFlyZone(),
                new RocketFuelJacket(Material.LEATHER_CHESTPLATE),
                new RocketFuelJacket(Material.IRON_CHESTPLATE),
                new RocketFuelJacket(Material.GOLDEN_CHESTPLATE),
                new RocketFuelJacket(Material.DIAMOND_CHESTPLATE)
        ));

        for (Material material : new ArrayList<Material>() {{
            add(Material.LEATHER);
            add(Material.IRON_INGOT);
            add(Material.GOLD_INGOT);
            add(Material.DIAMOND);
        }}) {

            for (Boolean bool : new ArrayList<Boolean>() {{
                add(true);
                add(false);
            }}) {

                registerMap.put(PluginRegisters.RegisterType.RECIPE.toString(),
                        registerMap.get(PluginRegisters.RegisterType.RECIPE.toString()) +
                                pluginRegisters.register(getPlugin(), PluginRegisters.RegisterType.RECIPE,
                                new RocketBoots(material, bool, bool),
                                new RocketBoots(material, bool, !bool)
                        ));

            }

        }

        registerMap.put(PluginRegisters.RegisterType.EVENT.toString(),
                pluginRegisters.registerAll(getPlugin(), PluginRegisters.RegisterType.EVENT));

        registerMap.put(PluginRegisters.RegisterType.TASK.toString(),
                pluginRegisters.registerAll(getPlugin(), PluginRegisters.RegisterType.TASK));

        new RocketVariant().init();
        new RocketEnhancement().init();

        getCommand("rocket").setExecutor(new RocketExecutor());
        getCommand("fuel").setExecutor(new RocketExecutor());

        getConfig().options().copyDefaults(true);
        saveConfig();

        Set<String> pluginList = new HashSet<>();

        if (pluginWorldGuard != null) {
            setWorldGuard((WorldGuardPlugin) pluginWorldGuard);
            pluginList.add("WorldGuard");
        }

        if (pluginVault != null) {
            RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
            if (economyProvider != null) {
                setVaultEconomy(economyProvider.getProvider());
                pluginList.add("Vault");
            }
        }

        new RocketFunctions().reloadFlyZones(true);
        int zoneList = registerMap.get("zone") != null ? registerMap.get("zone") : 0;

        Bukkit.getLogger().log(Level.INFO, "[" + pluginName + "] "
                + "Events: " + registerMap.get("event") + " | "
                + "Tasks: " + registerMap.get("task") + " | "
                + "Recipes: " + registerMap.get("recipe") + " | "
                + "Variants: " + (registerMap.get("variant") - 1) + " | "
                + "Enhancements: " + (registerMap.get("enhancement") - 1) + " | "
                + "Zones: " + zoneList);

        if (pluginList.size() > 0)
            Bukkit.getLogger().log(Level.INFO,
                    "[" + pluginName + "] Hooked: " + StringUtils.join(pluginList, ", "));

    }

    public void onDisable() {

        for (UUID uuid : rocketUsage)
            new RocketFunctions().disableRocketBoots(Bukkit.getPlayer(uuid), false, false, false, false, false);

    }

}