package org.cloudburstmc.protocol.bedrock.codec.v2168;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v1001.BedrockCodecHelper_v1001;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataType;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.common.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.RecipeNetId;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.ExperimentToggle;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.Experiments;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackNetId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackRequestId;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.*;
import org.cloudburstmc.protocol.bedrock.transformer.ActorDataTransformer;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;
import org.cloudburstmc.protocol.common.util.stream.LittleEndianByteBufInputStream;
import org.cloudburstmc.protocol.common.util.stream.LittleEndianByteBufOutputStream;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v2168 extends BedrockCodecHelper_v1001 {

    protected final TypeMap<ItemStackRequestActionType> legacyItemStackRequestActionTypeMap = TypeMap.builder(ItemStackRequestActionType.class)
            .insert(0, ItemStackRequestActionType.TAKE)
            .insert(1, ItemStackRequestActionType.PLACE)
            .insert(2, ItemStackRequestActionType.SWAP)
            .insert(3, ItemStackRequestActionType.DROP)
            .insert(4, ItemStackRequestActionType.DESTROY)
            .insert(5, ItemStackRequestActionType.CONSUME)
            .insert(6, ItemStackRequestActionType.CREATE)
            .insert(7, ItemStackRequestActionType.PLACE_IN_ITEM_CONTAINER)
            .insert(8, ItemStackRequestActionType.TAKE_FROM_ITEM_CONTAINER)
            .insert(9, ItemStackRequestActionType.SCREEN_LAB_TABLE_COMBINE)
            .insert(10, ItemStackRequestActionType.SCREEN_BEACON_PAYMENT)
            .insert(11, ItemStackRequestActionType.SCREEN_HUD_MINE_BLOCK)
            .insert(12, ItemStackRequestActionType.CRAFT_RECIPE)
            .insert(13, ItemStackRequestActionType.CRAFT_RECIPE_AUTO)
            .insert(14, ItemStackRequestActionType.CRAFT_CREATIVE)
            .insert(15, ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL)
            .insert(16, ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT)
            .insert(17, ItemStackRequestActionType.CRAFT_LOOM)
            .insert(18, ItemStackRequestActionType.CRAFT_NON_IMPLEMENTED)
            .insert(19, ItemStackRequestActionType.CRAFT_RESULTS)
            .build();

    public BedrockCodecHelper_v2168(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writePresenceConfiguration(ByteBuf buffer, PresenceConfiguration configuration) {
        this.writeOptionalNull(buffer, configuration.getRichPresenceId(), this::writeString);
    }

    @Override
    public PresenceConfiguration readPresenceConfiguration(ByteBuf buffer) {
        final PresenceConfiguration configuration = new PresenceConfiguration();
        configuration.setRichPresenceId(this.readOptional(buffer, null, this::readString));
        return configuration;
    }

    protected void writeUserDataBuffer(ByteBuf buffer, UserDataBuffer userDataBuffer, boolean isBlockingId) {
        ByteBuf userDataBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try (LittleEndianByteBufOutputStream stream = new LittleEndianByteBufOutputStream(userDataBuf);
             NBTOutputStream nbtStream = new NBTOutputStream(stream)) {
            if (userDataBuffer.getCompoundTag() != null) {
                stream.writeShort(-1);
                stream.writeByte(1); // Hardcoded in current version
                nbtStream.writeTag(userDataBuffer.getCompoundTag());
            } else {
                userDataBuf.writeShortLE(0);
            }

            String[] canPlace = userDataBuffer.getCanPlace();
            stream.writeInt(canPlace.length);
            for (String aCanPlace : canPlace) {
                stream.writeUTF(aCanPlace);
            }

            String[] canBreak = userDataBuffer.getCanBreak();
            stream.writeInt(canBreak.length);
            for (String aCanBreak : canBreak) {
                stream.writeUTF(aCanBreak);
            }

            if (isBlockingId) {
                stream.writeLong(userDataBuffer.getBlockingTicks());
            }

            VarInts.writeUnsignedInt(buffer, userDataBuf.readableBytes());
            buffer.writeBytes(userDataBuf);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to write item user data", e);
        } finally {
            userDataBuf.release();
        }
    }

    protected UserDataBuffer readUserDataBuffer(ByteBuf buffer, boolean isBlockingId) {
        NbtMap compoundTag = null;
        long blockingTicks = 0;
        String[] canPlace = null;
        String[] canBreak = null;

        final ByteBuf buf = buffer.readSlice(VarInts.readUnsignedInt(buffer));

        if (!buf.isReadable()) {
            return new UserDataBuffer(compoundTag, canPlace, canBreak, blockingTicks);
        }

        try (LittleEndianByteBufInputStream stream = new LittleEndianByteBufInputStream(buf);
             NBTInputStream nbtStream = new NBTInputStream(stream, this.getEncodingSettings().maxItemNBTSize())) {
            int nbtSize = stream.readShort();

            if (nbtSize > 0) {
                compoundTag = (NbtMap) nbtStream.readTag();
            } else if (nbtSize == -1) {
                int tagCount = stream.readUnsignedByte();
                if (tagCount != 1) throw new IllegalArgumentException("Expected 1 tag but got " + tagCount);
                compoundTag = (NbtMap) nbtStream.readTag();
            }

            int maxLength = this.getEncodingSettings().maxListSize();
            int length = stream.readInt();
            checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s can place entries, but maximum is %s", length, maxLength);
            canPlace = new String[length];
            for (int i = 0; i < canPlace.length; i++) {
                canPlace[i] = stream.readUTFMaxLen(this.getEncodingSettings().maxItemStackTagLength());
            }

            length = stream.readInt();
            checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s can break entries, but maximum is %s", length, maxLength);
            canBreak = new String[length];
            for (int i = 0; i < canBreak.length; i++) {
                canBreak[i] = stream.readUTFMaxLen(this.getEncodingSettings().maxItemStackTagLength());
            }

            if (isBlockingId) {
                blockingTicks = stream.readLong();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read item user data", e);
        }

        if (buf.isReadable()) {
            log.info("Item user data has {} readable bytes left", buf.readableBytes());

            if (log.isDebugEnabled()) {
                log.debug("Item data:\n{}", ByteBufUtil.prettyHexDump(buf.readerIndex(0)));
            }
        }
        return new UserDataBuffer(compoundTag, canPlace, canBreak, blockingTicks);
    }

    @Override
    @SuppressWarnings("ALL")
    public void writeNetworkItemStackDescriptor(ByteBuf buffer, ItemData item) {
        requireNonNull(item, "item is null!");

        ItemDefinition definition = item.getDefinition() == null ? ItemDefinition.AIR : item.getDefinition();
        buffer.writeShortLE(definition.getRuntimeId());
        buffer.writeShortLE(item.getCount());
        VarInts.writeUnsignedInt(buffer, item.getDamage());
        buffer.writeBoolean(item.isUsingNetId());
        if (item.isUsingNetId()) {
            VarInts.writeInt(buffer, item.getNetId());
        }
        VarInts.writeUnsignedInt(buffer, item.getBlockDefinition() == null ? 0 : item.getBlockDefinition().getRuntimeId());
        this.writeUserDataBuffer(
                buffer,
                new UserDataBuffer(
                        item.getTag(),
                        item.getCanPlace(),
                        item.getCanBreak(),
                        item.getBlockingTicks()
                ),
                definition != null && definition.getIdentifier().equals(BLOCKING_ID)
        );
    }

    @Override
    public ItemData readNetworkItemStackDescriptor(ByteBuf buffer) {
        int runtimeId = buffer.readShortLE();
        ItemDefinition definition = runtimeId == 0 ? ItemDefinition.AIR : this.getItemDefinitions().getDefinition(runtimeId);
        int count = buffer.readUnsignedShortLE();
        int damage = VarInts.readUnsignedInt(buffer);

        boolean hasNetId = buffer.readBoolean();
        int netId = 0;
        if (hasNetId) {
            netId = VarInts.readInt(buffer);
        }

        int blockRuntimeId = VarInts.readUnsignedInt(buffer);

        final UserDataBuffer userDataBuffer = this.readUserDataBuffer(buffer, definition != null && definition.getIdentifier().equals(BLOCKING_ID));
        return ItemData.builder()
                .definition(definition)
                .damage(damage)
                .count(count)
                .tag(userDataBuffer.getCompoundTag())
                .canPlace(userDataBuffer.getCanPlace())
                .canBreak(userDataBuffer.getCanBreak())
                .blockingTicks(userDataBuffer.getBlockingTicks())
                .blockDefinition(blockRuntimeId == 0 ? ItemData.AIR.getBlockDefinition() : this.getBlockDefinitions().getDefinition(blockRuntimeId))
                .usingNetId(hasNetId)
                .netId(netId)
                .build();
    }

    @Override
    public void writeNetworkItemInstanceDescriptor(ByteBuf buffer, ItemData item) {
        requireNonNull(item, "item is null!");

        ItemDefinition definition = item.getDefinition();

        VarInts.writeInt(buffer, definition.getRuntimeId());
        buffer.writeShortLE(item.getCount());
        VarInts.writeUnsignedInt(buffer, item.getDamage());
        VarInts.writeInt(buffer, item.getBlockDefinition() == null ? 0 : item.getBlockDefinition().getRuntimeId());
        this.writeUserDataBuffer(
                buffer,
                new UserDataBuffer(
                        item.getTag(),
                        item.getCanPlace(),
                        item.getCanBreak(),
                        item.getBlockingTicks()
                ),
                definition != null && definition.getIdentifier().equals(BLOCKING_ID)
        );
    }

    @Override
    public ItemData readNetworkItemInstanceDescriptor(ByteBuf buffer) {
        int runtimeId = VarInts.readInt(buffer);
        ItemDefinition definition = runtimeId == 0 ? ItemDefinition.AIR : this.getItemDefinitions().getDefinition(runtimeId);
        int count = buffer.readUnsignedShortLE();
        int damage = VarInts.readUnsignedInt(buffer);
        int blockRuntimeId = VarInts.readInt(buffer);
        final UserDataBuffer userDataBuffer = this.readUserDataBuffer(buffer, definition != null && definition.getIdentifier().equals(BLOCKING_ID));
        return ItemData.builder()
                .definition(definition)
                .damage(damage)
                .count(count)
                .tag(userDataBuffer.getCompoundTag())
                .canPlace(userDataBuffer.getCanPlace())
                .canBreak(userDataBuffer.getCanBreak())
                .blockingTicks(userDataBuffer.getBlockingTicks())
                .blockDefinition(blockRuntimeId == 0 ? ItemData.AIR.getBlockDefinition() : this.getBlockDefinitions().getDefinition(blockRuntimeId))
                .build();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void writeActorData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "actorDataDictionary");

        VarInts.writeUnsignedInt(buffer, actorDataMap.size());

        for (Map.Entry<ActorDataType<?>, Object> entry : actorDataMap.entrySet()) {
            ActorDataTypeMap.Definition<?> definition = this.actorData.fromType(entry.getKey());

            if (definition == null) {
                throw new NullPointerException("Failed to get definition for Actor Data Type: " + entry.getKey());
            }

            VarInts.writeUnsignedInt(buffer, definition.getId());
            VarInts.writeUnsignedInt(buffer, definition.getFormat().ordinal());
            buffer.writeByte(definition.getFormat().ordinal());

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
                        this.writeString(buffer, (String) value);
                        break;
                    case NBT:
                        this.writeTag(buffer, value);
                        break;
                    case VECTOR3I:
                        this.writeVector3i(buffer, (Vector3i) value);
                        break;
                    case LONG:
                        VarInts.writeLong(buffer, (long) value);
                        break;
                    case VECTOR3F:
                        this.writeVector3f(buffer, (Vector3f) value);
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
    public void readActorData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "actorDataDictionary");

        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(this.getEncodingSettings().maxListSize() <= 0 || length <= this.getEncodingSettings().maxListSize(), "Entity data size is too big: %s", length);

        for (int i = 0; i < length; i++) {
            int id = VarInts.readUnsignedInt(buffer);
            int formatId = VarInts.readUnsignedInt(buffer);
            ActorDataFormat format = ActorDataFormat.values()[formatId];
            buffer.readUnsignedByte(); // Type
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
                    value = this.readString(buffer);
                    break;
                case NBT:
                    value = this.readTag(buffer, Object.class);
                    break;
                case VECTOR3I:
                    value = this.readVector3i(buffer);
                    break;
                case LONG:
                    value = VarInts.readLong(buffer);
                    break;
                case VECTOR3F:
                    value = this.readVector3f(buffer);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown actor data type received");
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

    @Override
    public void writeRedactableString(ByteBuf buffer, RedactableString string) {
        this.writeString(buffer, string.getUnredacted());
        this.writeOptionalNull(buffer, string.getRedacted(), this::writeString);
    }

    @Override
    public RedactableString readRedactableString(ByteBuf buffer) {
        final RedactableString string = new RedactableString();
        string.setUnredacted(this.readString(buffer));
        string.setRedacted(this.readOptional(buffer, null, this::readString));
        return string;
    }

    @Override
    protected void writeItemEntry(ByteBuf buffer, ItemStackResponseSlotInfo itemEntry) {
        buffer.writeByte(itemEntry.getRequestedSlot());
        buffer.writeByte(itemEntry.getSlot());
        buffer.writeByte(itemEntry.getAmount());
        this.writeOptionalNull(buffer, itemEntry.getItemStackNetId(),
                (buf, helper, itemStackNetId) -> {
                    buf.writeBoolean(true);
                    VarInts.writeInt(buf, itemStackNetId.getID());
                });
        this.writeRedactableString(buffer, itemEntry.getCustomName());
        VarInts.writeInt(buffer, itemEntry.getDurabilityCorrection());
    }

    @Override
    protected ItemStackResponseSlotInfo readItemEntry(ByteBuf buffer) {
        int slot = buffer.readUnsignedByte();
        int requestedSlot = buffer.readUnsignedByte();
        int amount = buffer.readUnsignedByte();
        ItemStackNetId stackNetworkId = this.readOptional(buffer, null,
                (buf, helper) -> {
                    if (buf.readBoolean()) {
                        return new ItemStackNetId(VarInts.readInt(buf));
                    }
                    return null;
                });
        final RedactableString customName = this.readRedactableString(buffer);
        int durabilityCorrection = VarInts.readInt(buffer);
        return new ItemStackResponseSlotInfo(
                slot,
                requestedSlot,
                amount,
                stackNetworkId,
                customName,
                durabilityCorrection
        );
    }

    @Override
    public void writeGameRule(ByteBuf buffer, GameRuleData<?> gameRule) {
        final Object value = gameRule.getValue();
        final int id = this.gameRuleType.getId(value.getClass());

        this.writeString(buffer, gameRule.getName());
        buffer.writeBoolean(gameRule.isEditable());
        VarInts.writeUnsignedInt(buffer, id);

        switch (id) {
            case 1:
                buffer.writeBoolean((boolean) value);
                break;
            case 2:
                buffer.writeIntLE((int) value);
                break;
            case 3:
                buffer.writeFloatLE((float) value);
                break;
        }
    }

    @Override
    public GameRuleData<?> readGameRule(ByteBuf buffer) {
        final String name = this.readString(buffer);
        final boolean editable = buffer.readBoolean();
        final GameRuleData.Type type = GameRuleData.Type.from(VarInts.readUnsignedInt(buffer));

        switch (type) {
            case BOOL:
                return new GameRuleData<>(name, editable, type, buffer.readBoolean());
            case INT:
                return new GameRuleData<>(name, editable, type, buffer.readIntLE());
            case FLOAT:
                return new GameRuleData<>(name, editable, type, buffer.readFloatLE());
        }
        throw new IllegalStateException("Invalid gamerule type received");
    }

    @Override
    public void writeExperiments(ByteBuf buffer, Experiments experiments) {
        this.writeArray(buffer, experiments.getToggles(), ByteBuf::writeIntLE, this::writeExperimentToggle);
        buffer.writeBoolean(experiments.isExperimentsEverToggled());
    }

    @Override
    public Experiments readExperiments(ByteBuf buffer) {
        final Experiments experiments = new Experiments();
        this.readArray(buffer, experiments.getToggles(), ByteBuf::readIntLE, this::readExperimentToggle);
        experiments.setExperimentsEverToggled(buffer.readBoolean());
        return experiments;
    }

    @Override
    public void writeExperimentToggle(ByteBuf buffer, ExperimentToggle toggle) {
        this.writeString(buffer, toggle.getName());
        buffer.writeBoolean(toggle.isEnabled());
    }

    @Override
    public ExperimentToggle readExperimentToggle(ByteBuf buffer) {
        return new ExperimentToggle(this.readString(buffer), buffer.readBoolean());
    }

    @Override
    public void writeIngredient(ByteBuf buffer, RecipeIngredient ingredient) {
        this.writeItemDescriptor(buffer, ingredient.getDescriptor());
        buffer.writeShortLE(ingredient.getStackSize());
    }

    @Override
    public RecipeIngredient readIngredient(ByteBuf buffer) {
        return new RecipeIngredient(
                this.readItemDescriptor(buffer),
                buffer.readShortLE()
        );
    }

    @Override
    protected void writeItemDescriptor(ByteBuf buffer, ItemDescriptor descriptor) {
        VarInts.writeUnsignedInt(buffer, descriptor.getType().ordinal());
        buffer.writeByte(descriptor.getType().ordinal());
        switch (descriptor.getType()) {
            case EMPTY:
                break;
            case NAME:
                this.writeString(buffer, ((NameDescriptor) descriptor).getItemId().getIdentifier());
                VarInts.writeInt(buffer, ((NameDescriptor) descriptor).getAuxValue());
                break;
            case MOLANG:
                this.writeString(buffer, ((MolangDescriptor) descriptor).getTagExpression());
                buffer.writeShortLE(((MolangDescriptor) descriptor).getMolangVersion());
                break;
            case ITEM_TAG:
                this.writeString(buffer, ((ItemTagDescriptor) descriptor).getItemTag());
                break;
        }
    }

    protected ItemDescriptor readItemDescriptor(ByteBuf buffer) {
        final ItemDescriptorType type = ItemDescriptorType.from(VarInts.readUnsignedInt(buffer));
        buffer.readUnsignedByte();
        return this.readItemDescriptor(buffer, type);
    }

    @Override
    protected ItemDescriptor readItemDescriptor(ByteBuf buffer, ItemDescriptorType type) {
        switch (type) {
            case EMPTY:
                return EmptyDescriptor.INSTANCE;
            case NAME:
                return new NameDescriptor(
                        this.itemDefinitions.getDefinition(
                                this.itemDefinitions.getRuntimeIdByName(this.readString(buffer))
                        ),
                        VarInts.readInt(buffer)
                );
            case MOLANG:
                return new MolangDescriptor(this.readString(buffer), buffer.readShortLE());
            case ITEM_TAG:
                return new ItemTagDescriptor(this.readString(buffer));
            default:
                throw new IllegalStateException("Unknown ItemDescriptorType");
        }
    }

    public void writeItemStackRequestNetworkItemInstanceDescriptor(ByteBuf buffer, ItemStackRequestNetworkItemInstanceDescriptor descriptor) {
        final RecipeIngredient ingredient = descriptor.getIngredient();
        this.writeIngredient(buffer, descriptor.getIngredient());
        VarInts.writeUnsignedInt(buffer, descriptor.getBlockRuntimeId());
        this.writeUserDataBuffer(buffer, descriptor.getUserDataBuffer(),
                ingredient.getDescriptor() != null && ingredient.getDescriptor() instanceof NameDescriptor &&
                        BLOCKING_ID.equals(((NameDescriptor) ingredient.getDescriptor()).getItemId().getIdentifier())
        );
    }

    protected ItemStackRequestNetworkItemInstanceDescriptor readItemStackRequestNetworkItemInstanceDescriptor(ByteBuf buffer) {
        final RecipeIngredient ingredient = this.readIngredient(buffer);
        final int blockRuntimeId = VarInts.readUnsignedInt(buffer);
        final UserDataBuffer userDataBuffer = this.readUserDataBuffer(
                buffer,
                ingredient.getDescriptor() != null && ingredient.getDescriptor() instanceof NameDescriptor &&
                        BLOCKING_ID.equals(((NameDescriptor) ingredient.getDescriptor()).getItemId().getIdentifier())
        );
        return new ItemStackRequestNetworkItemInstanceDescriptor(ingredient, blockRuntimeId, userDataBuffer);
    }

    @Override
    public void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request) {
        VarInts.writeInt(buffer, request.getClientRequestId());
        this.writeArray(buffer, request.getActions(), this::writeItemStackRequestAction);
        this.writeArray(buffer, request.getStringsToFilter(), this::writeString);
        buffer.writeIntLE(
                request.getStringsToFilterOrigin().equals(TextProcessingEventOrigin.UNKNOWN) ? -1 :
                        this.textProcessingEventOrigins.getId(request.getStringsToFilterOrigin())
        );
    }

    @Override
    public ItemStackRequest readItemStackRequest(ByteBuf buffer) {
        final ItemStackRequestId clientRequestId = new ItemStackRequestId(VarInts.readInt(buffer));
        final List<ItemStackRequestAction> actions = new ObjectArrayList<>();
        this.readArray(buffer, actions, this::readItemStackRequestAction);
        final List<String> stringsToFilter = new ObjectArrayList<>();
        this.readArray(buffer, stringsToFilter, this::readString);
        final TextProcessingEventOrigin origin = this.textProcessingEventOrigins.getType(buffer.readIntLE());
        return new ItemStackRequest(
                clientRequestId.getID(),
                actions.toArray(new ItemStackRequestAction[0]),
                stringsToFilter.toArray(new String[0]),
                origin
        );
    }

    protected void writeItemStackRequestAction(ByteBuf buffer, ItemStackRequestAction action) {
        final ItemStackRequestActionType type = action.getType();
        VarInts.writeUnsignedInt(buffer, this.stackRequestActionTypes.getId(type));
        buffer.writeByte(this.legacyItemStackRequestActionTypeMap.getId(type));
        switch (type) {
            case TAKE:
                this.writeItemStackRequestTakeAction(buffer, (TakeAction) action);
                break;
            case PLACE:
                this.writeItemStackRequestPlaceAction(buffer, (PlaceAction) action);
                break;
            case SWAP:
                this.writeItemStackRequestSwapAction(buffer, (SwapAction) action);
                break;
            case DROP:
                this.writeItemStackRequestDropAction(buffer, (DropAction) action);
                break;
            case DESTROY:
                this.writeItemStackRequestDestroyAction(buffer, (DestroyAction) action);
                break;
            case CONSUME:
                this.writeItemStackRequestConsumeAction(buffer, (ConsumeAction) action);
                break;
            case CREATE:
                this.writeItemStackRequestCreateAction(buffer, (CreateAction) action);
                break;
            case SCREEN_LAB_TABLE_COMBINE:
                this.writeItemStackRequestLabTableCombineAction(buffer, (LabTableCombineAction) action);
                break;
            case SCREEN_BEACON_PAYMENT:
                this.writeItemStackRequestBeaconPaymentAction(buffer, (BeaconPaymentAction) action);
                break;
            case SCREEN_HUD_MINE_BLOCK:
                this.writeItemStackRequestMineBlockAction(buffer, (MineBlockAction) action);
                break;
            case CRAFT_RECIPE:
                this.writeItemStackRequestCraftRecipeAction(buffer, (CraftRecipeAction) action);
                break;
            case CRAFT_RECIPE_AUTO:
                this.writeItemStackRequestCraftRecipeAutoAction(buffer, (AutoCraftRecipeAction) action);
                break;
            case CRAFT_CREATIVE:
                this.writeItemStackRequestCraftCreativeAction(buffer, (CraftCreativeAction) action);
                break;
            case CRAFT_RECIPE_OPTIONAL:
                this.writeItemStackRequestCraftRecipeOptionalAction(buffer, (CraftRecipeOptionalAction) action);
                break;
            case CRAFT_REPAIR_AND_DISENCHANT:
                this.writeItemStackRequestCraftRepairAndDisenchantAction(buffer, (CraftGrindstoneAction) action);
                break;
            case CRAFT_LOOM:
                this.writeItemStackRequestCraftLoomAction(buffer, (CraftLoomAction) action);
                break;
            case CRAFT_NON_IMPLEMENTED:
                this.writeItemStackRequestCraftNonImplementedDeprecatedAction(buffer, (CraftNonImplementedAction) action);
                break;
            case CRAFT_RESULTS:
                this.writeItemStackRequestCraftResultsDeprecatedAction(buffer, (CraftResultsDeprecatedAction) action);
                break;
        }
    }

    protected ItemStackRequestAction readItemStackRequestAction(ByteBuf buffer) {
        final ItemStackRequestActionType type = this.stackRequestActionTypes.getType(VarInts.readUnsignedInt(buffer));
        buffer.readUnsignedByte();
        switch (type) {
            case TAKE:
                return this.readItemStackRequestTakeAction(buffer);
            case PLACE:
                return this.readItemStackRequestPlaceAction(buffer);
            case SWAP:
                return this.readItemStackRequestSwapAction(buffer);
            case DROP:
                return this.readItemStackRequestDropAction(buffer);
            case DESTROY:
                return this.readItemStackRequestDestroyAction(buffer);
            case CONSUME:
                return this.readItemStackRequestConsumeAction(buffer);
            case CREATE:
                return this.readItemStackRequestCreateAction(buffer);
            case SCREEN_LAB_TABLE_COMBINE:
                return this.readItemStackRequestLabTableCombineAction(buffer);
            case SCREEN_BEACON_PAYMENT:
                return this.readItemStackRequestBeaconPaymentAction(buffer);
            case SCREEN_HUD_MINE_BLOCK:
                return this.readItemStackRequestMineBlockAction(buffer);
            case CRAFT_RECIPE:
                return this.readItemStackRequestCraftRecipeAction(buffer);
            case CRAFT_RECIPE_AUTO:
                return this.readItemStackRequestCraftRecipeAutoAction(buffer);
            case CRAFT_CREATIVE:
                return this.readItemStackRequestCraftCreativeAction(buffer);
            case CRAFT_RECIPE_OPTIONAL:
                return this.readItemStackRequestCraftRecipeOptionalAction(buffer);
            case CRAFT_REPAIR_AND_DISENCHANT:
                return this.readItemStackRequestCraftRepairAndDisenchantAction(buffer);
            case CRAFT_LOOM:
                return this.readItemStackRequestCraftLoomAction(buffer);
            case CRAFT_NON_IMPLEMENTED:
                return this.readItemStackRequestCraftNonImplementedDeprecatedAction(buffer);
            case CRAFT_RESULTS:
                return this.readItemStackRequestCraftResultsDeprecatedAction(buffer);
            default:
                throw new IllegalArgumentException("Unknown ItemStackRequestActionType: " + type);
        }
    }

    @Override
    protected void writeStackRequestSlotInfo(ByteBuf buffer, ItemStackRequestSlotInfo data) {
        this.writeFullContainerName(buffer, data.getFullContainerName());
        buffer.writeByte(data.getSlot());
        buffer.writeIntLE(data.getStackNetworkId());
    }

    @Override
    protected ItemStackRequestSlotInfo readStackRequestSlotInfo(ByteBuf buffer) {
        final FullContainerName fullContainerName = this.readFullContainerName(buffer);
        return new ItemStackRequestSlotInfo(
                fullContainerName.getContainerName(),
                buffer.readUnsignedByte(),
                buffer.readIntLE(),
                fullContainerName
        );
    }

    protected void writeItemStackRequestTakeAction(ByteBuf buffer, TakeAction action) {
        buffer.writeByte(action.getAmount());
        this.writeStackRequestSlotInfo(buffer, action.getSource());
        this.writeStackRequestSlotInfo(buffer, action.getDestination());
    }

    protected TakeAction readItemStackRequestTakeAction(ByteBuf buffer) {
        return new TakeAction(
                buffer.readUnsignedByte(),
                this.readStackRequestSlotInfo(buffer),
                this.readStackRequestSlotInfo(buffer)
        );
    }

    protected void writeItemStackRequestPlaceAction(ByteBuf buffer, PlaceAction action) {
        buffer.writeByte(action.getAmount());
        this.writeStackRequestSlotInfo(buffer, action.getSource());
        this.writeStackRequestSlotInfo(buffer, action.getDestination());
    }

    protected PlaceAction readItemStackRequestPlaceAction(ByteBuf buffer) {
        return new PlaceAction(
                buffer.readUnsignedByte(),
                this.readStackRequestSlotInfo(buffer),
                this.readStackRequestSlotInfo(buffer)
        );
    }

    protected void writeItemStackRequestSwapAction(ByteBuf buffer, SwapAction action) {
        this.writeStackRequestSlotInfo(buffer, action.getSource());
        this.writeStackRequestSlotInfo(buffer, action.getDestination());
    }

    protected SwapAction readItemStackRequestSwapAction(ByteBuf buffer) {
        return new SwapAction(
                this.readStackRequestSlotInfo(buffer),
                this.readStackRequestSlotInfo(buffer)
        );
    }

    protected void writeItemStackRequestDropAction(ByteBuf buffer, DropAction action) {
        buffer.writeByte(action.getAmount());
        this.writeStackRequestSlotInfo(buffer, action.getSource());
        buffer.writeBoolean(action.isRandomly());
    }

    protected DropAction readItemStackRequestDropAction(ByteBuf buffer) {
        return new DropAction(
                buffer.readUnsignedByte(),
                this.readStackRequestSlotInfo(buffer),
                buffer.readBoolean()
        );
    }

    protected void writeItemStackRequestDestroyAction(ByteBuf buffer, DestroyAction action) {
        buffer.writeByte(action.getAmount());
        this.writeStackRequestSlotInfo(buffer, action.getSource());
    }

    protected DestroyAction readItemStackRequestDestroyAction(ByteBuf buffer) {
        return new DestroyAction(
                buffer.readUnsignedByte(),
                this.readStackRequestSlotInfo(buffer)
        );
    }

    protected void writeItemStackRequestConsumeAction(ByteBuf buffer, ConsumeAction action) {
        buffer.writeByte(action.getAmount());
        this.writeStackRequestSlotInfo(buffer, action.getSource());
    }

    protected ConsumeAction readItemStackRequestConsumeAction(ByteBuf buffer) {
        return new ConsumeAction(
                buffer.readUnsignedByte(),
                this.readStackRequestSlotInfo(buffer)
        );
    }

    protected void writeItemStackRequestCreateAction(ByteBuf buffer, CreateAction action) {
        buffer.writeByte(action.getResultsIndex());
    }

    protected CreateAction readItemStackRequestCreateAction(ByteBuf buffer) {
        return new CreateAction(
                buffer.readUnsignedByte()
        );
    }

    protected void writeItemStackRequestLabTableCombineAction(ByteBuf buffer, LabTableCombineAction action) {

    }

    protected LabTableCombineAction readItemStackRequestLabTableCombineAction(ByteBuf buffer) {
        return new LabTableCombineAction();
    }

    protected void writeItemStackRequestBeaconPaymentAction(ByteBuf buffer, BeaconPaymentAction action) {
        VarInts.writeInt(buffer, action.getPrimaryEffectId());
        VarInts.writeInt(buffer, action.getSecondaryEffectId());
    }

    protected BeaconPaymentAction readItemStackRequestBeaconPaymentAction(ByteBuf buffer) {
        return new BeaconPaymentAction(
                VarInts.readInt(buffer),
                VarInts.readInt(buffer)
        );
    }

    protected void writeItemStackRequestMineBlockAction(ByteBuf buffer, MineBlockAction action) {
        VarInts.writeInt(buffer, action.getSlot());
        VarInts.writeInt(buffer, action.getPredictedDurability());
        buffer.writeIntLE(action.getStackNetworkId());
    }

    protected MineBlockAction readItemStackRequestMineBlockAction(ByteBuf buffer) {
        return new MineBlockAction(
                VarInts.readInt(buffer),
                VarInts.readInt(buffer),
                buffer.readIntLE()
        );
    }

    protected void writeItemStackRequestCraftRecipeAction(ByteBuf buffer, CraftRecipeAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
        buffer.writeByte(action.getNumberOfRequestedCrafts());
    }

    protected CraftRecipeAction readItemStackRequestCraftRecipeAction(ByteBuf buffer) {
        return new CraftRecipeAction(
                new RecipeNetId(VarInts.readUnsignedInt(buffer)),
                buffer.readUnsignedByte()
        );
    }

    protected void writeItemStackRequestCraftRecipeAutoAction(ByteBuf buffer, AutoCraftRecipeAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
        buffer.writeByte(action.getNumberOfRequestedCrafts());
        this.writeArray(buffer, action.getIngredients(), this::writeIngredient);
    }

    protected AutoCraftRecipeAction readItemStackRequestCraftRecipeAutoAction(ByteBuf buffer) {
        final RecipeNetId recipeNetId = new RecipeNetId(VarInts.readUnsignedInt(buffer));
        final int numberOfRequestedCrafts = buffer.readUnsignedByte();
        final List<RecipeIngredient> ingredients = new ObjectArrayList<>();
        this.readArray(buffer, ingredients, this::readIngredient);
        return new AutoCraftRecipeAction(recipeNetId, -1, ingredients, numberOfRequestedCrafts);
    }

    protected void writeItemStackRequestCraftCreativeAction(ByteBuf buffer, CraftCreativeAction action) {
        VarInts.writeUnsignedInt(buffer, action.getCreativeItemNetId());
        buffer.writeByte(action.getNumberOfRequestedCrafts());
    }

    protected CraftCreativeAction readItemStackRequestCraftCreativeAction(ByteBuf buffer) {
        return new CraftCreativeAction(
                VarInts.readUnsignedInt(buffer),
                buffer.readUnsignedByte()
        );
    }

    protected void writeItemStackRequestCraftRecipeOptionalAction(ByteBuf buffer, CraftRecipeOptionalAction action) {
        VarInts.writeUnsignedInt(buffer, action.getRecipeNetId().getRawId());
        buffer.writeIntLE(action.getFilteredStringIndex());
    }

    protected CraftRecipeOptionalAction readItemStackRequestCraftRecipeOptionalAction(ByteBuf buffer) {
        return new CraftRecipeOptionalAction(
                new RecipeNetId(VarInts.readUnsignedInt(buffer)),
                buffer.readIntLE()
        );
    }

    protected void writeItemStackRequestCraftRepairAndDisenchantAction(ByteBuf buffer, CraftGrindstoneAction action) {
        buffer.writeIntLE(action.getRecipeNetId().getRawId());
        buffer.writeByte(action.getNumberOfRequestedCrafts());
        VarInts.writeInt(buffer, action.getRepairCost());
    }

    protected CraftGrindstoneAction readItemStackRequestCraftRepairAndDisenchantAction(ByteBuf buffer) {
        return new CraftGrindstoneAction(
                new RecipeNetId(buffer.readIntLE()),
                buffer.readUnsignedByte(),
                VarInts.readInt(buffer)
        );
    }

    protected void writeItemStackRequestCraftLoomAction(ByteBuf buffer, CraftLoomAction action) {
        this.writeString(buffer, action.getPatternNameId());
        buffer.writeByte(action.getNumCrafts());
    }

    protected CraftLoomAction readItemStackRequestCraftLoomAction(ByteBuf buffer) {
        return new CraftLoomAction(
                this.readString(buffer),
                buffer.readUnsignedByte()
        );
    }

    protected void writeItemStackRequestCraftNonImplementedDeprecatedAction(ByteBuf buffer, CraftNonImplementedAction action) {

    }

    protected CraftNonImplementedAction readItemStackRequestCraftNonImplementedDeprecatedAction(ByteBuf buffer) {
        return new CraftNonImplementedAction();
    }

    protected void writeItemStackRequestCraftResultsDeprecatedAction(ByteBuf buffer, CraftResultsDeprecatedAction action) {
        this.writeArray(buffer, action.getCraftResults(), this::writeItemStackRequestNetworkItemInstanceDescriptor);
        buffer.writeByte(action.getNumCrafts());
    }

    protected CraftResultsDeprecatedAction readItemStackRequestCraftResultsDeprecatedAction(ByteBuf buffer) {
        final List<ItemStackRequestNetworkItemInstanceDescriptor> craftResults = new ObjectArrayList<>();
        this.readArray(buffer, craftResults, this::readItemStackRequestNetworkItemInstanceDescriptor);
        final int numCrafts = buffer.readUnsignedByte();
        return new CraftResultsDeprecatedAction(null, craftResults, numCrafts);
    }

    @Override
    public void writeSerializedSkin(ByteBuf buffer, SerializedSkin serializedSkin) {
        this.writeString(buffer, serializedSkin.getID());
        this.writeString(buffer, serializedSkin.getPlayFabID());
        this.writeString(buffer, serializedSkin.getResourcePatch());
        this.writeSkinImage(buffer, serializedSkin.getImageData());
        this.writeArray(buffer, serializedSkin.getAnimatedImageData(), this::writeAnimatedImageData);
        this.writeSkinImage(buffer, serializedSkin.getCapeImageData());
        this.writeString(buffer, serializedSkin.getGeometryData());
        this.writeString(buffer, serializedSkin.getGeometryDataMinEngineVersion());
        this.writeString(buffer, serializedSkin.getAnimationData());
        this.writeString(buffer, serializedSkin.getCapeID());
        this.writeString(buffer, serializedSkin.getFullID());
        buffer.writeByte(serializedSkin.getArmSize().ordinal());
        buffer.writeIntLE(serializedSkin.getSkinColor());
        this.writeArray(buffer, serializedSkin.getPersonaPieces(), this::writeSerializedPersonaPieceHandle);
        this.writePieceTintColors(buffer, serializedSkin.getPieceTintColors());
        buffer.writeBoolean(serializedSkin.isPremium());
        buffer.writeBoolean(serializedSkin.isPersona());
        buffer.writeBoolean(serializedSkin.isPersonaCapeOnClassicSkin());
        buffer.writeBoolean(serializedSkin.isPrimaryUser());
        buffer.writeBoolean(serializedSkin.isOverridesPlayerAppearance());
        this.writeString(buffer, serializedSkin.getTrustedSkinFlag().getId());
        this.writeString(buffer, serializedSkin.getProfileHash());
    }

    @Override
    public SerializedSkin readSerializedSkin(ByteBuf buffer) {
        final SerializedSkin serializedSkin = new SerializedSkin();
        serializedSkin.setID(this.readString(buffer));
        serializedSkin.setPlayFabID(this.readString(buffer));
        serializedSkin.setResourcePatch(this.readString(buffer));
        serializedSkin.setImageData(this.readSkinImage(buffer));
        this.readArray(buffer, serializedSkin.getAnimatedImageData(), this::readAnimatedImageData);
        serializedSkin.setCapeImageData(this.readSkinImage(buffer));
        serializedSkin.setGeometryData(this.readStringMaxLen(buffer, this.encodingSettings.maxGeometryDataSize()));
        serializedSkin.setGeometryDataMinEngineVersion(this.readString(buffer));
        serializedSkin.setAnimationData(this.readString(buffer));
        serializedSkin.setCapeID(this.readString(buffer));
        serializedSkin.setFullID(this.readString(buffer));
        serializedSkin.setArmSize(ArmSizeType.from(buffer.readUnsignedByte()));
        serializedSkin.setSkinColor(buffer.readIntLE());
        this.readArray(buffer, serializedSkin.getPersonaPieces(), this::readSerializedPersonaPieceHandle);
        this.readPieceTintColors(buffer, serializedSkin.getPieceTintColors());
        serializedSkin.setPremium(buffer.readBoolean());
        serializedSkin.setPersona(buffer.readBoolean());
        serializedSkin.setPersonaCapeOnClassicSkin(buffer.readBoolean());
        serializedSkin.setPrimaryUser(buffer.readBoolean());
        serializedSkin.setOverridesPlayerAppearance(buffer.readBoolean());
        serializedSkin.setTrustedSkinFlag(TrustedSkinFlag.from(this.readString(buffer)));
        serializedSkin.setProfileHash(this.readString(buffer));
        return serializedSkin;
    }

    protected void writeSkinImage(ByteBuf buffer, SkinImage skinImage) {
        Preconditions.checkArgument(
                this.encodingSettings.maxSkinImageWidth() < 0 || skinImage.getWidth() <= this.encodingSettings.maxSkinImageWidth(),
                "The skin image exceeds the maximum image width" +
                        "value: " + skinImage.getWidth() + ", max: " + this.encodingSettings.maxSkinImageWidth()
        );
        Preconditions.checkArgument(
                this.encodingSettings.maxSkinImageHeight() < 0 || skinImage.getHeight() <= this.encodingSettings.maxSkinImageHeight(),
                "The skin image exceeds the maximum image height, " +
                        "value: " + skinImage.getHeight() + ", max: " + this.encodingSettings.maxSkinImageHeight()
        );
        Preconditions.checkArgument(
                this.encodingSettings.maxSkinImageBytesLength() < 0 || skinImage.getImageBytes().length <= this.encodingSettings.maxSkinImageBytesLength(),
                "The skin image exceeds the maximum image bytes length, value: " +
                        skinImage.getImageBytes().length + ", max: " + this.encodingSettings.maxSkinImageBytesLength()
        );
        buffer.writeIntLE(skinImage.getWidth());
        buffer.writeIntLE(skinImage.getHeight());
        this.writeByteArray(buffer, skinImage.getImageBytes());
    }

    protected SkinImage readSkinImage(ByteBuf buffer) {
        final int width = buffer.readIntLE();
        Preconditions.checkArgument(
                this.encodingSettings.maxSkinImageWidth() < 0 || width <= this.encodingSettings.maxSkinImageWidth(),
                "The skin image exceeds the maximum image width" +
                        "value: " + width + ", max: " + this.encodingSettings.maxSkinImageWidth()
        );
        final int height = buffer.readIntLE();
        Preconditions.checkArgument(
                this.encodingSettings.maxSkinImageHeight() < 0 || height <= this.encodingSettings.maxSkinImageHeight(),
                "The skin image exceeds the maximum image height, " +
                        "value: " + height + ", max: " + this.encodingSettings.maxSkinImageHeight()
        );
        final byte[] imageBytes = this.readByteArray(buffer, this.encodingSettings.maxSkinImageBytesLength());
        return new SkinImage(width, height, imageBytes);
    }

    protected void writeAnimatedImageData(ByteBuf buffer, AnimatedImageData animatedImageData) {
        this.writeSkinImage(buffer, animatedImageData.getSkinImage());
        VarInts.writeUnsignedInt(buffer, animatedImageData.getAnimatedTextureType().ordinal());
        buffer.writeFloatLE(animatedImageData.getFrames());
        VarInts.writeUnsignedInt(buffer, animatedImageData.getAnimationExpression().ordinal());
    }

    protected AnimatedImageData readAnimatedImageData(ByteBuf buffer) {
        final AnimatedImageData animatedImageData = new AnimatedImageData();
        animatedImageData.setSkinImage(this.readSkinImage(buffer));
        animatedImageData.setAnimatedTextureType(AnimatedTextureType.from(VarInts.readUnsignedInt(buffer)));
        animatedImageData.setFrames(buffer.readFloatLE());
        animatedImageData.setAnimationExpression(AnimationExpression.from(VarInts.readUnsignedInt(buffer)));
        return animatedImageData;
    }

    protected void writeSerializedPersonaPieceHandle(ByteBuf buffer, SerializedPersonaPieceHandle handle) {
        this.writeString(buffer, handle.getPieceId());
        buffer.writeIntLE(handle.getPieceType().ordinal());
        this.writeUuid(buffer, handle.getPackId());
        buffer.writeBoolean(handle.isDefaultPiece());
        this.writeString(buffer, handle.getProductId());
    }

    protected SerializedPersonaPieceHandle readSerializedPersonaPieceHandle(ByteBuf buffer) {
        final SerializedPersonaPieceHandle handle = new SerializedPersonaPieceHandle();
        handle.setPieceId(this.readString(buffer));
        handle.setPieceType(PieceType.from(buffer.readIntLE()));
        handle.setPackId(this.readUuid(buffer));
        handle.setDefaultPiece(buffer.readBoolean());
        handle.setProductId(this.readString(buffer));
        return handle;
    }

    protected void writePieceTintColors(ByteBuf buffer, Map<PieceType, TintMapColor> pieceTintColors) {
        VarInts.writeUnsignedInt(buffer, pieceTintColors.size());
        for (Map.Entry<PieceType, TintMapColor> entry : pieceTintColors.entrySet()) {
            this.writeString(buffer, entry.getKey().getId());
            this.writeTintMapColor(buffer, entry.getValue());
        }
    }

    protected void readPieceTintColors(ByteBuf buffer, Map<PieceType, TintMapColor> pieceTintColors) {
        final int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            pieceTintColors.put(PieceType.from(this.readString(buffer)), this.readTintMapColor(buffer));
        }
    }

    protected void writeTintMapColor(ByteBuf buffer, TintMapColor color) {
        for (int i = 0; i < 4; i++) {
            buffer.writeIntLE(color.getColors().get(i));
        }
    }

    protected TintMapColor readTintMapColor(ByteBuf buffer) {
        final TintMapColor color = new TintMapColor();
        for (int i = 0; i < 4; i++) {
            color.getColors().add(buffer.readIntLE());
        }
        return color;
    }
}