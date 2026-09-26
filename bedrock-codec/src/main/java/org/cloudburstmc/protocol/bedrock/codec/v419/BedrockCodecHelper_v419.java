package org.cloudburstmc.protocol.bedrock.codec.v419;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v407.BedrockCodecHelper_v407;
import org.cloudburstmc.protocol.bedrock.data.world.Experiments;
import org.cloudburstmc.protocol.bedrock.data.world.ExperimentToggle;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerInputTick;
import org.cloudburstmc.protocol.bedrock.data.skin.PersonaAnimatedTextureType;
import org.cloudburstmc.protocol.bedrock.data.skin.AnimatedImageData;
import org.cloudburstmc.protocol.bedrock.data.skin.PersonaAnimationExpression;
import org.cloudburstmc.protocol.bedrock.data.skin.SkinImage;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v419 extends BedrockCodecHelper_v407 {

    protected static final PersonaAnimationExpression[] EXPRESSION_TYPES = PersonaAnimationExpression.values();

    public BedrockCodecHelper_v419(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames);
    }

    @Override
    public void writeExperiments(ByteBuf buffer,  Experiments experiments) {
        this.writeArray(buffer, experiments.getToggles(), ByteBuf::writeIntLE, this::writeExperimentToggle);
        buffer.writeBoolean(experiments.isExperimentsEverToggled());
    }

    @Override
    public Experiments readExperiments(ByteBuf buffer) {
        final Experiments experiments = new Experiments();
        this.readArray(buffer, experiments.getToggles(), ByteBuf::readIntLE, this::readExperimentToggle);
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

    @Override
    public AnimatedImageData readAnimationData(ByteBuf buffer) {
        SkinImage image = this.readImage(buffer, SkinImage.ANIMATION_SIZE);
        PersonaAnimatedTextureType textureType = TEXTURE_TYPES[buffer.readIntLE()];
        float frames = buffer.readFloatLE();
        PersonaAnimationExpression expressionType = EXPRESSION_TYPES[buffer.readIntLE()];
        return new AnimatedImageData(image, textureType, frames, expressionType);
    }

    @Override
    public void writeAnimationData(ByteBuf buffer, AnimatedImageData animation) {
        super.writeAnimationData(buffer, animation);
        buffer.writeIntLE(animation.getAnimationExpression().ordinal());
    }

    @Override
    public void writePlayerInputTick(ByteBuf buffer, PlayerInputTick inputTick) {
        VarInts.writeUnsignedLong(buffer, inputTick.getInputTick());
    }

    @Override
    public PlayerInputTick readPlayerInputTick(ByteBuf buffer) {
        return new PlayerInputTick(VarInts.readUnsignedLong(buffer));
    }
}