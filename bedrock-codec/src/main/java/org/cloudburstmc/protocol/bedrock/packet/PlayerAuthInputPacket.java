package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.PackedLegacyItemUseInventoryTransaction;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlayerAuthInputPacket implements BedrockPacket {
    private Vector3f playerRotation; // head rot after motion
    private Vector3f position;
    private Vector2f moveVector;
    private final Set<PlayerAuthInputData> inputData = EnumSet.noneOf(PlayerAuthInputData.class);
    private InputMode inputMode;
    private ClientPlayMode playMode;
    /**
     * @deprecated since v748
     */
    private Vector3f vrGazeDirection;
    private long clientTick;
    private Vector3f posDelta;
    /**
     * {@link #inputData} must contain {@link PlayerAuthInputData#PERFORM_ITEM_INTERACTION} in order for this to not be null.
     *
     * @since v428
     */
    private PackedLegacyItemUseInventoryTransaction itemUseTransaction;
    /**
     * {@link #inputData} must contain {@link PlayerAuthInputData#PERFORM_ITEM_STACK_REQUEST} in order for this to not be null.
     *
     * @since v428
     */
    private ItemStackRequest itemStackRequest;
    /**
     * {@link #inputData} must contain {@link PlayerAuthInputData#PERFORM_BLOCK_ACTIONS} in order for this to not be empty.
     *
     * @since v428
     */
    private final List<PlayerBlockActionData> playerBlockActions = new ObjectArrayList<>();
    /**
     * @since v527
     */
    private InputInteractionModel newInteractionModel;
    /**
     * @since v748
     */
    private Vector2f interactRotation;
    /**
     * @since 575
     */
    private Vector2f analogMoveVector;
    /**
     * @since 649
     */
    private Long clientPredictedVehicle;
    /**
     * @since 662
     */
    private Vector2f vehicleRotation;
    /**
     * @since v748
     */
    private Vector3f cameraOrientation;
    /**
     * @since v766
     */
    private Vector2f rawMoveVector;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_AUTH_INPUT;
    }

    @Override
    public PlayerAuthInputPacket clone() {
        try {
            return (PlayerAuthInputPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

