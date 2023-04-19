package de.gtec.adminutils.cmd;

import de.gtec.adminutils.AdminUtils;
import de.gtec.adminutils.cmd.msg.MsgSender;
import de.gtec.adminutils.model.gm.GameModeChanger;
import de.gtec.adminutils.model.perm.Action;
import de.gtec.adminutils.model.perm.PermissionSecurityManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public class GamemodeCmd implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            final MsgSender msg = MsgSender.of(player);
            final String pf = AdminUtils.getPrefix();
            final PermissionSecurityManager perm = AdminUtils.getPermissionManager();

            if (!perm.checkAction(player, Action.GAMEMODE_CHANGE))
                return msg.msg(pf + "§cYou don't have Permission to execute this command!").send();

            int len = args.length;
            if (len == 0 || args[0].isBlank())
                return usage(msg, pf);

            String mode = args[0];
            Queue<Player> targets;
            if (len == 1)
                targets = new ArrayDeque<>(List.of(player));

            else {
                String name = args[1];
                if (name.equals("@a")) {
                    Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                    targets = new ArrayDeque<>(onlinePlayers);
                } else {
                    Player result = Bukkit.getPlayer(name);
                    if (result == null)
                        return msg.msg(pf + "§cTarget player not found!").send();

                    targets = new ArrayDeque<>(List.of(result));
                }
            }
            switch (mode) {
                case "c" -> {
                    while (!targets.isEmpty()) {
                        Player target = targets.poll();
                        GameModeChanger.setCreative(target);
                        msg.msg(pf + "§aGameMode of " + target.getName() + " set to §dCREATIVE").send();
                    }
                }
                case "s" -> {
                    while (!targets.isEmpty()) {
                        Player target = targets.poll();
                        GameModeChanger.setSurvival(target);
                        msg.msg(pf + "§aGameMode of " + target.getName() + " set to §dSURVIVAL").send();
                    }
                }
                case "a" -> {
                    while (!targets.isEmpty()) {
                        Player target = targets.poll();
                        GameModeChanger.setAdventure(target);
                        msg.msg(pf + "§aGameMode of " + target.getName() + " set to §dADVENTURE").send();
                    }
                }
                case "sp" -> {
                    while (!targets.isEmpty()) {
                        Player target = targets.poll();
                        GameModeChanger.setSpectator(target);
                        msg.msg(pf + "§aGameMode of " + target.getName() + " set to §dSPECTATOR").send();
                    }
                }
                default -> usage(msg, pf);
            };
        }
        return true;

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        int len = args.length;
        return switch (len) {
            case 1 -> List.of("c", "s", "a", "sp");
            case 2 -> Bukkit.getOnlinePlayers()
                    .stream()
                    .map(HumanEntity::getName)
                    .toList();
            default -> null;
        };
    }

    private boolean usage(MsgSender msg, String pf) {
        return msg.msg(pf + "§cUsage: §e/gm §7<§ec §7| §es §7| §ea §7| §esp§7>")
                .send();
    }

}
