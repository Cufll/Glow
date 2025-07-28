package pl.olie.glow;

import java.util.List;

public class Config {
    public Config(Glow plugin) {
        this.plugin = plugin;
        loadConfigValues();
    }
    private final Glow plugin;
    public List<String> disabledWorlds;
    public String Noperms;
    public String GlowingOn;
    public String GlowingOff;
    public String DisabledWorldMessage;
    public String UsageMessage;
    public String GlowingOnOther;
    public String GlowingOffOther;
    public String PlayerOffline;
    public boolean OffOnLeft;
    public String OnPlaceholder;
    public String OffPlaceholder;
    public void loadConfigValues() {
        this.Noperms = plugin.getConfig().getString("No-Perms").replaceAll("&", "§");
        this.GlowingOn = plugin.getConfig().getString("Glowing-On").replaceAll("&", "§");
        this.GlowingOff = plugin.getConfig().getString("Glowing-Off").replaceAll("&", "§");
        this.disabledWorlds = plugin.getConfig().getStringList("DisabledWorlds");
        this.DisabledWorldMessage = plugin.getConfig().getString("Disabled-world-message").replaceAll("&", "§");
        this.UsageMessage = plugin.getConfig().getString("Usage-message").replaceAll("&", "§");
        this.GlowingOnOther = plugin.getConfig().getString("Glowing-On-other").replaceAll("&", "§");
        this.GlowingOffOther = plugin.getConfig().getString("Glowing-Off-other").replaceAll("&", "§");
        this.PlayerOffline = plugin.getConfig().getString("Player-offline").replaceAll("&", "§");
        this.OffOnLeft = plugin.getConfig().getBoolean("Disable-glowing-on-left");
        Glow.prefix =  plugin.getConfig().getString("Prefix");
        this.OnPlaceholder = this.plugin.getConfig().getString("OnPlaceholder").replaceAll("&", "§");
        this.OffPlaceholder = this.plugin.getConfig().getString("OffPlaceholder").replaceAll("&", "§");
    }
}
