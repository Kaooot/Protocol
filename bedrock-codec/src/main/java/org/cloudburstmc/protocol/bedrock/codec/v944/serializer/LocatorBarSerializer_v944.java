package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import java.awt.Color;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.world.WorldPosition;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.waypoint.LocatorBarWaypointPayload;
import org.cloudburstmc.protocol.bedrock.data.waypoint.ServerWaypointGroupAction;
import org.cloudburstmc.protocol.bedrock.data.waypoint.ServerWaypointPayload;
import org.cloudburstmc.protocol.bedrock.data.waypoint.VanillaWaypointManagerConstants;
import org.cloudburstmc.protocol.bedrock.data.waypoint.WaypointGroupWaypointHandle;
import org.cloudburstmc.protocol.bedrock.packet.LocatorBarPacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocatorBarSerializer_v944 implements BedrockPacketSerializer<LocatorBarPacket> {
    public static final LocatorBarSerializer_v944 INSTANCE = new LocatorBarSerializer_v944();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LocatorBarPacket packet) {
        helper.writeArray(buffer, packet.getWaypoints(), this::writeLocatorBarWaypointPayload);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LocatorBarPacket packet) {
        helper.readArray(buffer, packet.getWaypoints(), this::readLocatorBarWaypointPayload, 40000);
    }

    protected void writeLocatorBarWaypointPayload(ByteBuf buffer, BedrockCodecHelper helper, LocatorBarWaypointPayload payload) {
        helper.writeUuid(buffer, payload.getGroupHandle().getUuid());
        this.writeServerWaypointPayload(buffer, helper, payload.getServerWaypointPayload());
        VarInts.writeUnsignedInt(buffer, payload.getActionFlag().ordinal());
    }

    protected LocatorBarWaypointPayload readLocatorBarWaypointPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final LocatorBarWaypointPayload payload = new LocatorBarWaypointPayload();
        final WaypointGroupWaypointHandle groupHandle = new WaypointGroupWaypointHandle();
        groupHandle.setUuid(helper.readUuid(buffer));
        payload.setGroupHandle(groupHandle);
        payload.setServerWaypointPayload(this.readServerWaypointPayload(buffer, helper));
        payload.setActionFlag(ServerWaypointGroupAction.from(VarInts.readUnsignedInt(buffer)));
        return payload;
    }

    protected void writeServerWaypointPayload(ByteBuf buffer, BedrockCodecHelper helper, ServerWaypointPayload payload) {
        buffer.writeIntLE(payload.getUpdateFlag());
        helper.writeOptional(buffer, OptionalBoolean::isPresent, payload.getIsVisible(),
                (buf, aHelper, isVisible) -> buf.writeBoolean(isVisible.getAsBoolean()));
        helper.writeOptionalNull(buffer, payload.getWorldPosition(), this::writeWorldPosition);
        helper.writeOptionalNull(buffer, payload.getTextureId(), (buf, imageType) -> buf.writeIntLE(imageType.ordinal()));
        helper.writeOptionalNull(buffer, payload.getColor(), (buf, color) -> buf.writeIntLE(color.getRGB()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, payload.getClientPositionAuthority(),
                (buf, aHelper, clientPositionAuthority) -> buf.writeBoolean(clientPositionAuthority.getAsBoolean()));
        helper.writeOptionalNull(buffer, payload.getActorUniqueID(), VarInts::writeLong);
    }

    protected ServerWaypointPayload readServerWaypointPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ServerWaypointPayload payload = new ServerWaypointPayload();
        payload.setUpdateFlag(buffer.readIntLE());
        payload.setIsVisible(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        payload.setWorldPosition(helper.readOptional(buffer, null, this::readWorldPosition));
        payload.setTextureId(helper.readOptional(buffer, null, buf -> VanillaWaypointManagerConstants.ImageType.from(buf.readIntLE())));
        payload.setColor(helper.readOptional(buffer, null, buf -> new Color(buf.readIntLE(), true)));
        payload.setClientPositionAuthority(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        payload.setActorUniqueID(helper.readOptional(buffer, null, VarInts::readLong));
        return payload;
    }

    protected void writeWorldPosition(ByteBuf buffer, BedrockCodecHelper helper, WorldPosition position) {
        helper.writeVector3f(buffer, position.getPosition());
        VarInts.writeInt(buffer, position.getDimensionType().getValue());
    }

    protected WorldPosition readWorldPosition(ByteBuf buffer, BedrockCodecHelper helper) {
        final Vector3f position = helper.readVector3f(buffer);
        final DimensionType dimensionType = DimensionType.from(VarInts.readInt(buffer));
        return new WorldPosition(position, dimensionType);
    }
}
