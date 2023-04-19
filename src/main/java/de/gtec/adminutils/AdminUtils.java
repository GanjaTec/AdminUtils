package de.gtec.adminutils;

import de.gtec.adminutils.cmd.GamemodeCmd;
import de.gtec.adminutils.cmd.GodmodeCmd;
import de.gtec.adminutils.cmd.ItemCommand;
import de.gtec.adminutils.cmd.ScanCmd;
import de.gtec.adminutils.event.EventProcessor;
import de.gtec.adminutils.model.god.GodModeManager;
import de.gtec.adminutils.model.perm.PermissionSecurityManager;
import de.gtec.adminutils.model.perm.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminUtils extends JavaPlugin {

    private static String PREFIX = "§7[§3Utils§7]: §r";
    private static Plugin pl;
    private static PermissionSecurityManager permManager;

    private static GodModeManager godModeManager;

    public static Plugin pl() {
        return pl;
    }

    public static String getPrefix() {
        return PREFIX;
    }

    public static void setPrefix(String prefix) {
        AdminUtils.PREFIX = prefix;
    }

    public static PermissionSecurityManager getPermissionManager() {
        return permManager;
    }

    public static GodModeManager getGodModeManager() {
        return godModeManager;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        // Plugin startup logic
        pl = this;
        permManager = new PermissionSecurityManager();
        godModeManager = new GodModeManager();

        getCommand("gamemode").setExecutor(new GamemodeCmd());
        getCommand("pingscan").setExecutor(new ScanCmd());
        getCommand("item").setExecutor(new ItemCommand());
        getCommand("godmode").setExecutor(new GodmodeCmd());

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new EventProcessor(), pl());
        Permissions.initPermissions(manager);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
