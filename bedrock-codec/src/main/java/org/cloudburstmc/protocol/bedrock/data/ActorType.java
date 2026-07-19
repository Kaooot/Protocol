package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ActorType {
  /**
   * Undefined
   */
  UNDEFINED,

  /**
   * Mob
   */
  MOB,

  /**
   * PathfinderMob
   */
  PATHFINDER_MOB,

  /**
   * Monster
   */
  MONSTER,

  /**
   * Animal
   */
  ANIMAL,

  /**
   * TamableAnimal
   */
  TAMABLE_ANIMAL,

  /**
   * Ambient
   */
  AMBIENT,

  /**
   * UndeadMonster
   */
  UNDEAD_MONSTER,

  /**
   * ZombieMonster
   */
  ZOMBIE_MONSTER,

  /**
   * Arthropod
   */
  ARTHROPOD,

  /**
   * Minecart
   */
  MINECART,

  /**
   * SkeletonMonster
   */
  SKELETON_MONSTER,

  /**
   * EquineAnimal
   */
  EQUINE_ANIMAL,

  /**
   * Projectile
   */
  PROJECTILE,

  /**
   * AbstractArrow
   */
  ABSTRACT_ARROW,

  /**
   * WaterAnimal
   */
  WATER_ANIMAL,

  /**
   * VillagerBase
   */
  VILLAGER_BASE,

  /**
   * Chicken
   */
  CHICKEN,

  /**
   * Cow
   */
  COW,

  /**
   * Pig
   */
  PIG,

  /**
   * Sheep
   */
  SHEEP,

  /**
   * Wolf
   */
  WOLF,

  /**
   * Villager
   */
  VILLAGER,

  /**
   * MushroomCow
   */
  MUSHROOM_COW,

  /**
   * Squid
   */
  SQUID,

  /**
   * Rabbit
   */
  RABBIT,

  /**
   * Bat
   */
  BAT,

  /**
   * IronGolem
   */
  IRON_GOLEM,

  /**
   * SnowGolem
   */
  SNOW_GOLEM,

  /**
   * Ocelot
   */
  OCELOT,

  /**
   * Horse
   */
  HORSE,

  /**
   * PolarBear
   */
  POLAR_BEAR,

  /**
   * Llama
   */
  LLAMA,

  /**
   * Parrot
   */
  PARROT,

  /**
   * Dolphin
   */
  DOLPHIN,

  /**
   * Donkey
   */
  DONKEY,

  /**
   * Mule
   */
  MULE,

  /**
   * SkeletonHorse
   */
  SKELETON_HORSE,

  /**
   * ZombieHorse
   */
  ZOMBIE_HORSE,

  /**
   * Zombie
   */
  ZOMBIE,

  /**
   * Creeper
   */
  CREEPER,

  /**
   * Skeleton
   */
  SKELETON,

  /**
   * Spider
   */
  SPIDER,

  /**
   * PigZombie
   */
  PIG_ZOMBIE,

  /**
   * Slime
   */
  SLIME,

  /**
   * EnderMan
   */
  ENDER_MAN,

  /**
   * Silverfish
   */
  SILVERFISH,

  /**
   * CaveSpider
   */
  CAVE_SPIDER,

  /**
   * Ghast
   */
  GHAST,

  /**
   * LavaSlime
   */
  LAVA_SLIME,

  /**
   * Blaze
   */
  BLAZE,

  /**
   * ZombieVillager
   */
  ZOMBIE_VILLAGER,

  /**
   * Witch
   */
  WITCH,

  /**
   * Stray
   */
  STRAY,

  /**
   * Husk
   */
  HUSK,

  /**
   * WitherSkeleton
   */
  WITHER_SKELETON,

  /**
   * Guardian
   */
  GUARDIAN,

  /**
   * ElderGuardian
   */
  ELDER_GUARDIAN,

  /**
   * Npc
   */
  NPC,

  /**
   * WitherBoss
   */
  WITHER_BOSS,

  /**
   * Dragon
   */
  DRAGON,

  /**
   * Shulker
   */
  SHULKER,

  /**
   * Endermite
   */
  ENDERMITE,

  /**
   * Agent
   */
  AGENT,

  /**
   * Vindicator
   */
  VINDICATOR,

  /**
   * Phantom
   */
  PHANTOM,

  /**
   * IllagerBeast
   */
  ILLAGER_BEAST,

  /**
   * ArmorStand
   */
  ARMOR_STAND,

  /**
   * TripodCamera
   */
  TRIPOD_CAMERA,

  /**
   * Player
   */
  PLAYER,

  /**
   * ItemEntity
   */
  ITEM_ENTITY,

  /**
   * PrimedTnt
   */
  PRIMED_TNT,

  /**
   * FallingBlock
   */
  FALLING_BLOCK,

  /**
   * MovingBlock
   */
  MOVING_BLOCK,

  /**
   * ExperiencePotion
   */
  EXPERIENCE_POTION,

  /**
   * Experience
   */
  EXPERIENCE,

  /**
   * EyeOfEnder
   */
  EYE_OF_ENDER,

  /**
   * EnderCrystal
   */
  ENDER_CRYSTAL,

  /**
   * FireworksRocket
   */
  FIREWORKS_ROCKET,

  /**
   * Trident
   */
  TRIDENT,

  /**
   * Turtle
   */
  TURTLE,

  /**
   * Cat
   */
  CAT,

  /**
   * ShulkerBullet
   */
  SHULKER_BULLET,

  /**
   * FishingHook
   */
  FISHING_HOOK,

  /**
   * Chalkboard
   */
  CHALKBOARD,

  /**
   * DragonFireball
   */
  DRAGON_FIREBALL,

  /**
   * Arrow
   */
  ARROW,

  /**
   * Snowball
   */
  SNOWBALL,

  /**
   * ThrownEgg
   */
  THROWN_EGG,

  /**
   * Painting
   */
  PAINTING,

  /**
   * LargeFireball
   */
  LARGE_FIREBALL,

  /**
   * ThrownPotion
   */
  THROWN_POTION,

  /**
   * Enderpearl
   */
  ENDERPEARL,

  /**
   * LeashKnot
   */
  LEASH_KNOT,

  /**
   * WitherSkull
   */
  WITHER_SKULL,

  /**
   * BoatRideable
   */
  BOAT_RIDEABLE,

  /**
   * WitherSkullDangerous
   */
  WITHER_SKULL_DANGEROUS,

  /**
   * LightningBolt
   */
  LIGHTNING_BOLT,

  /**
   * SmallFireball
   */
  SMALL_FIREBALL,

  /**
   * AreaEffectCloud
   */
  AREA_EFFECT_CLOUD,

  /**
   * LingeringPotion
   */
  LINGERING_POTION,

  /**
   * LlamaSpit
   */
  LLAMA_SPIT,

  /**
   * EvocationFang
   */
  EVOCATION_FANG,

  /**
   * EvocationIllager
   */
  EVOCATION_ILLAGER,

  /**
   * Vex
   */
  VEX,

  /**
   * MinecartRideable
   */
  MINECART_RIDEABLE,

  /**
   * MinecartHopper
   */
  MINECART_HOPPER,

  /**
   * MinecartTNT
   */
  MINECART_TNT,

  /**
   * MinecartChest
   */
  MINECART_CHEST,

  /**
   * MinecartFurnace
   */
  MINECART_FURNACE,

  /**
   * MinecartCommandBlock
   */
  MINECART_COMMAND_BLOCK,

  /**
   * IceBomb
   */
  ICE_BOMB,

  /**
   * Balloon
   */
  BALLOON,

  /**
   * Pufferfish
   */
  PUFFERFISH,

  /**
   * Salmon
   */
  SALMON,

  /**
   * Drowned
   */
  DROWNED,

  /**
   * Tropicalfish
   */
  TROPICALFISH,

  /**
   * Fish
   */
  FISH,

  /**
   * Panda
   */
  PANDA,

  /**
   * Pillager
   */
  PILLAGER,

  /**
   * VillagerV2
   */
  VILLAGER_V2,

  /**
   * ZombieVillagerV2
   */
  ZOMBIE_VILLAGER_V2,

  /**
   * Shield
   */
  SHIELD,

  /**
   * WanderingTrader
   */
  WANDERING_TRADER,

  /**
   * Lectern
   */
  LECTERN,

  /**
   * ElderGuardianGhost
   */
  ELDER_GUARDIAN_GHOST,

  /**
   * Fox
   */
  FOX,

  /**
   * Bee
   */
  BEE,

  /**
   * Piglin
   */
  PIGLIN,

  /**
   * Hoglin
   */
  HOGLIN,

  /**
   * Strider
   */
  STRIDER,

  /**
   * Zoglin
   */
  ZOGLIN,

  /**
   * PiglinBrute
   */
  PIGLIN_BRUTE,

  /**
   * Goat
   */
  GOAT,

  /**
   * GlowSquid
   */
  GLOW_SQUID,

  /**
   * Axolotl
   */
  AXOLOTL,

  /**
   * Warden
   */
  WARDEN,

  /**
   * Frog
   */
  FROG,

  /**
   * Tadpole
   */
  TADPOLE,

  /**
   * Allay
   */
  ALLAY,

  /**
   * ChestBoatRideable
   */
  CHEST_BOAT_RIDEABLE,

  /**
   * TraderLlama
   */
  TRADER_LLAMA,

  /**
   * Camel
   */
  CAMEL,

  /**
   * Sniffer
   */
  SNIFFER,

  /**
   * Breeze
   */
  BREEZE,

  /**
   * BreezeWindChargeProjectile
   */
  BREEZE_WIND_CHARGE_PROJECTILE,

  /**
   * Armadillo
   */
  ARMADILLO,

  /**
   * WindChargeProjectile
   */
  WIND_CHARGE_PROJECTILE,

  /**
   * Bogged
   */
  BOGGED,

  /**
   * OminousItemSpawner
   */
  OMINOUS_ITEM_SPAWNER,

  /**
   * Creaking
   */
  CREAKING,

  /**
   * HappyGhast
   */
  HAPPY_GHAST,

  /**
   * CopperGolem
   */
  COPPER_GOLEM,

  /**
   * Nautilus
   */
  NAUTILUS,

  /**
   * ZombieNautilus
   */
  ZOMBIE_NAUTILUS,

  /**
   * Parched
   */
  PARCHED,

  /**
   * CamelHusk
   */
  CAMEL_HUSK,

  /**
   * SulfurCube
   */
  SULFUR_CUBE,

  /**
   * Cushion
   */
  CUSHION;

  private static final ActorType[] VALUES = values();

  public static ActorType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ActorType ID: " + ordinal);
  }
}
