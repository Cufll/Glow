package pl.olie.glow;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import pl.olie.glow.config.Config;

public class GlowPlaceholder extends PlaceholderExpansion{
    private final Glow plugin;
    private final Config config;
    public GlowPlaceholder(Glow plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigValues();
    }
    @Override
    public String getIdentifier() {
        return "glow";
    }

    @Override
    public String getAuthor() {
        return "Cufl";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }
    @Override
    public String onPlaceholderRequest(Player player,String identifier) {
        if (identifier.equalsIgnoreCase("status")) {
            return player.isGlowing() ? config.getOnPlaceholder() : config.getOffPlaceholder();
        }
        return null;
    }
}
