package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ContainerEnumName {
  /**
   * AnvilInputContainer
   */
  ANVIL_INPUT_CONTAINER,

  /**
   * AnvilMaterialContainer
   */
  ANVIL_MATERIAL_CONTAINER,

  /**
   * AnvilResultPreviewContainer
   */
  ANVIL_RESULT_PREVIEW_CONTAINER,

  /**
   * SmithingTableInputContainer
   */
  SMITHING_TABLE_INPUT_CONTAINER,

  /**
   * SmithingTableMaterialContainer
   */
  SMITHING_TABLE_MATERIAL_CONTAINER,

  /**
   * SmithingTableResultPreviewContainer
   */
  SMITHING_TABLE_RESULT_PREVIEW_CONTAINER,

  /**
   * ArmorContainer
   */
  ARMOR_CONTAINER,

  /**
   * LevelEntityContainer
   */
  LEVEL_ENTITY_CONTAINER,

  /**
   * BeaconPaymentContainer
   */
  BEACON_PAYMENT_CONTAINER,

  /**
   * BrewingStandInputContainer
   */
  BREWING_STAND_INPUT_CONTAINER,

  /**
   * BrewingStandResultContainer
   */
  BREWING_STAND_RESULT_CONTAINER,

  /**
   * BrewingStandFuelContainer
   */
  BREWING_STAND_FUEL_CONTAINER,

  /**
   * CombinedHotbarAndInventoryContainer
   */
  COMBINED_HOTBAR_AND_INVENTORY_CONTAINER,

  /**
   * CraftingInputContainer
   */
  CRAFTING_INPUT_CONTAINER,

  /**
   * CraftingOutputPreviewContainer
   */
  CRAFTING_OUTPUT_PREVIEW_CONTAINER,

  /**
   * RecipeConstructionContainer
   */
  RECIPE_CONSTRUCTION_CONTAINER,

  /**
   * RecipeNatureContainer
   */
  RECIPE_NATURE_CONTAINER,

  /**
   * RecipeItemsContainer
   */
  RECIPE_ITEMS_CONTAINER,

  /**
   * RecipeFoodContainer
   */
  RECIPE_FOOD_CONTAINER,

  /**
   * RecipeBlocksContainer
   */
  RECIPE_BLOCKS_CONTAINER,

  /**
   * RecipeFurnaceItemsContainer
   */
  RECIPE_FURNACE_ITEMS_CONTAINER,

  /**
   * RecipeSearchContainer
   */
  RECIPE_SEARCH_CONTAINER,

  /**
   * RecipeSearchBarContainer
   */
  RECIPE_SEARCH_BAR_CONTAINER,

  /**
   * RecipeEquipmentContainer
   */
  RECIPE_EQUIPMENT_CONTAINER,

  /**
   * RecipeBookContainer
   */
  RECIPE_BOOK_CONTAINER,

  /**
   * EnchantingInputContainer
   */
  ENCHANTING_INPUT_CONTAINER,

  /**
   * EnchantingMaterialContainer
   */
  ENCHANTING_MATERIAL_CONTAINER,

  /**
   * FurnaceFuelContainer
   */
  FURNACE_FUEL_CONTAINER,

  /**
   * FurnaceIngredientContainer
   */
  FURNACE_INGREDIENT_CONTAINER,

  /**
   * FurnaceResultContainer
   */
  FURNACE_RESULT_CONTAINER,

  /**
   * HorseEquipContainer
   */
  HORSE_EQUIP_CONTAINER,

  /**
   * HotbarContainer
   */
  HOTBAR_CONTAINER,

  /**
   * InventoryContainer
   */
  INVENTORY_CONTAINER,

  /**
   * ShulkerBoxContainer
   */
  SHULKER_BOX_CONTAINER,

  /**
   * TradeIngredient1Container
   */
  TRADE_INGREDIENT1CONTAINER,

  /**
   * TradeIngredient2Container
   */
  TRADE_INGREDIENT2CONTAINER,

  /**
   * TradeResultPreviewContainer
   */
  TRADE_RESULT_PREVIEW_CONTAINER,

  /**
   * OffhandContainer
   */
  OFFHAND_CONTAINER,

  /**
   * CompoundCreatorInput
   */
  COMPOUND_CREATOR_INPUT,

  /**
   * CompoundCreatorOutputPreview
   */
  COMPOUND_CREATOR_OUTPUT_PREVIEW,

  /**
   * ElementConstructorOutputPreview
   */
  ELEMENT_CONSTRUCTOR_OUTPUT_PREVIEW,

  /**
   * MaterialReducerInput
   */
  MATERIAL_REDUCER_INPUT,

  /**
   * MaterialReducerOutput
   */
  MATERIAL_REDUCER_OUTPUT,

  /**
   * LabTableInput
   */
  LAB_TABLE_INPUT,

  /**
   * LoomInputContainer
   */
  LOOM_INPUT_CONTAINER,

  /**
   * LoomDyeContainer
   */
  LOOM_DYE_CONTAINER,

  /**
   * LoomMaterialContainer
   */
  LOOM_MATERIAL_CONTAINER,

  /**
   * LoomResultPreviewContainer
   */
  LOOM_RESULT_PREVIEW_CONTAINER,

  /**
   * BlastFurnaceIngredientContainer
   */
  BLAST_FURNACE_INGREDIENT_CONTAINER,

  /**
   * SmokerIngredientContainer
   */
  SMOKER_INGREDIENT_CONTAINER,

  /**
   * Trade2Ingredient1Container
   */
  TRADE2INGREDIENT1CONTAINER,

  /**
   * Trade2Ingredient2Container
   */
  TRADE2INGREDIENT2CONTAINER,

  /**
   * Trade2ResultPreviewContainer
   */
  TRADE2RESULT_PREVIEW_CONTAINER,

  /**
   * GrindstoneInputContainer
   */
  GRINDSTONE_INPUT_CONTAINER,

  /**
   * GrindstoneAdditionalContainer
   */
  GRINDSTONE_ADDITIONAL_CONTAINER,

  /**
   * GrindstoneResultPreviewContainer
   */
  GRINDSTONE_RESULT_PREVIEW_CONTAINER,

  /**
   * StonecutterInputContainer
   */
  STONECUTTER_INPUT_CONTAINER,

  /**
   * StonecutterResultPreviewContainer
   */
  STONECUTTER_RESULT_PREVIEW_CONTAINER,

  /**
   * CartographyInputContainer
   */
  CARTOGRAPHY_INPUT_CONTAINER,

  /**
   * CartographyAdditionalContainer
   */
  CARTOGRAPHY_ADDITIONAL_CONTAINER,

  /**
   * CartographyResultPreviewContainer
   */
  CARTOGRAPHY_RESULT_PREVIEW_CONTAINER,

  /**
   * BarrelContainer
   */
  BARREL_CONTAINER,

  /**
   * CursorContainer
   */
  CURSOR_CONTAINER,

  /**
   * CreatedOutputContainer
   */
  CREATED_OUTPUT_CONTAINER,

  /**
   * SmithingTableTemplateContainer
   */
  SMITHING_TABLE_TEMPLATE_CONTAINER,

  /**
   * CrafterLevelEntityContainer
   */
  CRAFTER_LEVEL_ENTITY_CONTAINER,

  /**
   * DynamicContainer
   */
  DYNAMIC_CONTAINER;

  private static final ContainerEnumName[] VALUES = values();

  public static ContainerEnumName from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ContainerEnumName ID: " + ordinal);
  }
}
