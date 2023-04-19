package de.gtec.adminutils.model.god;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GodModeManager {

    private final GodModeChanger changer;
    private final Map<UUID, GodMode> onGodMode;

    public GodModeManager() {
        this.changer = new GodModeChanger();
        this.onGodMode = new ConcurrentHashMap<>();
    }

    public boolean setOnGodMode(@NotNull Player player, @NotNull GodMode mode) {
        UUID uuid = player.getUniqueId();
        if (onGodMode.containsKey(uuid))
            return false;

        onGodMode.put(uuid, mode);
        changer.setGodMode(player, mode);
        return true;
    }

    public boolean isOnGodMode(@NotNull Player player) {
        UUID uuid = player.getUniqueId();
        return onGodMode.containsKey(uuid);
    }

    public void disable(@NotNull Player player) {
        if (!isOnGodMode(player))
            return;

        UUID uuid = player.getUniqueId();
        changer.disableGodMode(player, onGodMode.get(uuid));
        onGodMode.remove(uuid);
    }


}
