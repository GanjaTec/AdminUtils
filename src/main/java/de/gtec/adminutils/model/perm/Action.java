package de.gtec.adminutils.model.perm;

import org.bukkit.permissions.Permission;

public enum Action {
    GAMEMODE_CHANGE(Permissions.GM),
    PING_SCAN(Permissions.SCAN),
    ANY(Permissions.ALL);

    private final Permission perm;

    Action(Permission perm) {
        this.perm = perm;
    }

    public Permission permission() {
        return perm;
    }
}
