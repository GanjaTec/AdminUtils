package de.gtec.adminutils.model.god;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodModeChanger {

    GodModeChanger() {
    }

    public void setGodMode(@NotNull Player player, @NotNull GodMode mode) {
        if (mode.isDisable()) {
            player.setGlowing(false);
            player.setFlying(false);
            if (player.getGameMode() == GameMode.ADVENTURE || player.getGameMode() == GameMode.SURVIVAL)
                player.setAllowFlight(false);
            // TODO
            return;
        }
        if (mode.isDefault()) {
            // do nothing
        }
        if (mode.isGlowing()) {
            player.setGlowing(true);
        }
        if (mode.isFlying()) {
            player.setAllowFlight(true);
            player.setFlying(true);
        }
        if (mode.isAlmighty()) {
            // TODO
            player.sendMessage("§7Not implemented yet...");
        }
    }

    public void disableGodMode(@NotNull Player player, @NotNull GodMode previous) {
        setGodMode(player, GodMode.of(GodMode.DISABLE.mask() | previous.mask()));

    }

}
