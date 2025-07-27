package pl.olie.glow;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GlowCommand implements CommandExecutor, Listener {
    private final Glow plugin;
    private List<String> disabledWorlds;
    private String Noperms;
    private String GlowingOn;
    private String GlowingOff;
    private String DisabledWorldMessage;
    private String UsageMessage;
    private String GlowingOnOther;
    private String GlowingOffOther;
    private String PlayerOffline;
    private boolean OffOnLeft;
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
        Glow.prefix =  plugin.getConfig().getString("Prefix").replaceAll("&", "§");
    }
    public GlowCommand(Glow plugin) {
        this.plugin = plugin;
        loadConfigValues();
    }
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Glow.prefix + "Only players can use this command.");
            return true;
        }
        if (!player.hasPermission("Glow.use")) {
            player.sendMessage(Glow.prefix + Noperms);
            return true;
        }
        if (args.length == 0) {
            if (player.isGlowing()) {
                player.setGlowing(false);
                player.sendMessage(Glow.prefix + GlowingOff);
            } else {
                if (disabledWorlds.contains(player.getWorld().getName())) {
                    sender.sendMessage(Glow.prefix + DisabledWorldMessage);
                } else {
                    player.setGlowing(true);
                    player.sendMessage(Glow.prefix + GlowingOn);
                }
            }
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("Glow.reload")) {
                    sender.sendMessage(Glow.prefix + Noperms);
                } else {
                    plugin.reloadConfig();
                    loadConfigValues();
                    sender.sendMessage(Glow.prefix + "§aGlow Config reloaded!");
                }
                return true;
            } else if (args[0].equalsIgnoreCase("author")) {
                sender.sendMessage("Author: Cufl");
                sender.sendMessage("Version: 1.0");
                return true;
            }else if(sender.hasPermission("Glow.other")){
                String nick = args[0];
                Player target = Bukkit.getPlayer(nick);
                if(target != null && player.isOnline()){
                    if(target.isGlowing()){
                        target.setGlowing(false);
                        player.sendMessage(Glow.prefix + GlowingOffOther + nick);
                    }else{
                        target.setGlowing(true);
                        player.sendMessage(Glow.prefix + GlowingOnOther + nick);
                    }
                }else {
                    player.sendMessage(Glow.prefix + PlayerOffline);
                    return true;
                }
            }
            else {
                sender.sendMessage(Glow.prefix + UsageMessage);
                return true;
            }
        }

        return false;
    }
    @EventHandler
    public void onLeft(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(OffOnLeft){
            if(player.isGlowing()) {
                player.setGlowing(false);
            }
        }
        }
    }