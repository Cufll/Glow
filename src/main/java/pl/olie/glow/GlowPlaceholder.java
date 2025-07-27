package pl.olie.glow;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GlowPlaceholder extends PlaceholderExpansion{
    private final Glow plugin;
    private String OnPlaceholder;
    private String OffPlaceholder;
    public void loadConfigValues() {
        this.OnPlaceholder = this.plugin.getConfig().getString("OnPlaceholder");
        this.OffPlaceholder = this.plugin.getConfig().getString("OffPlaceholder");
    }
    public GlowPlaceholder(Glow plugin) {
        this.plugin = plugin;
        loadConfigValues();
    }
    @Override
    public @NotNull String getIdentifier() {
        return "glow";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Cufl";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }
    @Override
    public String onPlaceholderRequest(Player player,String identifier) {
        if (identifier.equalsIgnoreCase("status")) {
            return player.isGlowing() ? OnPlaceholder : OffPlaceholder;
        }
        return null;
    }
}
