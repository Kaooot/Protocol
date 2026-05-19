package org.cloudburstmc.protocol.bedrock.codec.v419.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ResourcePackStackSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.ExperimentToggle;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.Experiments;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePackStackSerializer_v419 extends ResourcePackStackSerializer_v291 {

    public static final ResourcePackStackSerializer_v419 INSTANCE = new ResourcePackStackSerializer_v419();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeString(buffer, packet.getBaseGameVersion());
        this.writeExperiments(buffer, helper, packet.getExperiments());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setBaseGameVersion(helper.readString(buffer));
        packet.setExperiments(this.readExperiments(buffer, helper));
    }

    protected void writeExperiments(ByteBuf buffer, BedrockCodecHelper helper, Experiments experiments) {
        helper.writeArray(buffer, experiments.getToggles(), this::writeExperimentToggle);
        buffer.writeBoolean(experiments.isExperimentsEverToggled());
    }

    protected Experiments readExperiments(ByteBuf buffer, BedrockCodecHelper helper) {
        final Experiments experiments = new Experiments();
        helper.readArray(buffer, experiments.getToggles(), this::readExperimentToggle);
        experiments.setExperimentsEverToggled(buffer.readBoolean());
        return experiments;
    }

    protected void writeExperimentToggle(ByteBuf buffer, BedrockCodecHelper helper, ExperimentToggle toggle) {
        helper.writeString(buffer, toggle.getName());
        buffer.writeBoolean(toggle.isEnabled());
    }

    protected ExperimentToggle readExperimentToggle(ByteBuf buffer, BedrockCodecHelper helper) {
        return new ExperimentToggle(helper.readString(buffer), buffer.readBoolean());
    }
}