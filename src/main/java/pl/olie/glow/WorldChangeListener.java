package pl.olie.glow;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WorldChangeListener implements Listener {
    private final Config config;
    public WorldChangeListener(Glow plugin) {
        this.config = plugin.getConfigValues();
    }
    protected final Map<UUID, String> glowingOnWorldChange = new HashMap<>();
    public boolean hadGlow(Player player) {
        return glowingOnWorldChange.containsKey(player.getUniqueId());
    }
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        List<String> disabledWorlds = config.getDisabledWorlds();
        if (disabledWorlds.contains(event.getPlayer().getWorld().getName())) {
            if(player.isGlowing()) {
                player.setGlowing(false);
                String name = player.getName();
                glowingOnWorldChange.put(player.getUniqueId(), name);
            }
        }else{
            if(hadGlow(player) && (player.hasPermission("Glow.use"))) {
                player.setGlowing(true);
                glowingOnWorldChange.remove(player.getUniqueId());
            }
        }
    }
}
