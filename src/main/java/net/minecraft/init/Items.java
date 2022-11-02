package net.minecraft.init;

import fz.frazionz.item.ItemUltraBow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmorStand;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEmptyMap;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShears;
import net.minecraft.resources.ResourceLocation;

public class Items
{
    public static final Item AIR;
    public static final Item IRON_SHOVEL;
    public static final Item IRON_PICKAXE;
    public static final Item IRON_AXE;
    public static final Item FLINT_AND_STEEL;
    public static final Item APPLE;
    public static final ItemBow BOW;
    public static final Item ARROW;
    public static final Item SPECTRAL_ARROW;
    public static final Item TIPPED_ARROW;
    public static final Item COAL;
    public static final Item DIAMOND;
    public static final Item IRON_INGOT;
    public static final Item GOLD_INGOT;
    public static final Item IRON_SWORD;
    public static final Item WOODEN_SWORD;
    public static final Item WOODEN_SHOVEL;
    public static final Item WOODEN_PICKAXE;
    public static final Item WOODEN_AXE;
    public static final Item STONE_SWORD;
    public static final Item STONE_SHOVEL;
    public static final Item STONE_PICKAXE;
    public static final Item STONE_AXE;
    public static final Item DIAMOND_SWORD;
    public static final Item DIAMOND_SHOVEL;
    public static final Item DIAMOND_PICKAXE;
    public static final Item DIAMOND_AXE;
    public static final Item STICK;
    public static final Item BOWL;
    public static final Item MUSHROOM_STEW;
    public static final Item GOLDEN_SWORD;
    public static final Item GOLDEN_SHOVEL;
    public static final Item GOLDEN_PICKAXE;
    public static final Item GOLDEN_AXE;
    public static final Item STRING;
    public static final Item FEATHER;
    public static final Item GUNPOWDER;
    public static final Item WOODEN_HOE;
    public static final Item STONE_HOE;
    public static final Item IRON_HOE;
    public static final Item DIAMOND_HOE;
    public static final Item GOLDEN_HOE;
    public static final Item WHEAT_SEEDS;
    public static final Item WHEAT;
    public static final Item BREAD;
    public static final ItemArmor LEATHER_HELMET;
    public static final ItemArmor LEATHER_CHESTPLATE;
    public static final ItemArmor LEATHER_LEGGINGS;
    public static final ItemArmor LEATHER_BOOTS;
    public static final ItemArmor CHAINMAIL_HELMET;
    public static final ItemArmor CHAINMAIL_CHESTPLATE;
    public static final ItemArmor CHAINMAIL_LEGGINGS;
    public static final ItemArmor CHAINMAIL_BOOTS;
    public static final ItemArmor IRON_HELMET;
    public static final ItemArmor IRON_CHESTPLATE;
    public static final ItemArmor IRON_LEGGINGS;
    public static final ItemArmor IRON_BOOTS;
    public static final ItemArmor DIAMOND_HELMET;
    public static final ItemArmor DIAMOND_CHESTPLATE;
    public static final ItemArmor DIAMOND_LEGGINGS;
    public static final ItemArmor DIAMOND_BOOTS;
    public static final ItemArmor GOLDEN_HELMET;
    public static final ItemArmor GOLDEN_CHESTPLATE;
    public static final ItemArmor GOLDEN_LEGGINGS;
    public static final ItemArmor GOLDEN_BOOTS;
    public static final Item FLINT;
    public static final Item PORKCHOP;
    public static final Item COOKED_PORKCHOP;
    public static final Item PAINTING;
    public static final Item GOLDEN_APPLE;
    public static final Item SIGN;
    public static final Item OAK_DOOR;
    public static final Item SPRUCE_DOOR;
    public static final Item BIRCH_DOOR;
    public static final Item JUNGLE_DOOR;
    public static final Item ACACIA_DOOR;
    public static final Item DARK_OAK_DOOR;
    public static final Item BUCKET;
    public static final Item WATER_BUCKET;
    public static final Item LAVA_BUCKET;
    public static final Item MINECART;
    public static final Item SADDLE;
    public static final Item IRON_DOOR;
    public static final Item REDSTONE;
    public static final Item SNOWBALL;
    public static final Item BOAT;
    public static final Item SPRUCE_BOAT;
    public static final Item BIRCH_BOAT;
    public static final Item JUNGLE_BOAT;
    public static final Item ACACIA_BOAT;
    public static final Item DARK_OAK_BOAT;
    public static final Item LEATHER;
    public static final Item MILK_BUCKET;
    public static final Item BRICK;
    public static final Item CLAY_BALL;
    public static final Item REEDS;
    public static final Item PAPER;
    public static final Item BOOK;
    public static final Item SLIME_BALL;
    public static final Item CHEST_MINECART;
    public static final Item FURNACE_MINECART;
    public static final Item EGG;
    public static final Item COMPASS;
    public static final ItemFishingRod FISHING_ROD;
    public static final Item CLOCK;
    public static final Item GLOWSTONE_DUST;
    public static final Item FISH;
    public static final Item COOKED_FISH;
    public static final Item DYE;
    public static final Item BONE;
    public static final Item SUGAR;
    public static final Item CAKE;
    public static final Item BED;
    public static final Item REPEATER;
    public static final Item COOKIE;
    public static final ItemMap FILLED_MAP;
    public static final ItemShears SHEARS;
    public static final Item MELON;
    public static final Item PUMPKIN_SEEDS;
    public static final Item MELON_SEEDS;
    public static final Item BEEF;
    public static final Item COOKED_BEEF;
    public static final Item CHICKEN;
    public static final Item COOKED_CHICKEN;
    public static final Item MUTTON;
    public static final Item COOKED_MUTTON;
    public static final Item RABBIT;
    public static final Item COOKED_RABBIT;
    public static final Item RABBIT_STEW;
    public static final Item RABBIT_FOOT;
    public static final Item RABBIT_HIDE;
    public static final Item ROTTEN_FLESH;
    public static final Item ENDER_PEARL;
    public static final Item BLAZE_ROD;
    public static final Item GHAST_TEAR;
    public static final Item GOLD_NUGGET;
    public static final Item NETHER_WART;
    public static final ItemPotion POTIONITEM;
    public static final ItemPotion SPLASH_POTION;
    public static final ItemPotion LINGERING_POTION;
    public static final Item GLASS_BOTTLE;
    public static final Item DRAGON_BREATH;
    public static final Item SPIDER_EYE;
    public static final Item FERMENTED_SPIDER_EYE;
    public static final Item BLAZE_POWDER;
    public static final Item MAGMA_CREAM;
    public static final Item BREWING_STAND;
    public static final Item CAULDRON;
    public static final Item ENDER_EYE;
    public static final Item SPECKLED_MELON;
    public static final Item SPAWN_EGG;
    public static final Item EXPERIENCE_BOTTLE;
    public static final Item FIRE_CHARGE;
    public static final Item WRITABLE_BOOK;
    public static final Item WRITTEN_BOOK;
    public static final Item EMERALD;
    public static final Item ITEM_FRAME;
    public static final Item FLOWER_POT;
    public static final Item CARROT;
    public static final Item POTATO;
    public static final Item BAKED_POTATO;
    public static final Item POISONOUS_POTATO;
    public static final ItemEmptyMap MAP;
    public static final Item GOLDEN_CARROT;
    public static final Item SKULL;
    public static final Item CARROT_ON_A_STICK;
    public static final Item NETHER_STAR;
    public static final Item PUMPKIN_PIE;
    public static final Item FIREWORKS;
    public static final Item FIREWORK_CHARGE;
    public static final Item ENCHANTED_BOOK;
    public static final Item COMPARATOR;
    public static final Item NETHERBRICK;
    public static final Item QUARTZ;
    public static final Item TNT_MINECART;
    public static final Item HOPPER_MINECART;
    public static final ItemArmorStand ARMOR_STAND;
    public static final Item IRON_HORSE_ARMOR;
    public static final Item GOLDEN_HORSE_ARMOR;
    public static final Item DIAMOND_HORSE_ARMOR;
    public static final Item LEAD;
    public static final Item NAME_TAG;
    public static final Item COMMAND_BLOCK_MINECART;
    public static final Item RECORD_13;
    public static final Item RECORD_CAT;
    public static final Item RECORD_BLOCKS;
    public static final Item RECORD_CHIRP;
    public static final Item RECORD_FAR;
    public static final Item RECORD_MALL;
    public static final Item RECORD_MELLOHI;
    public static final Item RECORD_STAL;
    public static final Item RECORD_STRAD;
    public static final Item RECORD_WARD;
    public static final Item RECORD_11;
    public static final Item RECORD_WAIT;
    public static final Item PRISMARINE_SHARD;
    public static final Item PRISMARINE_CRYSTALS;
    public static final Item BANNER;
    public static final Item END_CRYSTAL;
    public static final Item SHIELD;
    public static final Item ELYTRA;
    public static final Item CHORUS_FRUIT;
    public static final Item CHORUS_FRUIT_POPPED;
    public static final Item BEETROOT_SEEDS;
    public static final Item BEETROOT;
    public static final Item BEETROOT_SOUP;
    public static final Item TOTEM_OF_UNDYING;
    public static final Item SHULKER_SHELL;
    public static final Item IRON_NUGGET;
    public static final Item KNOWLEDGE_BOOK;
    
    public static final Item YELLITE;
    public static final ItemArmor YELLITE_HELMET;
    public static final ItemArmor YELLITE_CHESTPLATE;
    public static final ItemArmor YELLITE_LEGGINGS;
    public static final ItemArmor YELLITE_BOOTS;
    public static final Item YELLITE_SWORD;
    public static final Item YELLITE_SHOVEL;
    public static final Item YELLITE_PICKAXE;
    public static final Item YELLITE_AXE;
    public static final Item YELLITE_HOE;   
    public static final Item YELLITE_MULTITOOL;
    public static final Item YELLITE_APPLE;
    public static final Item YELLITE_STICK;
    
    public static final Item BAUXITE;
    public static final ItemArmor BAUXITE_HELMET;
    public static final ItemArmor BAUXITE_CHESTPLATE;
    public static final ItemArmor BAUXITE_LEGGINGS;
    public static final ItemArmor BAUXITE_BOOTS;
    public static final Item BAUXITE_SWORD;
    public static final Item BAUXITE_SHOVEL;
    public static final Item BAUXITE_PICKAXE;
    public static final Item BAUXITE_AXE;
    public static final Item BAUXITE_HOE;
    public static final Item BAUXITE_MULTITOOL;
    public static final Item BAUXITE_APPLE;
    public static final Item BAUXITE_STICK;

    public static final Item ONYX;
    public static final ItemArmor ONYX_HELMET;
    public static final ItemArmor ONYX_CHESTPLATE;
    public static final ItemArmor ONYX_LEGGINGS;
    public static final ItemArmor ONYX_BOOTS;
    public static final Item ONYX_SWORD;
    public static final Item ONYX_SHOVEL;
    public static final Item ONYX_PICKAXE;
    public static final Item ONYX_AXE;
    public static final Item ONYX_HOE;
    public static final Item ONYX_MULTITOOL;
    public static final Item ONYX_APPLE;
    public static final Item ONYX_STICK;

    public static final Item FRAZION;
    public static final Item FRAZION_POWDER;
    public static final ItemArmor FRAZION_HELMET;
    public static final ItemArmor FRAZION_CHESTPLATE;
    public static final ItemArmor FRAZION_LEGGINGS;
    public static final ItemArmor FRAZION_BOOTS;
    public static final Item FRAZION_SWORD;
    public static final Item FRAZION_DAGGER;
    public static final Item FRAZION_SHOVEL;
    public static final Item FRAZION_PICKAXE;
    public static final Item FRAZION_AXE;
    public static final Item FRAZION_HOE;
    public static final Item FRAZION_HAMMER;
    public static final Item FRAZION_MULTITOOL;
    public static final Item FRAZION_APPLE;
    public static final Item FRAZION_STICK;
    
    public static final ItemArmor FRAZION_HELMET_70;
    public static final ItemArmor FRAZION_CHESTPLATE_70;
    public static final ItemArmor FRAZION_LEGGINGS_70;
    public static final ItemArmor FRAZION_BOOTS_70;
    
    public static final ItemArmor FRAZION_HELMET_100;
    public static final ItemArmor FRAZION_CHESTPLATE_100;
    public static final ItemArmor FRAZION_LEGGINGS_100;
    public static final ItemArmor FRAZION_BOOTS_100;

    public static final Item COSMIC_POWDER;
    public static final Item COSMIC_NUGGET;
    public static final Item COSMIC_INGOT;
    
    public static final Item LEGENDARY_AXE;
    public static final Item LEGENDARY_SWORD;
    public static final Item LEGENDARY_DAGGER;
    public static final Item LEGENDARY_SCYTHE;
    
    public static final ItemUltraBow ULTRA_BOW;

    public static final Item TROPHY_BAT;
    public static final Item TROPHY_BLAZE;
    public static final Item TROPHY_CREEPER;
    public static final Item TROPHY_ENDERMAN;
    public static final Item TRPOHY_GHAST;
    public static final Item TROPHY_GUARDIAN;
    public static final Item TROPHY_VILLAGER;
    public static final Item TROPHY_SHULKER;
    public static final Item TROPHY_SPIDER;
    public static final Item TROPHY_SKELETON;
    public static final Item TROPHY_SLIME;
    public static final Item TRPOHY_SQUID;
    public static final Item TROPHY_PIG;
    public static final Item TROPHY_SHEEP;
    public static final Item TROPHY_SILVERFISH;
    public static final Item TROPHY_IRON_GOLEM;

    public static final Item RECORD_FZ_1;
    public static final Item RECORD_FZ_2;
    public static final Item RECORD_FZ_3;
    public static final Item RECORD_FZ_4;
    public static final Item RECORD_FZ_5;
    public static final Item RECORD_FZ_6;
    public static final Item RECORD_FZ_7;
    public static final Item RECORD_FZ_8;
    public static final Item RECORD_FZ_9;
    public static final Item RECORD_FZ_10;

    public static final Item FARM_KEY;
    public static final Item VOTE_KEY;
    public static final Item COMMON_KEY;
    public static final Item RARE_KEY;
    public static final Item LEGENDARY_KEY;
    
    public static final Item STRAWBERRY;
    public static final Item BANANA;
    public static final Item PIZZA;
    public static final Item DONUTS;
    
    public static final Item NETHER_STRING;
    public static final Item RENFORCED_STRING;
    
    public static final Item RUNE_ANTI_MALUS;
    public static final Item RUNE_BONUS;
    public static final Item RUNE_CHANCE;
    public static final Item RUNE_DAMAGE;
    public static final Item RUNE_HEALTH;
    public static final Item RUNE_MINING;
    public static final Item RUNE_REGENERATION;
    public static final Item RUNE_RESISTANCE;
    public static final Item RUNE_SPEED;

    public static final Item BIG_XP;
    
    public static final Item LOOT_POWDER;
    public static final Item FARM_POWDER;
    public static final Item FARM_NUGGET;
    
    public static final ItemArmor TRAVELERS_HELMET;
    public static final ItemArmor TRAVELERS_CHESTPLATE;
    public static final ItemArmor TRAVELERS_LEGGINGS;
    public static final ItemArmor TRAVELERS_BOOTS;
    
    public static final Item DYNAMITE;
    public static final Item DYNAMITE_ARROW;
    
    public static final Item SPAWNER_PICKAXE;
    public static final Item FARM_SWORD;
    
    public static final Item BILLET;

    public static final Item YELLITE_BREWING_STAND;
    public static final Item BAUXITE_BREWING_STAND;
    public static final Item ONYX_BREWING_STAND;
    public static final Item FRAZION_BREWING_STAND;
    
    public static final Item OBSIDIAN_TOWER;
    
    public static final Item BOOSTER_XP;
    public static final Item BOOSTER_APTITUDE;
    public static final Item BOOSTER_REPAIR;
    
    public static final Item WITHERED_BONE;
    public static final Item WITHERED_BONE_MEAL;
    
    public static final Item BOTTLEXP;
    public static final Item FACTION_TOKEN;
    public static final Item IRON_TOOTH;

    private static Item getRegisteredItem(String name)
    {
        Item item = Item.REGISTRY.getObject(new ResourceLocation(name));

        if (item == null)
        {
            throw new IllegalStateException("Invalid Item requested: " + name);
        }
        else
        {
            return item;
        }
    }

    static
    {
        if (!Bootstrap.isRegistered())
        {
            throw new RuntimeException("Accessed Items before Bootstrap!");
        }
        else
        {
            AIR = getRegisteredItem("air");
            IRON_SHOVEL = getRegisteredItem("iron_shovel");
            IRON_PICKAXE = getRegisteredItem("iron_pickaxe");
            IRON_AXE = getRegisteredItem("iron_axe");
            FLINT_AND_STEEL = getRegisteredItem("flint_and_steel");
            APPLE = getRegisteredItem("apple");
            BOW = (ItemBow)getRegisteredItem("bow");
            ARROW = getRegisteredItem("arrow");
            SPECTRAL_ARROW = getRegisteredItem("spectral_arrow");
            TIPPED_ARROW = getRegisteredItem("tipped_arrow");
            COAL = getRegisteredItem("coal");
            DIAMOND = getRegisteredItem("diamond");
            IRON_INGOT = getRegisteredItem("iron_ingot");
            GOLD_INGOT = getRegisteredItem("gold_ingot");
            IRON_SWORD = getRegisteredItem("iron_sword");
            WOODEN_SWORD = getRegisteredItem("wooden_sword");
            WOODEN_SHOVEL = getRegisteredItem("wooden_shovel");
            WOODEN_PICKAXE = getRegisteredItem("wooden_pickaxe");
            WOODEN_AXE = getRegisteredItem("wooden_axe");
            STONE_SWORD = getRegisteredItem("stone_sword");
            STONE_SHOVEL = getRegisteredItem("stone_shovel");
            STONE_PICKAXE = getRegisteredItem("stone_pickaxe");
            STONE_AXE = getRegisteredItem("stone_axe");
            DIAMOND_SWORD = getRegisteredItem("diamond_sword");
            DIAMOND_SHOVEL = getRegisteredItem("diamond_shovel");
            DIAMOND_PICKAXE = getRegisteredItem("diamond_pickaxe");
            DIAMOND_AXE = getRegisteredItem("diamond_axe");
            STICK = getRegisteredItem("stick");
            BOWL = getRegisteredItem("bowl");
            MUSHROOM_STEW = getRegisteredItem("mushroom_stew");
            GOLDEN_SWORD = getRegisteredItem("golden_sword");
            GOLDEN_SHOVEL = getRegisteredItem("golden_shovel");
            GOLDEN_PICKAXE = getRegisteredItem("golden_pickaxe");
            GOLDEN_AXE = getRegisteredItem("golden_axe");
            STRING = getRegisteredItem("string");
            FEATHER = getRegisteredItem("feather");
            GUNPOWDER = getRegisteredItem("gunpowder");
            WOODEN_HOE = getRegisteredItem("wooden_hoe");
            STONE_HOE = getRegisteredItem("stone_hoe");
            IRON_HOE = getRegisteredItem("iron_hoe");
            DIAMOND_HOE = getRegisteredItem("diamond_hoe");
            GOLDEN_HOE = getRegisteredItem("golden_hoe");
            WHEAT_SEEDS = getRegisteredItem("wheat_seeds");
            WHEAT = getRegisteredItem("wheat");
            BREAD = getRegisteredItem("bread");
            LEATHER_HELMET = (ItemArmor)getRegisteredItem("leather_helmet");
            LEATHER_CHESTPLATE = (ItemArmor)getRegisteredItem("leather_chestplate");
            LEATHER_LEGGINGS = (ItemArmor)getRegisteredItem("leather_leggings");
            LEATHER_BOOTS = (ItemArmor)getRegisteredItem("leather_boots");
            CHAINMAIL_HELMET = (ItemArmor)getRegisteredItem("chainmail_helmet");
            CHAINMAIL_CHESTPLATE = (ItemArmor)getRegisteredItem("chainmail_chestplate");
            CHAINMAIL_LEGGINGS = (ItemArmor)getRegisteredItem("chainmail_leggings");
            CHAINMAIL_BOOTS = (ItemArmor)getRegisteredItem("chainmail_boots");
            IRON_HELMET = (ItemArmor)getRegisteredItem("iron_helmet");
            IRON_CHESTPLATE = (ItemArmor)getRegisteredItem("iron_chestplate");
            IRON_LEGGINGS = (ItemArmor)getRegisteredItem("iron_leggings");
            IRON_BOOTS = (ItemArmor)getRegisteredItem("iron_boots");
            DIAMOND_HELMET = (ItemArmor)getRegisteredItem("diamond_helmet");
            DIAMOND_CHESTPLATE = (ItemArmor)getRegisteredItem("diamond_chestplate");
            DIAMOND_LEGGINGS = (ItemArmor)getRegisteredItem("diamond_leggings");
            DIAMOND_BOOTS = (ItemArmor)getRegisteredItem("diamond_boots");
            GOLDEN_HELMET = (ItemArmor)getRegisteredItem("golden_helmet");
            GOLDEN_CHESTPLATE = (ItemArmor)getRegisteredItem("golden_chestplate");
            GOLDEN_LEGGINGS = (ItemArmor)getRegisteredItem("golden_leggings");
            GOLDEN_BOOTS = (ItemArmor)getRegisteredItem("golden_boots");
            FLINT = getRegisteredItem("flint");
            PORKCHOP = getRegisteredItem("porkchop");
            COOKED_PORKCHOP = getRegisteredItem("cooked_porkchop");
            PAINTING = getRegisteredItem("painting");
            GOLDEN_APPLE = getRegisteredItem("golden_apple");
            SIGN = getRegisteredItem("sign");
            OAK_DOOR = getRegisteredItem("wooden_door");
            SPRUCE_DOOR = getRegisteredItem("spruce_door");
            BIRCH_DOOR = getRegisteredItem("birch_door");
            JUNGLE_DOOR = getRegisteredItem("jungle_door");
            ACACIA_DOOR = getRegisteredItem("acacia_door");
            DARK_OAK_DOOR = getRegisteredItem("dark_oak_door");
            BUCKET = getRegisteredItem("bucket");
            WATER_BUCKET = getRegisteredItem("water_bucket");
            LAVA_BUCKET = getRegisteredItem("lava_bucket");
            MINECART = getRegisteredItem("minecart");
            SADDLE = getRegisteredItem("saddle");
            IRON_DOOR = getRegisteredItem("iron_door");
            REDSTONE = getRegisteredItem("redstone");
            SNOWBALL = getRegisteredItem("snowball");
            BOAT = getRegisteredItem("boat");
            SPRUCE_BOAT = getRegisteredItem("spruce_boat");
            BIRCH_BOAT = getRegisteredItem("birch_boat");
            JUNGLE_BOAT = getRegisteredItem("jungle_boat");
            ACACIA_BOAT = getRegisteredItem("acacia_boat");
            DARK_OAK_BOAT = getRegisteredItem("dark_oak_boat");
            LEATHER = getRegisteredItem("leather");
            MILK_BUCKET = getRegisteredItem("milk_bucket");
            BRICK = getRegisteredItem("brick");
            CLAY_BALL = getRegisteredItem("clay_ball");
            REEDS = getRegisteredItem("reeds");
            PAPER = getRegisteredItem("paper");
            BOOK = getRegisteredItem("book");
            SLIME_BALL = getRegisteredItem("slime_ball");
            CHEST_MINECART = getRegisteredItem("chest_minecart");
            FURNACE_MINECART = getRegisteredItem("furnace_minecart");
            EGG = getRegisteredItem("egg");
            COMPASS = getRegisteredItem("compass");
            FISHING_ROD = (ItemFishingRod)getRegisteredItem("fishing_rod");
            CLOCK = getRegisteredItem("clock");
            GLOWSTONE_DUST = getRegisteredItem("glowstone_dust");
            FISH = getRegisteredItem("fish");
            COOKED_FISH = getRegisteredItem("cooked_fish");
            DYE = getRegisteredItem("dye");
            BONE = getRegisteredItem("bone");
            SUGAR = getRegisteredItem("sugar");
            CAKE = getRegisteredItem("cake");
            BED = getRegisteredItem("bed");
            REPEATER = getRegisteredItem("repeater");
            COOKIE = getRegisteredItem("cookie");
            FILLED_MAP = (ItemMap)getRegisteredItem("filled_map");
            SHEARS = (ItemShears)getRegisteredItem("shears");
            MELON = getRegisteredItem("melon");
            PUMPKIN_SEEDS = getRegisteredItem("pumpkin_seeds");
            MELON_SEEDS = getRegisteredItem("melon_seeds");
            BEEF = getRegisteredItem("beef");
            COOKED_BEEF = getRegisteredItem("cooked_beef");
            CHICKEN = getRegisteredItem("chicken");
            COOKED_CHICKEN = getRegisteredItem("cooked_chicken");
            MUTTON = getRegisteredItem("mutton");
            COOKED_MUTTON = getRegisteredItem("cooked_mutton");
            RABBIT = getRegisteredItem("rabbit");
            COOKED_RABBIT = getRegisteredItem("cooked_rabbit");
            RABBIT_STEW = getRegisteredItem("rabbit_stew");
            RABBIT_FOOT = getRegisteredItem("rabbit_foot");
            RABBIT_HIDE = getRegisteredItem("rabbit_hide");
            ROTTEN_FLESH = getRegisteredItem("rotten_flesh");
            ENDER_PEARL = getRegisteredItem("ender_pearl");
            BLAZE_ROD = getRegisteredItem("blaze_rod");
            GHAST_TEAR = getRegisteredItem("ghast_tear");
            GOLD_NUGGET = getRegisteredItem("gold_nugget");
            NETHER_WART = getRegisteredItem("nether_wart");
            POTIONITEM = (ItemPotion)getRegisteredItem("potion");
            SPLASH_POTION = (ItemPotion)getRegisteredItem("splash_potion");
            LINGERING_POTION = (ItemPotion)getRegisteredItem("lingering_potion");
            GLASS_BOTTLE = getRegisteredItem("glass_bottle");
            DRAGON_BREATH = getRegisteredItem("dragon_breath");
            SPIDER_EYE = getRegisteredItem("spider_eye");
            FERMENTED_SPIDER_EYE = getRegisteredItem("fermented_spider_eye");
            BLAZE_POWDER = getRegisteredItem("blaze_powder");
            MAGMA_CREAM = getRegisteredItem("magma_cream");
            BREWING_STAND = getRegisteredItem("brewing_stand");
            CAULDRON = getRegisteredItem("cauldron");
            ENDER_EYE = getRegisteredItem("ender_eye");
            SPECKLED_MELON = getRegisteredItem("speckled_melon");
            SPAWN_EGG = getRegisteredItem("spawn_egg");
            EXPERIENCE_BOTTLE = getRegisteredItem("experience_bottle");
            FIRE_CHARGE = getRegisteredItem("fire_charge");
            WRITABLE_BOOK = getRegisteredItem("writable_book");
            WRITTEN_BOOK = getRegisteredItem("written_book");
            EMERALD = getRegisteredItem("emerald");
            ITEM_FRAME = getRegisteredItem("item_frame");
            FLOWER_POT = getRegisteredItem("flower_pot");
            CARROT = getRegisteredItem("carrot");
            POTATO = getRegisteredItem("potato");
            BAKED_POTATO = getRegisteredItem("baked_potato");
            POISONOUS_POTATO = getRegisteredItem("poisonous_potato");
            MAP = (ItemEmptyMap)getRegisteredItem("map");
            GOLDEN_CARROT = getRegisteredItem("golden_carrot");
            SKULL = getRegisteredItem("skull");
            CARROT_ON_A_STICK = getRegisteredItem("carrot_on_a_stick");
            NETHER_STAR = getRegisteredItem("nether_star");
            PUMPKIN_PIE = getRegisteredItem("pumpkin_pie");
            FIREWORKS = getRegisteredItem("fireworks");
            FIREWORK_CHARGE = getRegisteredItem("firework_charge");
            ENCHANTED_BOOK = getRegisteredItem("enchanted_book");
            COMPARATOR = getRegisteredItem("comparator");
            NETHERBRICK = getRegisteredItem("netherbrick");
            QUARTZ = getRegisteredItem("quartz");
            TNT_MINECART = getRegisteredItem("tnt_minecart");
            HOPPER_MINECART = getRegisteredItem("hopper_minecart");
            ARMOR_STAND = (ItemArmorStand)getRegisteredItem("armor_stand");
            IRON_HORSE_ARMOR = getRegisteredItem("iron_horse_armor");
            GOLDEN_HORSE_ARMOR = getRegisteredItem("golden_horse_armor");
            DIAMOND_HORSE_ARMOR = getRegisteredItem("diamond_horse_armor");
            LEAD = getRegisteredItem("lead");
            NAME_TAG = getRegisteredItem("name_tag");
            COMMAND_BLOCK_MINECART = getRegisteredItem("command_block_minecart");
            RECORD_13 = getRegisteredItem("record_13");
            RECORD_CAT = getRegisteredItem("record_cat");
            RECORD_BLOCKS = getRegisteredItem("record_blocks");
            RECORD_CHIRP = getRegisteredItem("record_chirp");
            RECORD_FAR = getRegisteredItem("record_far");
            RECORD_MALL = getRegisteredItem("record_mall");
            RECORD_MELLOHI = getRegisteredItem("record_mellohi");
            RECORD_STAL = getRegisteredItem("record_stal");
            RECORD_STRAD = getRegisteredItem("record_strad");
            RECORD_WARD = getRegisteredItem("record_ward");
            RECORD_11 = getRegisteredItem("record_11");
            RECORD_WAIT = getRegisteredItem("record_wait");
            PRISMARINE_SHARD = getRegisteredItem("prismarine_shard");
            PRISMARINE_CRYSTALS = getRegisteredItem("prismarine_crystals");
            BANNER = getRegisteredItem("banner");
            END_CRYSTAL = getRegisteredItem("end_crystal");
            SHIELD = getRegisteredItem("shield");
            ELYTRA = getRegisteredItem("elytra");
            CHORUS_FRUIT = getRegisteredItem("chorus_fruit");
            CHORUS_FRUIT_POPPED = getRegisteredItem("chorus_fruit_popped");
            BEETROOT_SEEDS = getRegisteredItem("beetroot_seeds");
            BEETROOT = getRegisteredItem("beetroot");
            BEETROOT_SOUP = getRegisteredItem("beetroot_soup");
            TOTEM_OF_UNDYING = getRegisteredItem("totem_of_undying");
            SHULKER_SHELL = getRegisteredItem("shulker_shell");
            IRON_NUGGET = getRegisteredItem("iron_nugget");
            KNOWLEDGE_BOOK = getRegisteredItem("knowledge_book");
        
            YELLITE = getRegisteredItem("frazionz:yellite");
            YELLITE_HELMET = (ItemArmor)getRegisteredItem("frazionz:yellite_helmet");
            YELLITE_CHESTPLATE = (ItemArmor)getRegisteredItem("frazionz:yellite_chestplate");
            YELLITE_LEGGINGS = (ItemArmor)getRegisteredItem("frazionz:yellite_leggings");
            YELLITE_BOOTS = (ItemArmor)getRegisteredItem("frazionz:yellite_boots");
            YELLITE_SWORD = getRegisteredItem("frazionz:yellite_sword");
            YELLITE_SHOVEL = getRegisteredItem("frazionz:yellite_shovel");
            YELLITE_PICKAXE = getRegisteredItem("frazionz:yellite_pickaxe");
            YELLITE_AXE = getRegisteredItem("frazionz:yellite_axe");
            YELLITE_HOE = getRegisteredItem("frazionz:yellite_hoe");
            YELLITE_MULTITOOL = getRegisteredItem("frazionz:yellite_multitool");
            YELLITE_APPLE = getRegisteredItem("frazionz:yellite_apple");
            YELLITE_STICK = getRegisteredItem("frazionz:yellite_stick");
            
            BAUXITE = getRegisteredItem("frazionz:bauxite");
            BAUXITE_HELMET = (ItemArmor)getRegisteredItem("frazionz:bauxite_helmet");
            BAUXITE_CHESTPLATE = (ItemArmor)getRegisteredItem("frazionz:bauxite_chestplate");
            BAUXITE_LEGGINGS = (ItemArmor)getRegisteredItem("frazionz:bauxite_leggings");
            BAUXITE_BOOTS = (ItemArmor)getRegisteredItem("frazionz:bauxite_boots");
            BAUXITE_SWORD = getRegisteredItem("frazionz:bauxite_sword");
            BAUXITE_SHOVEL = getRegisteredItem("frazionz:bauxite_shovel");
            BAUXITE_PICKAXE = getRegisteredItem("frazionz:bauxite_pickaxe");
            BAUXITE_AXE = getRegisteredItem("frazionz:bauxite_axe");
            BAUXITE_HOE = getRegisteredItem("frazionz:bauxite_hoe");
            BAUXITE_MULTITOOL = getRegisteredItem("frazionz:bauxite_multitool");
            BAUXITE_APPLE = getRegisteredItem("frazionz:bauxite_apple");
            BAUXITE_STICK = getRegisteredItem("frazionz:bauxite_stick");
            
            ONYX = getRegisteredItem("frazionz:onyx");
            ONYX_HELMET = (ItemArmor)getRegisteredItem("frazionz:onyx_helmet");
            ONYX_CHESTPLATE = (ItemArmor)getRegisteredItem("frazionz:onyx_chestplate");
            ONYX_LEGGINGS = (ItemArmor)getRegisteredItem("frazionz:onyx_leggings");
            ONYX_BOOTS = (ItemArmor)getRegisteredItem("frazionz:onyx_boots");
            ONYX_SWORD = getRegisteredItem("frazionz:onyx_sword");
            ONYX_SHOVEL = getRegisteredItem("frazionz:onyx_shovel");
            ONYX_PICKAXE = getRegisteredItem("frazionz:onyx_pickaxe");
            ONYX_AXE = getRegisteredItem("frazionz:onyx_axe");
            ONYX_HOE = getRegisteredItem("frazionz:onyx_hoe");
            ONYX_MULTITOOL = getRegisteredItem("frazionz:onyx_multitool");
            ONYX_APPLE = getRegisteredItem("frazionz:onyx_apple");
            ONYX_STICK = getRegisteredItem("frazionz:onyx_stick");
            
            FRAZION = getRegisteredItem("frazionz:frazion");
            FRAZION_POWDER = getRegisteredItem("frazionz:frazion_powder");
            FRAZION_HELMET = (ItemArmor)getRegisteredItem("frazionz:frazion_helmet");
            FRAZION_CHESTPLATE = (ItemArmor)getRegisteredItem("frazionz:frazion_chestplate");
            FRAZION_LEGGINGS = (ItemArmor)getRegisteredItem("frazionz:frazion_leggings");
            FRAZION_BOOTS = (ItemArmor)getRegisteredItem("frazionz:frazion_boots");
            FRAZION_SWORD = getRegisteredItem("frazionz:frazion_sword");
            FRAZION_SHOVEL = getRegisteredItem("frazionz:frazion_shovel");
            FRAZION_PICKAXE = getRegisteredItem("frazionz:frazion_pickaxe");
            FRAZION_AXE = getRegisteredItem("frazionz:frazion_axe");
            FRAZION_HOE = getRegisteredItem("frazionz:frazion_hoe");
            FRAZION_MULTITOOL = getRegisteredItem("frazionz:frazion_multitool");
            FRAZION_HAMMER = getRegisteredItem("frazionz:frazion_hammer");
            FRAZION_APPLE = getRegisteredItem("frazionz:frazion_apple");
            FRAZION_STICK = getRegisteredItem("frazionz:frazion_stick");
            
            FRAZION_HELMET_70 = (ItemArmor)getRegisteredItem("frazionz:frazion_helmet_70");
            FRAZION_CHESTPLATE_70 = (ItemArmor)getRegisteredItem("frazionz:frazion_chestplate_70");
            FRAZION_LEGGINGS_70 = (ItemArmor)getRegisteredItem("frazionz:frazion_leggings_70");
            FRAZION_BOOTS_70 = (ItemArmor)getRegisteredItem("frazionz:frazion_boots_70");
            
            FRAZION_HELMET_100 = (ItemArmor)getRegisteredItem("frazionz:frazion_helmet_100");
            FRAZION_CHESTPLATE_100 = (ItemArmor)getRegisteredItem("frazionz:frazion_chestplate_100");
            FRAZION_LEGGINGS_100 = (ItemArmor)getRegisteredItem("frazionz:frazion_leggings_100");
            FRAZION_BOOTS_100 = (ItemArmor)getRegisteredItem("frazionz:frazion_boots_100");
            
            COSMIC_POWDER = getRegisteredItem("frazionz:cosmic_powder");
            COSMIC_NUGGET = getRegisteredItem("frazionz:cosmic_nugget");
            COSMIC_INGOT = getRegisteredItem("frazionz:cosmic_ingot");
            LEGENDARY_AXE = getRegisteredItem("frazionz:legendary_axe");
            LEGENDARY_SWORD = getRegisteredItem("frazionz:legendary_sword");
            LEGENDARY_DAGGER = getRegisteredItem("frazionz:legendary_dagger");
            LEGENDARY_SCYTHE = getRegisteredItem("frazionz:legendary_scythe");
            
            ULTRA_BOW = (ItemUltraBow)getRegisteredItem("frazionz:ultra_bow");
            
            TROPHY_BAT = getRegisteredItem("frazionz:trophy_bat");
            TROPHY_BLAZE = getRegisteredItem("frazionz:trophy_blaze");
            TROPHY_CREEPER = getRegisteredItem("frazionz:trophy_creeper");
            TROPHY_ENDERMAN = getRegisteredItem("frazionz:trophy_enderman");
            TRPOHY_GHAST = getRegisteredItem("frazionz:trophy_ghast");
            TROPHY_GUARDIAN = getRegisteredItem("frazionz:trophy_guardian");
            TROPHY_VILLAGER = getRegisteredItem("frazionz:trophy_villager");
            TROPHY_SHULKER = getRegisteredItem("frazionz:trophy_shulker");
            TROPHY_SPIDER = getRegisteredItem("frazionz:trophy_spider");
            TROPHY_SKELETON = getRegisteredItem("frazionz:trophy_skeleton");
            TROPHY_SLIME = getRegisteredItem("frazionz:trophy_slime");
            TRPOHY_SQUID = getRegisteredItem("frazionz:trophy_squid");
            TROPHY_PIG = getRegisteredItem("frazionz:trophy_pig");
            TROPHY_SHEEP = getRegisteredItem("frazionz:trophy_sheep");
            TROPHY_SILVERFISH = getRegisteredItem("frazionz:trophy_silverfish");
            TROPHY_IRON_GOLEM = getRegisteredItem("frazionz:trophy_iron_golem");
            
            RECORD_FZ_1 = getRegisteredItem("frazionz:fz_record_1");
            RECORD_FZ_2 = getRegisteredItem("frazionz:fz_record_2");
            RECORD_FZ_3 = getRegisteredItem("frazionz:fz_record_3");
            RECORD_FZ_4 = getRegisteredItem("frazionz:fz_record_4");
            RECORD_FZ_5 = getRegisteredItem("frazionz:fz_record_5");
            RECORD_FZ_6 = getRegisteredItem("frazionz:fz_record_6");
            RECORD_FZ_7 = getRegisteredItem("frazionz:fz_record_7");
            RECORD_FZ_8 = getRegisteredItem("frazionz:fz_record_8");
            RECORD_FZ_9 = getRegisteredItem("frazionz:fz_record_9");
            RECORD_FZ_10 = getRegisteredItem("frazionz:fz_record_10");
            
            FARM_KEY = getRegisteredItem("frazionz:key_farm");
            VOTE_KEY = getRegisteredItem("frazionz:key_vote");
            COMMON_KEY = getRegisteredItem("frazionz:key_common");
            RARE_KEY = getRegisteredItem("frazionz:key_rare");
            LEGENDARY_KEY = getRegisteredItem("frazionz:key_legendary");
            
            STRAWBERRY = getRegisteredItem("frazionz:strawberry");
            BANANA = getRegisteredItem("frazionz:banana");
            PIZZA = getRegisteredItem("frazionz:pizza");
            DONUTS = getRegisteredItem("frazionz:donuts");
            
            NETHER_STRING = getRegisteredItem("frazionz:nether_string");
            RENFORCED_STRING = getRegisteredItem("frazionz:renforced_string");
            
            RUNE_ANTI_MALUS = getRegisteredItem("frazionz:rune_anti_malus");
            RUNE_BONUS = getRegisteredItem("frazionz:rune_bonus");
            RUNE_CHANCE = getRegisteredItem("frazionz:rune_chance");
            RUNE_DAMAGE = getRegisteredItem("frazionz:rune_damage");
            RUNE_HEALTH = getRegisteredItem("frazionz:rune_health");
            RUNE_MINING = getRegisteredItem("frazionz:rune_mining");
            RUNE_REGENERATION = getRegisteredItem("frazionz:rune_regeneration");
            RUNE_RESISTANCE = getRegisteredItem("frazionz:rune_resistance");
            RUNE_SPEED = getRegisteredItem("frazionz:rune_speed");
            
            BIG_XP = getRegisteredItem("frazionz:big_xp");
            
            LOOT_POWDER = getRegisteredItem("frazionz:loot_powder");
            FARM_POWDER = getRegisteredItem("frazionz:farm_powder");
            FARM_NUGGET = getRegisteredItem("frazionz:farm_nugget");
            
            TRAVELERS_HELMET = (ItemArmor)getRegisteredItem("frazionz:travelers_helmet");
            TRAVELERS_CHESTPLATE = (ItemArmor)getRegisteredItem("frazionz:travelers_chestplate");
            TRAVELERS_LEGGINGS = (ItemArmor)getRegisteredItem("frazionz:travelers_leggings");
            TRAVELERS_BOOTS = (ItemArmor)getRegisteredItem("frazionz:travelers_boots");
            
            DYNAMITE = getRegisteredItem("frazionz:dynamite");
            
            DYNAMITE_ARROW = getRegisteredItem("frazionz:dynamite_arrow");
            
            SPAWNER_PICKAXE = getRegisteredItem("frazionz:spawner_pickaxe");
            
            FRAZION_DAGGER = getRegisteredItem("frazionz:frazion_dagger");
            
            BILLET = getRegisteredItem("frazionz:billet");
            
            FARM_SWORD = getRegisteredItem("frazionz:farm_sword");
            
            YELLITE_BREWING_STAND = getRegisteredItem("frazionz:yellite_brewing_stand");
            BAUXITE_BREWING_STAND = getRegisteredItem("frazionz:bauxite_brewing_stand");
            ONYX_BREWING_STAND = getRegisteredItem("frazionz:onyx_brewing_stand");
            FRAZION_BREWING_STAND = getRegisteredItem("frazionz:frazion_brewing_stand");
            
            OBSIDIAN_TOWER = getRegisteredItem("frazionz:obsidian_tower");
            
            BOOSTER_XP = getRegisteredItem("frazionz:booster_xp");
            BOOSTER_APTITUDE = getRegisteredItem("frazionz:booster_aptitude");
            BOOSTER_REPAIR = getRegisteredItem("frazionz:booster_repair");
            
            WITHERED_BONE = getRegisteredItem("frazionz:withered_bone");
            WITHERED_BONE_MEAL = getRegisteredItem("frazionz:withered_bone_meal");
            
            BOTTLEXP = getRegisteredItem("frazionz:bottle_xp");
            FACTION_TOKEN = getRegisteredItem("frazionz:faction_token");
            IRON_TOOTH = getRegisteredItem("frazionz:iron_tooth");
        }
    }
}
