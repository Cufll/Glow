package pl.olie.glow.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.olie.glow.Glow;
import pl.olie.glow.config.Config;

public class PlayerQuitListener implements Listener {
    private final Config config;
    public PlayerQuitListener(Glow plugin) {
        this.config = plugin.getConfigValues();
    }
    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(config.getOffOnLeft()){
            if(player.isGlowing()) {
                player.setGlowing(false);
            }
        }
    }
}
