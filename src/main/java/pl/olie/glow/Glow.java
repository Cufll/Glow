package pl.olie.glow;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class Glow extends JavaPlugin {
    public static String prefix;

    @Override
    public void onEnable() {
        prefix = getConfig().getString("Prefix");
        GlowCommand glowCommand = new GlowCommand(this);
        Objects.requireNonNull(getCommand("glow")).setExecutor(new GlowCommand(this));
        getServer().getPluginManager().registerEvents(new WorldChangeListener(this), this);
        getServer().getPluginManager().registerEvents(new GlowCommand(this), this);
        new GlowPlaceholder(this).register();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
    }
}
