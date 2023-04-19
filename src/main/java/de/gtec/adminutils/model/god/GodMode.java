package de.gtec.adminutils.model.god;

import org.jetbrains.annotations.NotNull;

public class GodMode {

    public static final GodMode
            DEFAULT = new GodMode(0x1),
            GLOWING = new GodMode(DEFAULT.mask | 0x2),
            FLYING = new GodMode(GLOWING.mask | 0x4),
            ALMIGHTY = new GodMode(DEFAULT.mask | GLOWING.mask | FLYING.mask),
            DISABLE = new GodMode(0x8);

    private static final GodMode MAX = new GodMode(0x10);

    private final int mask;

    public static GodMode of(int mask) {
        int dmask = MAX.mask;
        if (mask < 0 && mask > dmask)
            throw new IllegalArgumentException("Mask must be between 0 and DISABLE.mask (" + dmask + ")");

        return new GodMode(mask);
    }

    public static boolean equalsMask(@NotNull GodMode expected, @NotNull GodMode actual) {
        return actual.mask == expected.mask;
    }

    public static boolean containsMask(@NotNull GodMode expected, @NotNull GodMode actual) {
        int expMask = expected.mask;
        return (actual.mask & expMask) == expMask;
    }

    private GodMode(int mask) {
        this.mask = mask;
    }

    public int mask() {
        return mask;
    }

    public boolean isDefault() {
        return containsMask(DEFAULT, this);
    }

    public boolean isGlowing() {
        return containsMask(GLOWING, this);
    }

    public boolean isFlying() {
        return containsMask(FLYING, this);
    }

    public boolean isAlmighty() {
        return containsMask(ALMIGHTY, this);
    }

    public boolean isDisable() {
        return containsMask(DISABLE, this);
    }
}
