package de.gtec.adminutils.model.perm;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PermissionSecurityManager implements SecurityManager {

    @Override
    public boolean checkAction(@NotNull Player player, @NotNull Action action) {
        return player.hasPermission(action.permission());
    }


}
