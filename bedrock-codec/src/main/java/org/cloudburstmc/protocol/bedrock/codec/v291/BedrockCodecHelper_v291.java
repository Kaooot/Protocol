package org.cloudburstmc.protocol.bedrock.codec.v291;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.codec.BaseBedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.data.ActorLinkType;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumConstraint;
import org.cloudburstmc.protocol.bedrock.data.command.CommandEnumData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.payload.common.RedactableString;
import org.cloudburstmc.protocol.bedrock.transformer.ActorDataTransformer;
import org.cloudburstmc.protocol.common.util.TriConsumer;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.common.util.stream.LittleEndianByteBufOutputStream;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

public class BedrockCodecHelper_v291 extends BaseBedrockCodecHelper {

    public BedrockCodecHelper_v291(ActorDataTypeMap actorData, TypeMap<Class<?>> gameRulesTypes) {
        super(actorData, gameRulesTypes);
    }

    @Override
    public ActorLink readActorLink(ByteBuf buffer) {
        long targetA = VarInts.readLong(buffer);
        long targetB = VarInts.readLong(buffer);
        int type = buffer.readUnsignedByte();
        boolean immediate = buffer.readBoolean();

        return new ActorLink(targetA, targetB, ActorLinkType.from(type), immediate);
    }

    @Override
    public void writeActorLink(ByteBuf buffer, ActorLink actorLink) {
        checkNotNull(actorLink, "actorLink");

        VarInts.writeLong(buffer, actorLink.getTargetA());
        VarInts.writeLong(buffer, actorLink.getTargetB());
        buffer.writeByte(actorLink.getType().ordinal());
        buffer.writeBoolean(actorLink.isImmediate());
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
        int runtimeId = VarInts.readInt(buffer);
        if (runtimeId == 0 || runtimeId == -1) {
            // We don't need to read anything extra.
            return ItemData.AIR;
        }
        ItemDefinition definition = this.itemDefinitions.getDefinition(runtimeId);
        int aux = VarInts.readInt(buffer);
        int damage = (short) (aux >> 8);
        if (damage == Short.MAX_VALUE) damage = -1;
        int count = aux & 0xff;
        short nbtSize = buffer.readShortLE();

        NbtMap compoundTag = null;
        if (nbtSize > 0) {
            try (NBTInputStream reader = NbtUtils.createReaderLE(new ByteBufInputStream(buffer.readSlice(nbtSize)), this.encodingSettings.maxItemNBTSize())) {
                Object tag = reader.readTag();
                if (tag instanceof NbtMap) {
                    compoundTag = (NbtMap) tag;
                }
            } catch (IOException e) {
                throw new IllegalStateException("Unable to load NBT data", e);
            }
        }

        String[] canPlace = readArray(buffer, new String[0], this::readString);
        String[] canBreak = readArray(buffer, new String[0], this::readString);

        return ItemData.builder()
                .definition(definition)
                .damage(damage)
                .count(count)
                .tag(compoundTag)
                .canPlace(canPlace)
                .canBreak(canBreak)
                .build();
    }

    @Override
    public void writeItem(ByteBuf buffer, ItemData item) {
        checkNotNull(item, "item");

        // Write id
        ItemDefinition definition = item.getDefinition();
        if (isAir(definition)) {
            // We don't need to write anything extra.
            buffer.writeByte(0);
            return;
        }
        VarInts.writeInt(buffer, definition.getRuntimeId());

        // Write damage and count
        int damage = item.getDamage();
        if (damage == -1) damage = Short.MAX_VALUE;
        VarInts.writeInt(buffer, (damage << 8) | (item.getCount() & 0xff));

        // Remember this position, since we'll be writing the true NBT size here later:
        int sizeIndex = buffer.writerIndex();
        buffer.writeShortLE(0);

        if (item.getTag() != null) {
            int afterSizeIndex = buffer.writerIndex();
            try (NBTOutputStream stream = new NBTOutputStream(new LittleEndianByteBufOutputStream(buffer))) {
                stream.writeTag(item.getTag());
            } catch (IOException e) {
                // This shouldn't happen (as this is backed by a Netty ByteBuf), but okay...
                throw new IllegalStateException("Unable to save NBT data", e);
            }
            // Set to the written NBT size
            buffer.setShortLE(sizeIndex, buffer.writerIndex() - afterSizeIndex);
        }

        writeArray(buffer, item.getCanPlace(), this::writeString);
        writeArray(buffer, item.getCanBreak(), this::writeString);
    }

    @Override
    public ItemData readItemInstance(ByteBuf buffer) {
        return readItem(buffer);
    }

    @Override
    public void writeItemInstance(ByteBuf buffer, ItemData item) {
        writeItem(buffer, item);
    }

    @Override
    public CommandOriginData readCommandOrigin(ByteBuf buffer) {
        CommandOriginType origin = CommandOriginType.values()[VarInts.readUnsignedInt(buffer)];
        UUID uuid = readUuid(buffer);
        String requestId = readString(buffer);
        long varLong = -1;
        if (origin == CommandOriginType.DEV_CONSOLE || origin == CommandOriginType.TEST) {
            varLong = VarInts.readLong(buffer);
        }
        return new CommandOriginData(origin, uuid, requestId, varLong);
    }

    @Override
    public void writeCommandOrigin(ByteBuf buffer, CommandOriginData originData) {
        checkNotNull(originData, "commandOriginData");
        VarInts.writeUnsignedInt(buffer, originData.getCommandType().ordinal());
        writeUuid(buffer, originData.getCommandUUID());
        writeString(buffer, originData.getRequestID());
        if (originData.getCommandType() == CommandOriginType.DEV_CONSOLE || originData.getCommandType() == CommandOriginType.TEST) {
            VarInts.writeLong(buffer, originData.getPlayerID());
        }
    }

    @Override
    public GameRuleData<?> readGameRule(ByteBuf buffer) {

        String name = readString(buffer);
        int type = VarInts.readUnsignedInt(buffer);

        switch (type) {
            case 1:
                return new GameRuleData<>(name, buffer.readBoolean());
            case 2:
                return new GameRuleData<>(name, VarInts.readUnsignedInt(buffer));
            case 3:
                return new GameRuleData<>(name, buffer.readFloatLE());
        }
        throw new IllegalStateException("Invalid gamerule type received");
    }

    @Override
    public void writeGameRule(ByteBuf buffer, GameRuleData<?> gameRule) {
        checkNotNull(gameRule, "gameRule");

        Object value = gameRule.getValue();
        int type = this.gameRuleType.getId(value.getClass());

        writeString(buffer, gameRule.getName());
        VarInts.writeUnsignedInt(buffer, type);
        switch (type) {
            case 1:
                buffer.writeBoolean((boolean) value);
                break;
            case 2:
                VarInts.writeUnsignedInt(buffer, (int) value);
                break;
            case 3:
                buffer.writeFloatLE((float) value);
                break;
        }
    }

    @Override
    public void readActorData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "actorDataDictionary");

        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(this.encodingSettings.maxListSize() <= 0 || length <= this.encodingSettings.maxListSize(), "Actor data size is too big: %s", length);

        for (int i = 0; i < length; i++) {
            int id = VarInts.readUnsignedInt(buffer);
            ActorDataFormat format = ActorDataFormat.values()[VarInts.readUnsignedInt(buffer)];

            Object value;
            switch (format) {
                case BYTE:
                    value = buffer.readByte();
                    break;
                case SHORT:
                    value = buffer.readShortLE();
                    break;
                case INT:
                    value = VarInts.readInt(buffer);
                    break;
                case FLOAT:
                    value = buffer.readFloatLE();
                    break;
                case STRING:
                    value = readString(buffer);
                    break;
                case NBT:
                    value = this.readItem(buffer).getTag();
                    break;
                case VECTOR3I:
                    value = readVector3i(buffer);
                    break;
                case LONG:
                    value = VarInts.readLong(buffer);
                    break;
                case VECTOR3F:
                    value = readVector3f(buffer);
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown actor data type received");
            }

            ActorDataTypeMap.Definition<?>[] definitions = this.actorData.fromId(id, format);
            if (definitions != null) {
                for (ActorDataTypeMap.Definition<?> definition : definitions) {
                    //noinspection unchecked
                    ActorDataTransformer<Object, ?> transformer = (ActorDataTransformer<Object, ?>) definition.getTransformer();
                    Object transformedValue = transformer.deserialize(this, actorDataMap, value);
                    if (transformedValue != null) {
                        actorDataMap.put(definition.getType(), transformer.deserialize(this, actorDataMap, value));
                    }
                }
            } else {
                log.debug("Unknown actor data: {} type {} value {}", id, format, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void writeActorData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "actorDataDictionary");

        VarInts.writeUnsignedInt(buffer, actorDataMap.size());

        for (Map.Entry<ActorDataType<?>, Object> entry : actorDataMap.entrySet()) {
            ActorDataTypeMap.Definition<?> definition = this.actorData.fromType(entry.getKey());

            VarInts.writeUnsignedInt(buffer, definition.getId());
            VarInts.writeUnsignedInt(buffer, definition.getFormat().ordinal());

            try {
                Object value = ((ActorDataTransformer<?, Object>) definition.getTransformer())
                        .serialize(this, actorDataMap, entry.getValue());

                switch (definition.getFormat()) {
                    case BYTE:
                        buffer.writeByte((byte) value);
                        break;
                    case SHORT:
                        buffer.writeShortLE((short) value);
                        break;
                    case INT:
                        VarInts.writeInt(buffer, (int) value);
                        break;
                    case FLOAT:
                        buffer.writeFloatLE((float) value);
                        break;
                    case STRING:
                        writeString(buffer, (String) value);
                        break;
                    case NBT:
                        this.writeItem(buffer, ItemData.builder()
                                .definition(ItemDefinition.LEGACY_FIREWORK)
                                .damage(0)
                                .count(1)
                                .tag((NbtMap) value)
                                .build());
                        break;
                    case VECTOR3I:
                        writeVector3i(buffer, (Vector3i) value);
                        break;
                    case LONG:
                        VarInts.writeLong(buffer, (long) value);
                        break;
                    case VECTOR3F:
                        writeVector3f(buffer, (Vector3f) value);
                        break;
                    default:
                        throw new UnsupportedOperationException("Unknown actor data type " + definition.getFormat());
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to encode ActorData " + definition.getId() + " of " + definition.getType().getTypeName(), e);
            }
        }
    }

    @Override
    public CommandEnumData readCommandEnum(ByteBuf buffer, boolean soft) {

        String name = readString(buffer);

        int count = VarInts.readUnsignedInt(buffer);
        LinkedHashMap<String, Set<CommandEnumConstraint>> values = new LinkedHashMap<>();
        for (int i = 0; i < count; i++) {
            values.put(readString(buffer), Collections.emptySet());
        }
        return new CommandEnumData(name, values, soft);
    }

    @Override
    public void writeCommandEnum(ByteBuf buffer, CommandEnumData enumData) {
        checkNotNull(enumData, "enumData");

        writeString(buffer, enumData.getName());

        Set<String> values = enumData.getValues().keySet();
        VarInts.writeUnsignedInt(buffer, values.size());
        for (String value : values) {
            writeString(buffer, value);
        }
    }

    @Override
    public <O> O readOptional(ByteBuf buffer, O emptyValue, Function<ByteBuf, O> function) {
        if (buffer.readBoolean()) {
            return function.apply(buffer);
        }
        return emptyValue;
    }

    @Override
    public <O> O readOptional(ByteBuf buffer, O emptyValue, BiFunction<ByteBuf, BedrockCodecHelper, O> function) {
        if (buffer.readBoolean()) {
            return function.apply(buffer, this);
        }
        return emptyValue;
    }

    @Override
    public <T> void writeOptional(ByteBuf buffer, Predicate<T> isPresent, T object, BiConsumer<ByteBuf, T> consumer) {
        checkNotNull(consumer, "read consumer");
        boolean exists = isPresent.test(object);
        buffer.writeBoolean(exists);
        if (exists) {
            consumer.accept(buffer, object);
        }
    }

    @Override
    public <T> void writeOptional(ByteBuf buffer, Predicate<T> isPresent, T object, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
        checkNotNull(consumer, "read consumer");
        boolean exists = isPresent.test(object);
        buffer.writeBoolean(exists);
        if (exists) {
            consumer.accept(buffer, this, object);
        }
    }

    @Override
    public <T> void writeOptionalNull(ByteBuf buffer, T object, BiConsumer<ByteBuf, T> consumer) {
        this.writeOptional(buffer, Objects::nonNull, object, consumer);
    }

    @Override
    public <T> void writeOptionalNull(ByteBuf buffer, T object, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
        this.writeOptional(buffer, Objects::nonNull, object, consumer);
    }

    @Override
    public void writeRedactableString(ByteBuf buffer, RedactableString string) {
        this.writeString(buffer, string.getUnredacted());
    }

    @Override
    public RedactableString readRedactableString(ByteBuf buffer) {
        final RedactableString string = new RedactableString();
        string.setUnredacted(this.readString(buffer));
        return string;
    }
}
