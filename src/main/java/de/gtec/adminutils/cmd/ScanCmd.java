package de.gtec.adminutils.cmd;

import de.gtec.adminutils.AdminUtils;
import de.gtec.adminutils.cmd.msg.MsgSender;
import de.gtec.adminutils.model.perm.Action;
import de.gtec.adminutils.model.perm.PermissionSecurityManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScanCmd implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("pingscan") && sender instanceof Player player) {
            final MsgSender msg = MsgSender.of(player);
            final String pf = AdminUtils.getPrefix();
            final PermissionSecurityManager perm = AdminUtils.getPermissionManager();

            if (!perm.checkAction(player, Action.PING_SCAN))
                return msg.msg(pf + "§cYou don't have Permission to execute this command!").send();

            int len = args.length;
            if (len == 0 || args[0].isBlank())
                return usage(msg, pf);

            String target = args[0];
            boolean isIp = target.contains(".");
            return isIp ? onIPScan(target, msg, pf) : onNameScan(target, msg, pf);

        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        int len = args.length;
        return switch (len) {
            case 1 -> List.of("<IP | Name>");
            default -> null;
        };
    }

    private boolean onIPScan(String ip, MsgSender msg, String pf) {

        return true;
    }

    private boolean onNameScan(String name, MsgSender msg, String pf) {

        return true;
    }

    private boolean usage(MsgSender msg, String pf) {
        return msg.msg(pf + "§cUsage: §e/scan §7<§eIP §7| §eName§7>")
                .send();
    }

}
