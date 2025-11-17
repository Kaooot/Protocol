package org.cloudburstmc.protocol.bedrock.codec.v897.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v594.serializer.AvailableCommandsSerializer_v594;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

/**
 * @author Kaooot
 */
public class AvailableCommandsSerializer_v897 extends AvailableCommandsSerializer_v594 {

    public AvailableCommandsSerializer_v897(TypeMap<CommandParam> paramTypeMap) {
        super(paramTypeMap);
    }

    @Override
    protected void writeEnums(ByteBuf buffer, BedrockCodecHelper helper, List<String> values, List<CommandEnumData> enums) {
        helper.writeArray(buffer, enums, (buf, commandEnum) -> {
            helper.writeString(buf, commandEnum.getName());

            VarInts.writeUnsignedInt(buffer, commandEnum.getValues().size());
            for (String value : commandEnum.getValues().keySet()) {
                int index = values.indexOf(value);
                checkArgument(index > -1, "Invalid enum value detected: " + value);
                WRITE_INT.accept(buf, index);
            }
        });
    }

    @Override
    protected void readEnums(ByteBuf buffer, BedrockCodecHelper helper, List<String> values, List<CommandEnumData> enums) {
        helper.readArray(buffer, enums, buf -> {
            String name = helper.readString(buf);

            int length = VarInts.readUnsignedInt(buffer);
            LinkedHashMap<String, Set<CommandEnumConstraint>> enumValues = new LinkedHashMap<>();
            for (int i = 0; i < length; i++) {
                enumValues.put(values.get(READ_INT.applyAsInt(buf)), EnumSet.noneOf(CommandEnumConstraint.class));
            }
            return new CommandEnumData(name, enumValues, false);
        });
    }

    @Override
    protected void writeSubCommand(ByteBuf buffer, BedrockCodecHelper helper, List<String> values, ChainedSubCommandData data) {
        helper.writeString(buffer, data.getName());
        helper.writeArray(buffer, data.getValues(), (buf, val) -> {
            int first = values.indexOf(val.getFirst());
            checkArgument(first > -1, "Invalid enum value detected: " + val.getFirst());

            int second = values.indexOf(val.getSecond());
            checkArgument(second > -1, "Invalid enum value detected: " + val.getSecond());

            VarInts.writeUnsignedInt(buf, first);
            VarInts.writeUnsignedInt(buf, second);
        });
    }

    @Override
    protected ChainedSubCommandData readSubCommand(ByteBuf buffer, BedrockCodecHelper helper, List<String> values) {
        String name = helper.readString(buffer);
        ChainedSubCommandData data = new ChainedSubCommandData(name);

        helper.readArray(buffer, data.getValues(), buf -> {
            int first = VarInts.readUnsignedInt(buf);
            int second = VarInts.readUnsignedInt(buf);
            return new ChainedSubCommandData.Value(values.get(first), values.get(second));
        });
        return data;
    }

    @Override
    protected void writeCommand(ByteBuf buffer, BedrockCodecHelper helper, CommandData commandData, List<CommandEnumData> enums, List<CommandEnumData> softEnums, List<String> postFixes, List<ChainedSubCommandData> subCommands) {
        helper.writeString(buffer, commandData.getName());
        helper.writeString(buffer, commandData.getDescription());
        this.writeFlags(buffer, commandData.getFlags());
        CommandPermissionLevel permission = commandData.getPermission() == null ? CommandPermissionLevel.ANY : commandData.getPermission();
        helper.writeString(buffer, permission.getId());

        CommandEnumData aliases = commandData.getAliases();
        buffer.writeIntLE(aliases == null ? -1 : enums.indexOf(aliases));

        helper.writeArray(buffer, commandData.getSubcommands(), (buf, subcommand) -> {
            int index = subCommands.indexOf(subcommand);
            checkArgument(index > -1, "Invalid subcommand index: " + subcommand);
            buf.writeIntLE(index);
        });

        CommandOverloadData[] overloads = commandData.getOverloads();
        VarInts.writeUnsignedInt(buffer, overloads.length);
        for (CommandOverloadData overload : overloads) {
            buffer.writeBoolean(overload.isChaining());
            VarInts.writeUnsignedInt(buffer, overload.getOverloads().length);
            for (CommandParamData param : overload.getOverloads()) {
                this.writeParameter(buffer, helper, param, enums, softEnums, postFixes);
            }
        }
    }

    @Override
    protected CommandData readCommand(ByteBuf buffer, BedrockCodecHelper helper, List<CommandEnumData> enums,
                                      List<String> postfixes, Set<Consumer<List<CommandEnumData>>> softEnumParameters, List<ChainedSubCommandData> subCommandsList) {
        String name = helper.readString(buffer);
        String description = helper.readString(buffer);
        Set<CommandData.Flag> flags = this.readFlags(buffer);
        String permissions = helper.readString(buffer);
        int aliasIndex = buffer.readIntLE();
        CommandEnumData aliases = aliasIndex == -1 ? null : enums.get(aliasIndex);

        List<ChainedSubCommandData> subcommands = new ObjectArrayList<>();
        helper.readArray(buffer, subcommands, (buf, help) -> {
            int index = buffer.readIntLE();
            return subCommandsList.get(index);
        });

        CommandOverloadData[] overloads = new CommandOverloadData[VarInts.readUnsignedInt(buffer)];
        for (int i = 0; i < overloads.length; i++) {
            boolean chaining = buffer.readBoolean();
            CommandParamData[] params = new CommandParamData[VarInts.readUnsignedInt(buffer)];
            overloads[i] = new CommandOverloadData(chaining, params);
            for (int i2 = 0; i2 < params.length; i2++) {
                params[i2] = readParameter(buffer, helper, enums, postfixes, softEnumParameters);
            }
        }
        return new CommandData(name, description, flags, CommandPermissionLevel.from(permissions), aliases, subcommands, overloads);
    }
}