package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.LocatorBarWaypointPayload;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class LocatorBarPacket implements BedrockPacket {

    private final List<LocatorBarWaypointPayload> waypoints = new ObjectArrayList<>();

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LOCATOR_BAR;
    }

    @Override
    public LocatorBarPacket clone() {
        try {
            return (LocatorBarPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}