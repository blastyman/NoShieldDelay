package me.blasty.noshielddelay.listener;

import me.blasty.noshielddelay.NoShieldDelay;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShieldListener implements Listener {
    private static NoShieldDelay instance = NoShieldDelay.getInstance();
    private static FileConfiguration config = instance.getConfig();

    public static int MAX_SHIELD_DELAY = config.getInt("max-shield-delay");
    public static int MIN_SHIELD_DELAY = config.getInt("min-shield-delay");

    @EventHandler
    public void onShieldInteract(PlayerInteractEvent event) {
        if (!event.getMaterial().equals(Material.SHIELD)) return;
        if (event.isCancelled()) return;

        var player = event.getPlayer();
        player.setShieldBlockingDelay(Math.min(MAX_SHIELD_DELAY, Math.min(MIN_SHIELD_DELAY, player.getPing() / 50)));
    }
}
