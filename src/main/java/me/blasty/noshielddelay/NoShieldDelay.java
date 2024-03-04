package me.blasty.noshielddelay;

import me.blasty.noshielddelay.listener.ShieldListener;
import me.vaperion.blade.Blade;
import me.vaperion.blade.bukkit.BladeBukkitPlatform;
import org.bukkit.plugin.java.JavaPlugin;

public class NoShieldDelay extends JavaPlugin {

    private static NoShieldDelay instance;

    public static NoShieldDelay getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getLogger().info("Starting NoShieldDelay " + getDescription().getVersion() + "...");

        instance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new ShieldListener(), this);

        Blade.forPlatform(new BladeBukkitPlatform(this))
                .config(cfg -> {
                    cfg.setFallbackPrefix("shield");
                    cfg.setDefaultPermissionMessage("No permission!");
                })
                .build()
                .registerPackage(NoShieldDelay.class, "me.blasty.noshielddelay.command");
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}