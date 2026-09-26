package org.cloudburstmc.protocol.bedrock.codec.compat;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BaseBedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.data.ability.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.data.structure.StructureSettings;
import org.cloudburstmc.protocol.bedrock.data.world.GameRule;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.TypeMap;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class NoopBedrockCodecHelper extends BaseBedrockCodecHelper {
    public static final NoopBedrockCodecHelper INSTANCE = new NoopBedrockCodecHelper();

    private NoopBedrockCodecHelper() {
        super(ActorDataTypeMap.builder().build(), TypeMap.empty("GameRule"));
    }

    @Override
    public ActorLink readActorLink(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeActorLink(ByteBuf buffer, ActorLink actorLink) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemData readNetItem(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNetItem(ByteBuf buffer, ItemData item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemData readItem(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeItem(ByteBuf buffer, ItemData item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemData readNetworkItemStackDescriptor(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNetworkItemStackDescriptor(ByteBuf buffer, ItemData item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemData readItemInstance(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeItemInstance(ByteBuf buffer, ItemData item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeCommandOriginData(ByteBuf buffer, CommandOriginData commandOrigin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CommandOriginData readCommandOriginData(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameRule readGameRule(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeGameRule(ByteBuf buffer, GameRule gameRule) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameRule readGameRuleInStartGame(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeGameRuleInStartGame(ByteBuf buffer, GameRule gameRule) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void readEntityData(ByteBuf buffer, ActorDataMap entityData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeEntityData(ByteBuf buffer, ActorDataMap entityData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CommandEnumData readCommandEnum(ByteBuf buffer, boolean soft) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeCommandEnum(ByteBuf buffer, CommandEnumData commandEnum) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StructureSettings readStructureSettings(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeStructureSettings(ByteBuf buffer, StructureSettings settings) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SerializedSkin readSkin(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <O> O readOptional(ByteBuf buffer, O emptyValue, Function<ByteBuf, O> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T readOptional(ByteBuf buffer, T emptyValue, BiFunction<ByteBuf, BedrockCodecHelper, T> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void writeOptional(ByteBuf buffer, Predicate<T> isPresent, T object, BiConsumer<ByteBuf, T> consumer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void writeOptional(ByteBuf buffer, Predicate<T> isPresent, T object, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void writeOptionalNull(ByteBuf buffer, T object, BiConsumer<ByteBuf, T> consumer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void writeOptionalNull(ByteBuf buffer, T object, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeSerializedAbilitiesData(ByteBuf buffer, SerializedAbilitiesData data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SerializedAbilitiesData readSerializedAbilitiesData(ByteBuf buffer) {
        throw new UnsupportedOperationException();
    }
}
