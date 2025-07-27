package pl.olie.glow;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
    public void loadConfigValues() {
        this.Noperms = plugin.getConfig().getString("Noperms");
        this.GlowingOn = plugin.getConfig().getString("GlowingOn");
        this.GlowingOff = plugin.getConfig().getString("GlowingOff");
        this.disabledWorlds = plugin.getConfig().getStringList("DisabledWorlds");
        this.DisabledWorldMessage = plugin.getConfig().getString("Disabled_world_message");
        this.UsageMessage = plugin.getConfig().getString("Usage_message");
        Glow.prefix =  plugin.getConfig().getString("Prefix");
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
                plugin.glowingStatus.put(player.getUniqueId(), false);
                player.sendMessage(Glow.prefix + GlowingOff);
            } else {
                if (disabledWorlds.contains(player.getWorld().getName())) {
                    sender.sendMessage(Glow.prefix + DisabledWorldMessage);
                } else {
                    player.setGlowing(true);
                    plugin.glowingStatus.put(player.getUniqueId(), true);

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
            }
            else {
                sender.sendMessage(Glow.prefix + UsageMessage);
                return true;
            }
        }

        return false;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.isGlowing() && (!player.hasPermission("Glow.use"))) {
            player.setGlowing(false);
        }
        }
    }