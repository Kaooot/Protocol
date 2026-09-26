package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.diagnostics.EntityDiagnosticTimingInfo;
import org.cloudburstmc.protocol.bedrock.data.diagnostics.MemoryCategoryCounter;
import org.cloudburstmc.protocol.bedrock.data.diagnostics.SystemCategory;
import org.cloudburstmc.protocol.bedrock.data.diagnostics.SystemDiagnosticTimingInfo;
import org.cloudburstmc.protocol.bedrock.data.diagnostics.WhiskerScopeDataSummary;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ServerboundDiagnosticsPacket implements BedrockPacket {

    private float avgFps;
    private float avgServerSimTickTimeMS;
    private float avgClientSimTickTimeMS;
    private float avgBeginFrameTimeMS;
    private float avgInputTimeMS;
    private float avgRenderTimeMS;
    private float avgEndFrameTimeMS;
    private float avgRemainderTimePercent;
    private float avgUnaccountedTimePercent;
    private final List<MemoryCategoryCounter> memoryCategoryValues = new ObjectArrayList<>();
    private final List<EntityDiagnosticTimingInfo> entityDiagnostics = new ObjectArrayList<>();
    private final List<SystemDiagnosticTimingInfo> systemDiagnostics = new ObjectArrayList<>();
    private final List<SystemCategory> systemCategories = new ObjectArrayList<>();
    private final List<WhiskerScopeDataSummary> whiskerScopes = new ObjectArrayList<>();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVERBOUND_DIAGNOSTICS;
    }

    @Override
    public ServerboundDiagnosticsPacket clone() {
        try {
            return (ServerboundDiagnosticsPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}