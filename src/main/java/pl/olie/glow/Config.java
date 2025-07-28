package pl.olie.glow;

import java.util.List;

public class Config {
    public Config(Glow plugin) {
        this.plugin = plugin;
        loadConfigValues();
    }
    private final Glow plugin;
    public List<String> disabledWorlds;
    public String noPerms;
    public String glowingOn;
    public String glowingOff;
    public String disabledWorldMessage;
    public String usageMessage;
    public String glowingOnOther;
    public String glowingOffOther;
    public String playerOffline;
    public boolean offOnLeft;
    public String onPlaceholder;
    public String offPlaceholder;
    public void loadConfigValues() {
        this.noPerms = plugin.getConfig().getString("No-Perms").replaceAll("&", "§");
        this.glowingOn = plugin.getConfig().getString("Glowing-On").replaceAll("&", "§");
        this.glowingOff = plugin.getConfig().getString("Glowing-Off").replaceAll("&", "§");
        this.disabledWorlds = plugin.getConfig().getStringList("DisabledWorlds");
        this.disabledWorldMessage = plugin.getConfig().getString("Disabled-world-message").replaceAll("&", "§");
        this.usageMessage = plugin.getConfig().getString("Usage-message").replaceAll("&", "§");
        this.glowingOnOther = plugin.getConfig().getString("Glowing-On-other").replaceAll("&", "§");
        this.glowingOffOther = plugin.getConfig().getString("Glowing-Off-other").replaceAll("&", "§");
        this.playerOffline = plugin.getConfig().getString("Player-offline").replaceAll("&", "§");
        this.offOnLeft = plugin.getConfig().getBoolean("Disable-glowing-on-left");
        Glow.prefix =  plugin.getConfig().getString("Prefix");
        this.onPlaceholder = this.plugin.getConfig().getString("OnPlaceholder").replaceAll("&", "§");
        this.offPlaceholder = this.plugin.getConfig().getString("OffPlaceholder").replaceAll("&", "§");
    }
}
