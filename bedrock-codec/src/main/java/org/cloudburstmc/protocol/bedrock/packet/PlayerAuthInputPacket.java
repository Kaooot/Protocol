package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Long;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ClientPlayMode;
import org.cloudburstmc.protocol.bedrock.data.InputMode;
import org.cloudburstmc.protocol.bedrock.data.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.NewInteractionModel;
import org.cloudburstmc.protocol.bedrock.data.PackedItemUseLegacyInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.data.PlayerInputTick;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PlayerAuthInputPacket implements BedrockPacket {
  private Vector2f playerRotation;

  private Vector3f position;

  private Vector2f moveVector;

  private float playerHeadRotation;

  private final List<PlayerAuthInputData> inputData = new ObjectArrayList<>();

  private InputMode inputMode;

  private ClientPlayMode playMode;

  private NewInteractionModel newInteractionModel;

  private Vector2f interactRotation;

  private PlayerInputTick clientTick;

  private Vector3f posDelta;

  private PackedItemUseLegacyInventoryTransaction itemUseTransaction;

  private ItemStackRequest itemStackRequest;

  private final List<PlayerBlockActionData> playerBlockActions = new ObjectArrayList<>();

  private Vector2f vehicleRotation;

  private Long clientPredictedVehicle;

  private Vector2f analogMoveVector;

  private Vector3f cameraOrientation;

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
