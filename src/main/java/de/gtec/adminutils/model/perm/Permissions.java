package de.gtec.adminutils.model.perm;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

public class Permissions {

    public static final Permission ALL = new Permission("admin.util.*");
    public static final Permission GM = new Permission("admin.util.gm");
    public static final Permission SCAN = new Permission("admin.util.scan");
    public static final Permission SET_PERM = new Permission("admin.util.perm.set");
    public static final Permission GET_PERM = new Permission("admin.util.perm.get");

    public static void initPermissions(PluginManager manager) {
        manager.addPermission(ALL);
        manager.addPermission(GM);
        manager.addPermission(SCAN);
        manager.addPermission(SET_PERM);
        manager.addPermission(GET_PERM);
    }

}
