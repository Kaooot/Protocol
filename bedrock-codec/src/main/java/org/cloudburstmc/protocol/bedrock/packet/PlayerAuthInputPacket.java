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

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 144 (0x90)
 * These are for Server Authoritative Movement to sync all player input with the server.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PlayerAuthInputPacket implements BedrockPacket {
  private Vector2f PlayerRotation;

  private Vector3f Position;

  private Vector2f MoveVector;

  private float PlayerHeadRotation;

  private final List<PlayerAuthInputData> InputData = new ObjectArrayList<>();

  private InputMode InputMode;

  private ClientPlayMode PlayMode;

  private NewInteractionModel NewInteractionModel;

  private Vector2f InteractRotation;

  private PlayerInputTick ClientTick;

  private Vector3f PosDelta;

  private PackedItemUseLegacyInventoryTransaction ItemUseTransaction;

  private ItemStackRequest ItemStackRequest;

  private final List<PlayerBlockActionData> PlayerBlockActions = new ObjectArrayList<>();

  private Vector2f VehicleRotation;

  private Long ClientPredictedVehicle;

  private Vector2f AnalogMoveVector;

  private Vector3f CameraOrientation;

  private Vector2f RawMoveVector;

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
