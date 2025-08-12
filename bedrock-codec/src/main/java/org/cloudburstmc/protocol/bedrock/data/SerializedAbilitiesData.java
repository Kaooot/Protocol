package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.command.CommandPermissionLevel;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kaooot
 */
@Data
@ToString
public class SerializedAbilitiesData {

    private long targetPlayerRawId;
    private PlayerPermissionLevel playerPermissions;
    private CommandPermissionLevel commandPermissions;
    private List<SerializedLayer> layers = new ObjectArrayList<>();

    @Data
    @ToString
    public static class SerializedLayer {
        private SerializedAbilitiesLayer serializedLayer;
        private final Set<AbilitiesIndex> abilitiesSet = EnumSet.noneOf(AbilitiesIndex.class);
        private final Set<AbilitiesIndex> abilityValues = EnumSet.noneOf(AbilitiesIndex.class);
        private float flySpeed;
        /**
         * @since v776
         */
        private float verticalFlySpeed;
        private float walkSpeed;

        public enum SerializedAbilitiesLayer {
            CUSTOM_CACHE,
            BASE,
            SPECTATOR,
            COMMANDS,
            /**
             * @since v557
             */
            EDITOR,
            /**
             * @since v712
             */
            LOADING_SCREEN;

            private static final SerializedAbilitiesLayer[] VALUES = values();

            public static SerializedAbilitiesLayer from(int ordinal) {
                if (ordinal >= 0 && ordinal < VALUES.length) {
                    return VALUES[ordinal];
                }
                throw new UnsupportedOperationException("Detected unknown SerializedAbilitiesLayer ID: " + ordinal);
            }
        }
    }
}