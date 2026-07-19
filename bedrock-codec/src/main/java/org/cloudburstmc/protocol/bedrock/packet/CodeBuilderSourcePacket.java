package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.CodeBuilderExecutionStateCodeStatus;
import org.cloudburstmc.protocol.bedrock.data.CodeBuilderStorageQueryOptionsCategory;
import org.cloudburstmc.protocol.bedrock.data.CodeBuilderStorageQueryOptionsOperation;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 178 (0xb2)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class CodeBuilderSourcePacket implements BedrockPacket {
  private CodeBuilderStorageQueryOptionsOperation Operation;

  private CodeBuilderStorageQueryOptionsCategory Category;

  private CodeBuilderExecutionStateCodeStatus CodeStatus;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.CODE_BUILDER_SOURCE;
  }

  @Override
  public CodeBuilderSourcePacket clone() {
    try {
      return (CodeBuilderSourcePacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}
