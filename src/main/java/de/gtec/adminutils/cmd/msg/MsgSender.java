package de.gtec.adminutils.cmd.msg;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MsgSender {

    private final Player player;
    private String[] msg, orElse;
    private final MsgCondition cond;

    private MsgSender(@NotNull Player player) {
        this.player = player;
        this.msg = null;
        this.cond = new MsgCondition();
    }

    public static MsgSender of(@NotNull Player player) {
        return new MsgSender(player);
    }

    @SuppressWarnings("ConstantConditions")
    public boolean send() {
        String[] sndMsg;
        if (cond.isSet()) {
            sndMsg = cond.get().get() ? msg : orElse;
        } else {
            sndMsg = msg != null ? msg : orElse;
        }
        if (sndMsg != null)
            player.sendMessage(sndMsg);
        return true;
    }

    public MsgSender msg(@NotNull String... msg) {
        this.msg = msg;
        return this;
    }

    public MsgSender orElse(@NotNull String... msg) {
        this.orElse = msg;
        return this;
    }

    public MsgSender condition(boolean b) {
        cond.set(b);
        return this;
    }

}
