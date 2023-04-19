package de.gtec.adminutils.event;

import de.gtec.adminutils.AdminUtils;
import de.gtec.adminutils.model.god.GodModeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventProcessor implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player player) {
            GodModeManager gmm = AdminUtils.getGodModeManager();
            if (gmm.isOnGodMode(player))
                e.setCancelled(true);
        }
    }


}
