package org.cloudburstmc.protocol.bedrock.codec.v974.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.LocatorBarSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.payload.locatorbar.ServerWaypointPayload;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocatorBarSerializer_v974 extends LocatorBarSerializer_v944 {
    public static final LocatorBarSerializer_v974 INSTANCE = new LocatorBarSerializer_v974();

    @Override
    protected void writeServerWaypointPayload(ByteBuf buffer, BedrockCodecHelper helper, ServerWaypointPayload payload) {
        this.writeUpdateFlags(buffer, helper, payload.getUpdateFlags());
        helper.writeOptional(buffer, OptionalBoolean::isPresent, payload.getIsVisible(),
                (buf, aHelper, isVisible) -> buf.writeBoolean(isVisible.getAsBoolean()));
        helper.writeOptionalNull(buffer, payload.getWorldPosition(), this::writeWorldPosition);
        helper.writeOptionalNull(buffer, payload.getTexturePath(), helper::writeString);
        helper.writeOptionalNull(buffer, payload.getIconSize(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, payload.getColor(), ByteBuf::writeIntLE);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, payload.getClientPositionAuthority(),
                (buf, aHelper, clientPositionAuthority) -> buf.writeBoolean(clientPositionAuthority.getAsBoolean()));
        helper.writeOptionalNull(buffer, payload.getActorUniqueID(), VarInts::writeLong);
    }

    @Override
    protected ServerWaypointPayload readServerWaypointPayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ServerWaypointPayload payload = new ServerWaypointPayload();
        this.readUpdateFlags(buffer, helper, payload.getUpdateFlags());
        payload.setIsVisible(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        payload.setWorldPosition(helper.readOptional(buffer, null, this::readWorldPosition));
        payload.setTexturePath(helper.readOptional(buffer, null, helper::readString));
        payload.setIconSize(helper.readOptional(buffer, null, helper::readVector2f));
        payload.setColor(helper.readOptional(buffer, null, ByteBuf::readIntLE));
        payload.setClientPositionAuthority(helper.readOptional(buffer, OptionalBoolean.empty(), buf -> OptionalBoolean.of(buf.readBoolean())));
        payload.setActorUniqueID(helper.readOptional(buffer, null, VarInts::readLong));
        return payload;
    }
}