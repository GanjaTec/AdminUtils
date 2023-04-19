package de.gtec.adminutils.cmd;

import de.gtec.adminutils.AdminUtils;
import de.gtec.adminutils.cmd.msg.MsgSender;
import de.gtec.adminutils.model.god.GodMode;
import de.gtec.adminutils.model.god.GodModeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GodmodeCmd implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("godmode") && sender instanceof Player player) {

            int len = args.length;
            GodModeManager gmm = AdminUtils.getGodModeManager();
            String pf = AdminUtils.getPrefix();
            MsgSender msg = MsgSender.of(player);
            return switch (len) {
                case 0 -> {
                    boolean cond = gmm.isOnGodMode(player);
                    if (cond)
                        gmm.disable(player);
                    else
                        gmm.setOnGodMode(player, GodMode.DEFAULT);
                    yield msg.condition(cond)
                            .msg(pf + "§6GodMode§a disabled")
                            .orElse(pf + "§6GodMode§a enabled")
                            .send();
                }
                case 1 -> {
                    GodMode mode = getMode(args, pf, msg);
                    if (mode == null) yield notFound(pf, msg, "Mode");

                    gmm.setOnGodMode(player, mode);
                    yield msg.msg(pf + "§aGodMode applied on §6" + player.getName()).send();
                }
                default -> {
                    GodMode mode = getMode(args, pf, msg);
                    if (mode == null) yield notFound(pf, msg, "Mode");

                    Player target = Bukkit.getPlayer(args[2]);
                    if (target == null)
                        yield notFound(pf, msg, "Target");

                    gmm.setOnGodMode(target, mode);
                    yield msg.msg(pf + "§aGodMode applied on §6" + target.getName()).send();
                }
            };
        }
        return true;
    }

    private boolean notFound(String pf, MsgSender msg, String target) {
        return msg.msg(pf + "§6GodMode: §c" + target + " not found!").send();
    }

    private GodMode getMode(@NotNull String @NotNull [] args, String pf, MsgSender msg) {
        return switch (args[0]) {
            case "default", "d", "1" -> GodMode.DEFAULT;
            case "glowing", "g", "2" -> GodMode.GLOWING;
            case "flying", "f", "3" -> GodMode.FLYING;
            case "almighty", "am", "a", "4" -> GodMode.ALMIGHTY;
            default -> GodMode.DISABLE;
        };
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        int len = args.length;
        return switch (len) {
            case 1 -> List.of("default", "glowing", "flying", "almighty");
            case 2 -> Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList();
            default -> null;
        };
    }

}
