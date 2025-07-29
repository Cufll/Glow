package pl.olie.glow.config;

import pl.olie.glow.Glow;

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
        this.noPerms = colorize(plugin.getConfig().getString("No-Perms"));
        this.glowingOn = colorize(plugin.getConfig().getString("Glowing-On"));
        this.glowingOff = colorize(plugin.getConfig().getString("Glowing-Off"));
        this.disabledWorlds = plugin.getConfig().getStringList("DisabledWorlds");
        this.disabledWorldMessage = colorize(plugin.getConfig().getString("Disabled-world-message"));
        this.usageMessage = colorize(plugin.getConfig().getString("Usage-message"));
        this.glowingOnOther = colorize(plugin.getConfig().getString("Glowing-On-other"));
        this.glowingOffOther = colorize(plugin.getConfig().getString("Glowing-Off-other"));
        this.playerOffline = colorize(plugin.getConfig().getString("Player-offline"));
        this.offOnLeft = plugin.getConfig().getBoolean("Disable-glowing-on-left");
        Glow.prefix =  plugin.getConfig().getString("Prefix");
        this.onPlaceholder = colorize(plugin.getConfig().getString("OnPlaceholder"));
        this.offPlaceholder = colorize(plugin.getConfig().getString("OffPlaceholder"));
    }
    private String colorize(String text) {
        if(text == null){
            return "";
        }
        return text.replace("&", "§");
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
