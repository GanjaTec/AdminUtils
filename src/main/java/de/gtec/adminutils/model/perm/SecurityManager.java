package de.gtec.adminutils.model.perm;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface SecurityManager {

    boolean checkAction(@NotNull Player player, @NotNull Action action);

}
