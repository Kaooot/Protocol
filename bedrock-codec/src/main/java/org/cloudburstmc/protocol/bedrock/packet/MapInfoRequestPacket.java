package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ClientPixelsProxy;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 68 (0x44)
 * If the server finds the map, it sends the data back. If it can't find the map, it creates it and sends the map and data back.
 * (the map creation data packet and the map data packet are separate packets).
 * The response from the server potentially has to load from disk, just an fyi.
 * This packet is fired via map item tick, if the map data we have is invalid, or if the map is placed in an item frame.
 *
 * For Client Side Generation when we re-sample pixels from the Client's ChunkSource we need to inform the Server's map about
 * these new pixels so that it can save them to LevelStorage. Use this packet to send to the Server the extra pixels
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class MapInfoRequestPacket implements BedrockPacket {
  private long mapUniqueID;

  private final List<ClientPixelsProxy> clientPixelsList = new ObjectArrayList<>();

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.MAP_INFO_REQUEST;
  }

  @Override
  public MapInfoRequestPacket clone() {
    try {
      return (MapInfoRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
