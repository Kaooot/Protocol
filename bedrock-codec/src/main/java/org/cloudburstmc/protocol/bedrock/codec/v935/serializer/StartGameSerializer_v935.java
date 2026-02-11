package org.cloudburstmc.protocol.bedrock.codec.v935.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.StartGameSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.gathering.GatheringJoinInfo;
import org.cloudburstmc.protocol.bedrock.data.gathering.PresenceInfo;
import org.cloudburstmc.protocol.bedrock.data.gathering.ServerJoinInfo;
import org.cloudburstmc.protocol.bedrock.data.gathering.StoreEntryPointInfo;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v935 extends StartGameSerializer_v924 {
    public static final StartGameSerializer_v935 INSTANCE = new StartGameSerializer_v935();

    @Override
    protected void writeServerJoinInfo(ByteBuf buffer, BedrockCodecHelper helper, ServerJoinInfo joinInfo) {
        super.writeServerJoinInfo(buffer, helper, joinInfo);
        helper.writeOptionalNull(buffer, joinInfo.getStoreEntryPointInfo(), this::writeStoreEntryPointInfo);
        helper.writeOptionalNull(buffer, joinInfo.getPresenceInfo(), this::writePresenceInfo);
    }

    @Override
    protected ServerJoinInfo readServerJoinInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final ServerJoinInfo joinInfo = new ServerJoinInfo();
        joinInfo.setGatheringJoinInfo(helper.readOptional(buffer, null, this::readGatheringJoinInfo));
        joinInfo.setStoreEntryPointInfo(helper.readOptional(buffer, null, this::readStoreEntryPointInfo));
        joinInfo.setPresenceInfo(helper.readOptional(buffer, null, this::readPresenceInfo));
        return joinInfo;
    }

    @Override
    protected void writeGatheringJoinInfo(ByteBuf buffer, BedrockCodecHelper helper, GatheringJoinInfo info) {
        helper.writeUuid(buffer, info.getExperienceID());
        helper.writeString(buffer, info.getExperienceName());
        helper.writeUuid(buffer, info.getExperienceWorldID());
        helper.writeString(buffer, info.getExperienceWorldName());
        helper.writeString(buffer, info.getCreatorID());
        helper.writeUuid(buffer, info.getUnk());
        helper.writeUuid(buffer, info.getUnk1());
        helper.writeString(buffer, info.getServerID());
    }

    @Override
    protected GatheringJoinInfo readGatheringJoinInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final GatheringJoinInfo info = new GatheringJoinInfo();
        info.setExperienceID(helper.readUuid(buffer));
        info.setExperienceName(helper.readString(buffer));
        info.setExperienceWorldID(helper.readUuid(buffer));
        info.setExperienceWorldName(helper.readString(buffer));
        info.setCreatorID(helper.readString(buffer));
        info.setUnk(helper.readUuid(buffer));
        info.setUnk1(helper.readUuid(buffer));
        info.setServerID(helper.readString(buffer));
        return info;
    }

    protected void writeStoreEntryPointInfo(ByteBuf buffer, BedrockCodecHelper helper, StoreEntryPointInfo info) {
        helper.writeString(buffer, info.getStoreID());
        helper.writeString(buffer, info.getStoreName());
    }

    protected StoreEntryPointInfo readStoreEntryPointInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final StoreEntryPointInfo info = new StoreEntryPointInfo();
        info.setStoreID(helper.readString(buffer));
        info.setStoreName(helper.readString(buffer));
        return info;
    }

    protected void writePresenceInfo(ByteBuf buffer, BedrockCodecHelper helper, PresenceInfo info) {
        helper.writeString(buffer, info.getExperienceName());
        helper.writeString(buffer, info.getWorldName());
    }

    protected PresenceInfo readPresenceInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final PresenceInfo info = new PresenceInfo();
        info.setExperienceName(helper.readString(buffer));
        info.setWorldName(helper.readString(buffer));
        return info;
    }
}