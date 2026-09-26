package org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request;

public enum ItemStackRequestActionType {

    TAKE,
    PLACE,
    SWAP,
    DROP,
    DESTROY,
    CONSUME,
    CREATE,
    SCREEN_LAB_TABLE_COMBINE,
    SCREEN_BEACON_PAYMENT,
    /**
     * @since v428
     */
    SCREEN_HUD_MINE_BLOCK,
    CRAFT_RECIPE,
    CRAFT_RECIPE_AUTO,
    CRAFT_CREATIVE,
    /**
     * @since v422
     */
    CRAFT_RECIPE_OPTIONAL,
    /**
     * @since v471
     */
    CRAFT_REPAIR_AND_DISENCHANT,
    /**
     * @since v471
     */
    CRAFT_LOOM,
    CRAFT_NON_IMPLEMENTED,
    CRAFT_RESULTS,
    /**
     * @deprecated since 712
     */
    PLACE_IN_ITEM_CONTAINER,
    /**
     * @deprecated since 712
     */
    TAKE_FROM_ITEM_CONTAINER
}