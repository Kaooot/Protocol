package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
@ToString(doNotUseGetters = true)
public class ServerboundPackSettingChangePacket implements BedrockPacket {
    private UUID packId;
    private String packSettingName;
    private Type packSettingDataType;
    private Object packSettingValue;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_PACK_SETTING_CHANGE;
    }

    @Override
    public BedrockPacket clone() {
        try {
            return (ServerboundPackSettingChangePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum Type {
        FLOAT,
        BOOLEAN,
        STRING;

        private static final Type[] VALUES = values();

        public static Type from(int ordinal) {
            if (ordinal < 0 || ordinal >= VALUES.length) {
                throw new IllegalStateException("Detected unknown ServerboundPackSettingChangePacket.Type ID: " + ordinal);
            }
            return VALUES[ordinal];
        }
    }
}