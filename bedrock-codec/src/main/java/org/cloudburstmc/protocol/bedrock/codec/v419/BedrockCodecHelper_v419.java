package org.cloudburstmc.protocol.bedrock.codec.v419;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v407.BedrockCodecHelper_v407;
import org.cloudburstmc.protocol.bedrock.data.Experiment;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.ExperimentToggle;
import org.cloudburstmc.protocol.bedrock.data.payload.experiment.Experiments;
import org.cloudburstmc.protocol.bedrock.data.skin.AnimatedTextureType;
import org.cloudburstmc.protocol.bedrock.data.skin.AnimationData;
import org.cloudburstmc.protocol.bedrock.data.skin.AnimationExpressionType;
import org.cloudburstmc.protocol.bedrock.data.skin.ImageData;
import org.cloudburstmc.protocol.common.util.TypeMap;

import java.util.List;

public class BedrockCodecHelper_v419 extends BedrockCodecHelper_v407 {

    protected static final AnimationExpressionType[] EXPRESSION_TYPES = AnimationExpressionType.values();

    public BedrockCodecHelper_v419(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
    }

    @Override
    public void writeExperiments(ByteBuf buffer, Experiments experiments) {
        buffer.writeIntLE(experiments.getToggles().size());

        for (ExperimentToggle experimentToggle : experiments.getToggles()) {
            this.writeString(buffer, experimentToggle.getName());
            buffer.writeBoolean(experimentToggle.isEnabled());
        }
    }

    @Override
    public Experiments readExperiments(ByteBuf buffer) {
        final Experiments experiments = new Experiments();
        int count = buffer.readIntLE(); // Actually unsigned
        for (int i = 0; i < count; i++) {
            experiments.getToggles().add(new ExperimentToggle(
                    this.readString(buffer),
                    buffer.readBoolean() // Hardcoded to true in 414
            ));
        }
        return experiments;
    }

    @Override
    public AnimationData readAnimationData(ByteBuf buffer) {
        ImageData image = this.readImage(buffer);
        AnimatedTextureType textureType = TEXTURE_TYPES[buffer.readIntLE()];
        float frames = buffer.readFloatLE();
        AnimationExpressionType expressionType = EXPRESSION_TYPES[buffer.readIntLE()];
        return new AnimationData(image, textureType, frames, expressionType);
    }

    @Override
    public void writeAnimationData(ByteBuf buffer, AnimationData animation) {
        super.writeAnimationData(buffer, animation);
        buffer.writeIntLE(animation.getExpressionType().ordinal());
    }
}
