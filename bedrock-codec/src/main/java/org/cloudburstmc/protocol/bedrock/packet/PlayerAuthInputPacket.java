package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
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
    @Deprecated
    @Removed("1.21.40")
    private Vector3f vrGazeDirection;
    private ClientPlayMode playMode;
    @Added("1.19.0")
    private NewInteractionModel newInteractionModel;
    @Added("1.21.40")
    private Vector2f interactRotation;
    @Added("1.16.100")
    private PlayerInputTick clientTick;
    @Added("1.16.100")
    private Vector3f posDelta;
    /**
     * {@link #inputData} must contain {@link PlayerAuthInputData#PERFORM_ITEM_INTERACTION} in order for this to not be null.
     */
    @Added("1.16.210")
    private PackedItemUseLegacyInventoryTransaction itemUseTransaction;
    /**
     * {@link #inputData} must contain {@link PlayerAuthInputData#PERFORM_ITEM_STACK_REQUEST} in order for this to not be null.
     */
    @Added("1.16.210")
    private ItemStackRequest itemStackRequest;
    /**
     * {@link #inputData} must contain {@link PlayerAuthInputData#PERFORM_BLOCK_ACTIONS} in order for this to not be empty.
     */
    @Added("1.16.210")
    private final List<PlayerBlockActionData> playerBlockActions = new ObjectArrayList<>();
    @Added("1.20.70")
    private Vector2f vehicleRotation;
    @Added("1.20.60")
    private Long clientPredictedVehicle;
    @Added("1.19.70")
    private Vector2f analogMoveVector;
    @Added("1.21.40")
    private Vector3f cameraOrientation;
    @Added("1.21.50")
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