package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.PackedItemUseLegacyInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.player.*;
import org.cloudburstmc.protocol.bedrock.data.player.input.ClientPlayMode;
import org.cloudburstmc.protocol.bedrock.data.player.input.InputMode;
import org.cloudburstmc.protocol.bedrock.data.player.input.NewInteractionModel;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerAuthInputData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlayerAuthInputPacket implements BedrockPacket {

    private Vector2f playerRotation;
    private Vector3f position;
    private Vector2f moveVector;
    private float playerHeadRotation;
    private final Set<PlayerAuthInputData> inputData = EnumSet.noneOf(PlayerAuthInputData.class);
    private InputMode inputMode;
    /**
     * @deprecated since v748
     */
    private Vector3f vrGazeDirection;
    private ClientPlayMode playMode;
    /**
     * @since v527
     */
    private NewInteractionModel newInteractionModel;
    /**
     * @since v748
     */
    private Vector2f interactRotation;
    /**
     * @since v419
     */
    private PlayerInputTick clientTick;
    /**
     * @since v419
     */
    private Vector3f posDelta;
    /**
     * {@link #inputData} must contain {@link PlayerAuthInputData#PERFORM_ITEM_INTERACTION} in order for this to not be null.
     *
     * @since v428
     */
    private PackedItemUseLegacyInventoryTransaction itemUseTransaction;
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
     * @since v662
     */
    private Vector2f vehicleRotation;
    /**
     * @since v649
     */
    private Long clientPredictedVehicle;
    /**
     * @since v575
     */
    private Vector2f analogMoveVector;
    /**
     * @since v748
     */
    private Vector3f cameraOrientation;
    /**
     * @since v766
     */
    private Vector2f rawMoveVector;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
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