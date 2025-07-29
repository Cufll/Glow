package pl.olie.glow;

import org.bukkit.plugin.java.JavaPlugin;
import pl.olie.glow.listeners.WorldChangeListener;

import java.util.Objects;

public final class Glow extends JavaPlugin {
    public static String prefix;
    private Config configValues;
    public Config getConfigValues(){
        return configValues;
    }
    @Override
    public void onEnable() {
        configValues = new Config(this);
        prefix = getConfig().getString("Prefix").replaceAll("&", "§");;
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
