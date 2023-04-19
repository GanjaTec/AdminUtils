package de.gtec.adminutils.cmd.msg;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

public class MsgCondition {

    private AtomicBoolean value;

    MsgCondition() {
        this.value = null;
    }

    boolean isSet() {
        return value != null;
    }

    @Nullable
    AtomicBoolean get() {
        return value;
    }

    void set(boolean v) {
        if (value == null)
            value = new AtomicBoolean(v);
        else
            value.set(v);
    }

}
