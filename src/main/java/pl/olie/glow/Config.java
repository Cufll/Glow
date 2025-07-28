package pl.olie.glow;

import java.util.List;

public class Config {
    public Config(Glow plugin) {
        this.plugin = plugin;
        loadConfigValues();
    }
    private final Glow plugin;
    private List<String> disabledWorlds;
    private String noPerms;
    private String glowingOn;
    private String glowingOff;
    private String disabledWorldMessage;
    private String usageMessage;
    private String glowingOnOther;
    private String glowingOffOther;
    private String playerOffline;
    private boolean offOnLeft;
    private String onPlaceholder;
    private String offPlaceholder;
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
    public String getNoPerms(){return noPerms;}
    public List<String> getDisabledWorlds(){return disabledWorlds;}
    public String getGlowingOn(){return glowingOn;}
    public String getGlowingOff(){return glowingOff;}
    public String getDisabledWorldMessage(){return disabledWorldMessage;}
    public String getUsageMessage(){return usageMessage;}
    public String getGlowingOnOther(){return glowingOnOther;}
    public String getGlowingOffOther(){return glowingOffOther;}
    public String getPlayerOffline(){return playerOffline;}
    public boolean getOffOnLeft(){return offOnLeft;}
    public String getOnPlaceholder(){return onPlaceholder;}
    public String getOffPlaceholder(){return offPlaceholder;}
}
