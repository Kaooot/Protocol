package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapDecoration;
import org.cloudburstmc.protocol.bedrock.data.payload.map.MapItemTrackedActorUniqueId;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundMapItemDataPacket implements BedrockPacket {

    private long mapID;
    private DimensionType dimension;
    private boolean isLocked;
    private Vector3i mapOrigin;
    private LongList creationMapIDs;
    private int scale;
    private List<MapItemTrackedActorUniqueId> trackedActorIDs;
    private List<MapDecoration> decorations;
    private Integer width;
    private Integer height;
    private Integer startX;
    private Integer startY;
    private IntList pixels;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_MAP_ITEM_DATA;
    }

    @Override
    public ClientboundMapItemDataPacket clone() {
        try {
            return (ClientboundMapItemDataPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}