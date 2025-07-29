package pl.olie.glow;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import pl.olie.glow.config.Config;

import java.util.ArrayList;
import java.util.List;


public class GlowCommand implements CommandExecutor, Listener, TabCompleter {
    private final Glow plugin;
    private final Config config;
    public GlowCommand(Glow plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigValues();

    }
    public boolean onCommand(CommandSender sender, Command cmd,String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Glow.prefix + "Only players can use this command.");
            return true;
        }
        if (args.length == 0) {
            if (!player.hasPermission("glow.use")) {
                player.sendMessage(Glow.prefix + config.getNoPerms());
                return true;
            }
            if (player.isGlowing()) {
                player.setGlowing(false);
                player.sendMessage(Glow.prefix + config.getGlowingOff());
            } else {
                if (config.getDisabledWorlds().contains(player.getWorld().getName())) {
                    sender.sendMessage(Glow.prefix + config.getDisabledWorldMessage());
                } else {
                    player.setGlowing(true);
                    player.sendMessage(Glow.prefix + config.getGlowingOn());
                }
            }
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("glow.reload")) {
                    sender.sendMessage(Glow.prefix + config.getNoPerms());
                } else {
                    plugin.reloadConfig();
                    config.loadConfigValues();
                    sender.sendMessage(Glow.prefix + "§aGlow Config reloaded!");
                }
                return true;
            } else if (args[0].equalsIgnoreCase("author")) {
                sender.sendMessage("Author: Cufl");
                sender.sendMessage("Version: 1.0");
                return true;
            }else if(sender.hasPermission("glow.other")){
                String nick = args[0];
                Player target = Bukkit.getPlayer(nick);
                if(target != null && player.isOnline()){
                    if(target.isGlowing()){
                        target.setGlowing(false);
                        player.sendMessage(Glow.prefix + config.getGlowingOffOther().replace("%target%", nick));
                    }else{
                        target.setGlowing(true);
                        player.sendMessage(Glow.prefix + config.getGlowingOnOther().replace("%target%", nick));
                    }
                }else {
                    player.sendMessage(Glow.prefix + config.getPlayerOffline().replace("%target%", nick));
                    return true;
                }
            }
            else {
                sender.sendMessage(Glow.prefix + config.getUsageMessage());
                return true;
            }
        }

        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            List<String> tab = new ArrayList<>();
            if (sender.hasPermission("glow.reload")) {
                tab.add("reload");
                tab.add("author");
            }
            if (sender.hasPermission("glow.other")) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (online.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                        tab.add(online.getName());
                    }
                }
            }
            return tab;
        }
        return List.of();
      }
    }