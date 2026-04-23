package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.common.util.TypeMap;

@RequiredArgsConstructor
@Getter
public class CommandParam {

    public static final CommandParam UNKNOWN = new CommandParam(CommandParamType.UNKNOWN);
    public static final CommandParam INT = new CommandParam(CommandParamType.INT);
    public static final CommandParam FLOAT = new CommandParam(CommandParamType.FLOAT);
    public static final CommandParam VAL = new CommandParam(CommandParamType.VAL);
    public static final CommandParam RVAL = new CommandParam(CommandParamType.RVAL);
    public static final CommandParam WILDCARD_INT = new CommandParam(CommandParamType.WILDCARD_INT);
    public static final CommandParam OPERATOR = new CommandParam(CommandParamType.OPERATOR);
    public static final CommandParam COMPARE_OPERATOR = new CommandParam(CommandParamType.COMPARE_OPERATOR);
    public static final CommandParam SELECTION = new CommandParam(CommandParamType.SELECTION);
    public static final CommandParam STANDALONE_SELECTION = new CommandParam(CommandParamType.STANDALONE_SELECTION);
    public static final CommandParam WILDCARD_SELECTION = new CommandParam(CommandParamType.WILDCARD_SELECTION);
    public static final CommandParam NON_ID_SELECTOR = new CommandParam(CommandParamType.NON_ID_SELECTOR);
    public static final CommandParam SCORES_ARG = new CommandParam(CommandParamType.SCORES_ARG);
    public static final CommandParam SCORES_ARGS = new CommandParam(CommandParamType.SCORES_ARGS);
    public static final CommandParam SCORE_SELECT_PARAM = new CommandParam(CommandParamType.SCORE_SELECT_PARAM);
    public static final CommandParam SCORE_SELECTOR = new CommandParam(CommandParamType.SCORE_SELECTOR);
    public static final CommandParam TAG_SELECTOR = new CommandParam(CommandParamType.TAG_SELECTOR);
    public static final CommandParam FILE_PATH = new CommandParam(CommandParamType.FILE_PATH);
    public static final CommandParam FILE_PATH_VAL = new CommandParam(CommandParamType.FILE_PATH_VAL);
    public static final CommandParam FILE_PATH_CONT = new CommandParam(CommandParamType.FILE_PATH_CONT);
    public static final CommandParam INTEGER_RANGE_VAL = new CommandParam(CommandParamType.INTEGER_RANGE_VAL);
    public static final CommandParam INTEGER_RANGE_POST_VAL = new CommandParam(CommandParamType.INTEGER_RANGE_POST_VAL);
    public static final CommandParam INTEGER_RANGE = new CommandParam(CommandParamType.INTEGER_RANGE);
    public static final CommandParam FULL_INTEGER_RANGE = new CommandParam(CommandParamType.FULL_INTEGER_RANGE);
    public static final CommandParam RATIONAL_RANGE_VAL = new CommandParam(CommandParamType.RATIONAL_RANGE_VAL);
    public static final CommandParam RATIONAL_RANGE_POST_VAL = new CommandParam(CommandParamType.RATIONAL_RANGE_POST_VAL);
    public static final CommandParam RATIONAL_RANGE = new CommandParam(CommandParamType.RATIONAL_RANGE);
    public static final CommandParam FULL_RATIONAL_RANGE = new CommandParam(CommandParamType.FULL_RATIONAL_RANGE);
    public static final CommandParam SEL_ARGS = new CommandParam(CommandParamType.SEL_ARGS);
    public static final CommandParam ARGS = new CommandParam(CommandParamType.ARGS);
    public static final CommandParam ARG = new CommandParam(CommandParamType.ARG);
    public static final CommandParam MARG = new CommandParam(CommandParamType.MARG);
    public static final CommandParam MVALUE = new CommandParam(CommandParamType.MVALUE);
    public static final CommandParam NAME_ARG = new CommandParam(CommandParamType.NAME_ARG);
    public static final CommandParam TYPE_ARG = new CommandParam(CommandParamType.TYPE_ARG);
    public static final CommandParam FAMILY_ARG = new CommandParam(CommandParamType.FAMILY_ARG);
    public static final CommandParam HAS_PERMISSION_ARG = new CommandParam(CommandParamType.HAS_PERMISSION_ARG);
    public static final CommandParam HAS_PERMISSIONS_ARG = new CommandParam(CommandParamType.HAS_PERMISSIONS_ARG);
    public static final CommandParam HAS_PERMISSION_SELECTOR = new CommandParam(CommandParamType.HAS_PERMISSION_SELECTOR);
    public static final CommandParam HAS_PERMISSION_ELEMENT = new CommandParam(CommandParamType.HAS_PERMISSION_ELEMENT);
    public static final CommandParam HAS_PERMISSION_ELEMENTS = new CommandParam(CommandParamType.HAS_PERMISSION_ELEMENTS);
    public static final CommandParam TAG_ARG = new CommandParam(CommandParamType.TAG_ARG);
    public static final CommandParam HAS_ITEM_ELEMENT = new CommandParam(CommandParamType.HAS_ITEM_ELEMENT);
    public static final CommandParam HAS_ITEM_ELEMENTS = new CommandParam(CommandParamType.HAS_ITEM_ELEMENTS);
    public static final CommandParam HAS_ITEM_ARG = new CommandParam(CommandParamType.HAS_ITEM_ARG);
    public static final CommandParam HAS_ITEM_ARGS = new CommandParam(CommandParamType.HAS_ITEM_ARGS);
    public static final CommandParam HAS_ITEM_SELECTOR = new CommandParam(CommandParamType.HAS_ITEM_SELECTOR);
    public static final CommandParam EQUIPMENT_SLOT_ENUM = new CommandParam(CommandParamType.EQUIPMENT_SLOT_ENUM);
    public static final CommandParam PROPERTY_VALUE = new CommandParam(CommandParamType.PROPERTY_VALUE);
    public static final CommandParam HAS_PROPERTY_PARAM_VALUE = new CommandParam(CommandParamType.HAS_PROPERTY_PARAM_VALUE);
    public static final CommandParam HAS_PROPERTY_PARAM_ENUM_VALUE = new CommandParam(CommandParamType.HAS_PROPERTY_PARAM_ENUM_VALUE);
    public static final CommandParam HAS_PROPERTY_ARG = new CommandParam(CommandParamType.HAS_PROPERTY_ARG);
    public static final CommandParam HAS_PROPERTY_ARGS = new CommandParam(CommandParamType.HAS_PROPERTY_ARGS);
    public static final CommandParam HAS_PROPERTY_ELEMENT = new CommandParam(CommandParamType.HAS_PROPERTY_ELEMENT);
    public static final CommandParam HAS_PROPERTY_ELEMENTS = new CommandParam(CommandParamType.HAS_PROPERTY_ELEMENTS);
    public static final CommandParam HAS_PROPERTY_SELECTOR = new CommandParam(CommandParamType.HAS_PROPERTY_SELECTOR);
    public static final CommandParam ID = new CommandParam(CommandParamType.ID);
    public static final CommandParam ID_CONT = new CommandParam(CommandParamType.ID_CONT);
    public static final CommandParam COORD_X_INT = new CommandParam(CommandParamType.COORD_X_INT);
    public static final CommandParam COORD_Y_INT = new CommandParam(CommandParamType.COORD_Y_INT);
    public static final CommandParam COORD_Z_INT = new CommandParam(CommandParamType.COORD_Z_INT);
    public static final CommandParam COORD_X_FLOAT = new CommandParam(CommandParamType.COORD_X_FLOAT);
    public static final CommandParam COORD_Y_FLOAT = new CommandParam(CommandParamType.COORD_Y_FLOAT);
    public static final CommandParam COORD_Z_FLOAT = new CommandParam(CommandParamType.COORD_Z_FLOAT);
    public static final CommandParam POSITION = new CommandParam(CommandParamType.POSITION);
    public static final CommandParam POSITION_FLOAT = new CommandParam(CommandParamType.POSITION_FLOAT);
    public static final CommandParam MESSAGE_EXP = new CommandParam(CommandParamType.MESSAGE_EXP);
    public static final CommandParam MESSAGE = new CommandParam(CommandParamType.MESSAGE);
    public static final CommandParam MESSAGE_ROOT = new CommandParam(CommandParamType.MESSAGE_ROOT);
    public static final CommandParam POST_SELECTOR = new CommandParam(CommandParamType.POST_SELECTOR);
    public static final CommandParam RAW_TEXT = new CommandParam(CommandParamType.RAW_TEXT);
    public static final CommandParam RAW_TEXT_CONT = new CommandParam(CommandParamType.RAW_TEXT_CONT);
    public static final CommandParam JSON_VALUE = new CommandParam(CommandParamType.JSON_VALUE);
    public static final CommandParam JSON_FIELD = new CommandParam(CommandParamType.JSON_FIELD);
    public static final CommandParam JSON_OBJECT = new CommandParam(CommandParamType.JSON_OBJECT);
    public static final CommandParam JSON_OBJECT_FIELDS = new CommandParam(CommandParamType.JSON_OBJECT_FIELDS);
    public static final CommandParam JSON_OBJECT_CONT = new CommandParam(CommandParamType.JSON_OBJECT_CONT);
    public static final CommandParam JSON_ARRAY = new CommandParam(CommandParamType.JSON_ARRAY);
    public static final CommandParam JSON_ARRAY_VALUES = new CommandParam(CommandParamType.JSON_ARRAY_VALUES);
    public static final CommandParam JSON_ARRAY_CONT = new CommandParam(CommandParamType.JSON_ARRAY_CONT);
    public static final CommandParam BLOCK_STATE = new CommandParam(CommandParamType.BLOCK_STATE);
    public static final CommandParam BLOCK_STATE_KEY = new CommandParam(CommandParamType.BLOCK_STATE_KEY);
    public static final CommandParam BLOCK_STATE_VALUE = new CommandParam(CommandParamType.BLOCK_STATE_VALUE);
    public static final CommandParam BLOCK_STATE_VALUES = new CommandParam(CommandParamType.BLOCK_STATE_VALUES);
    public static final CommandParam BLOCK_STATE_ARRAY = new CommandParam(CommandParamType.BLOCK_STATE_ARRAY);
    public static final CommandParam BLOCK_STATE_ARRAY_CONT = new CommandParam(CommandParamType.BLOCK_STATE_ARRAY_CONT);
    public static final CommandParam COMMAND = new CommandParam(CommandParamType.COMMAND);
    public static final CommandParam SLASH_COMMAND = new CommandParam(CommandParamType.SLASH_COMMAND);
    public static final CommandParam CHAINED_COMMAND = new CommandParam(CommandParamType.CHAINED_COMMAND);
    public static final CommandParam CODE_BUILDER_ARG = new CommandParam(CommandParamType.CODE_BUILDER_ARG);
    public static final CommandParam CODE_BUILDER_ARGS = new CommandParam(CommandParamType.CODE_BUILDER_ARGS);
    public static final CommandParam CODE_BUILDER_SELECT_PARAM = new CommandParam(CommandParamType.CODE_BUILDER_SELECT_PARAM);
    public static final CommandParam CODE_BUILDER_SELECTOR = new CommandParam(CommandParamType.CODE_BUILDER_SELECTOR);

    private final CommandParamType paramType;
    private final int defaultValue;

    public CommandParam(CommandParamType paramType) {
        this.paramType = paramType;
        this.defaultValue = -1;
    }

    public CommandParam(int defaultValue) {
        this.defaultValue = defaultValue;
        this.paramType = null;
    }

    public int getValue(TypeMap<CommandParam> typeMap) {
        if (this.defaultValue > 0 || this.paramType == null) {
            return this.defaultValue;
        } else {
            return typeMap.getId(this);
        }
    }

    @Override
    public String toString() {
        return "CommandParam(type=" + (this.paramType == null ? "UNKNOWN" : this.paramType.name()) + ", defaultValue=" + this.defaultValue + ")";
    }
}
