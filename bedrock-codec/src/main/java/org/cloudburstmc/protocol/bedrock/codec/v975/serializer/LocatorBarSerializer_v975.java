package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import java.awt.Color;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.LocatorBarSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.waypoint.ServerWaypointPayload;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocatorBarSerializer_v975 extends LocatorBarSerializer_v944 {
    public static final LocatorBarSerializer_v975 INSTANCE = new LocatorBarSerializer_v975();

    @Override
    protected void writeServerWaypointPayload(ByteBuf buffer, BedrockCodecHelper helper, ServerWaypointPayload payload) {
        buffer.writeIntLE(payload.getUpdateFlag());
        helper.writeOptional(buffer, OptionalBoolean::isPresent, payload.getIsVisible(),
                (buf, aHelper, isVisible) -> buf.writeBoolean(isVisible.getAsBoolean()));
        helper.writeOptionalNull(buffer, payload.getWorldPosition(), this::writeWorldPosition);
        helper.writeOptionalNull(buffer, payload.getTexturePath(), helper::writeString);
        helper.writeOptionalNull(buffer, payload.getIconSize(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, payload.getColor(), (buf, color) -> buf.writeIntLE(color.getRGB()));
        helper.writeOptional(buffer, OptionalBoolean::isPresent, payload.getClientPositionAuthority(),
                (buf, aHelper, clientPositionAuthority) -> buf.writeBoolean(clientPositionAuthority.getAsBoolean()));
        helper.writeOptionalNull(buffer, payload.getActorUniqueID(), VarInts::writeLong);
    }

    @Override
    protected ServerWaypointPayload readServerWaypointPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ServerWaypointPayload payload = new ServerWaypointPayload();
        payload.setUpdateFlag(buffer.readIntLE());
        payload.setIsVisible(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        payload.setWorldPosition(helper.readOptional(buffer, null, this::readWorldPosition));
        payload.setTexturePath(helper.readOptional(buffer, null, helper::readString));
        payload.setIconSize(helper.readOptional(buffer, null, helper::readVector2f));
        payload.setColor(helper.readOptional(buffer, null, buf -> new Color(buf.readIntLE(), true)));
        payload.setClientPositionAuthority(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        payload.setActorUniqueID(helper.readOptional(buffer, null, VarInts::readLong));
        return payload;
    }
}
