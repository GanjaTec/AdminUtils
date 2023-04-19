package de.gtec.adminutils.model.gm;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameModeChanger {

    public static void setGamemode(@NotNull Player player, @NotNull GameMode mode) {
        player.setGameMode(mode);
    }

    public static void setCreative(@NotNull Player player) {
        setGamemode(player, GameMode.CREATIVE);
    }

    public static void setSurvival(@NotNull Player player) {
        setGamemode(player, GameMode.SURVIVAL);
    }

    public static void setAdventure(@NotNull Player player) {
        setGamemode(player, GameMode.ADVENTURE);
    }

    public static void setSpectator(@NotNull Player player) {
        setGamemode(player, GameMode.SPECTATOR);
    }

}
