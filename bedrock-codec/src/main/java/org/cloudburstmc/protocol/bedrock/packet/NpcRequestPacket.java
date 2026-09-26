package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class NpcRequestPacket implements BedrockPacket {

    private long npcRuntimeID;
    private RequestType requestType;
    private String actions;
    private int actionIndex;
    private String sceneName;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.NPC_REQUEST;
    }

    @Override
    public NpcRequestPacket clone() {
        try {
            return (NpcRequestPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum RequestType {
        SET_ACTIONS,
        EXECUTE_ACTION,
        EXECUTE_CLOSING_COMMANDS,
        SET_NAME,
        SET_SKIN,
        SET_INTERACT_TEXT,
        EXECUTE_OPENING_COMMANDS;

        private static final RequestType[] VALUES = values();

        public static RequestType from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown NpcRequestPacketPayload::RequestType ID: " + ordinal);
        }
    }
}