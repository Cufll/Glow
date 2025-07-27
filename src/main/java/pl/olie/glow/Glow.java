package pl.olie.glow;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class Glow extends JavaPlugin {
    public Map<UUID, Boolean> glowingStatus = new HashMap<>();
    public static String prefix;

    @Override
    public void onEnable() {
        prefix = getConfig().getString("Prefix");
        saveDefaultConfig();
        GlowCommand glowCommand = new GlowCommand(this);
        Objects.requireNonNull(getCommand("glow")).setExecutor(new GlowCommand(this));
        getServer().getPluginManager().registerEvents(new WorldChangeListener(this), this);
        getServer().getPluginManager().registerEvents(new GlowCommand(this), this);
        new GlowPlaceholder(this).register();
    }

    @Override
    public void onDisable() {
    }
}
