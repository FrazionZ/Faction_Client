package net.minecraft.init;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import fz.frazionz.block.BlockBauxiteChest;
import fz.frazionz.block.BlockCrimsonFungi;
import fz.frazionz.block.BlockCrimsonRoots;
import fz.frazionz.block.BlockFrazionChest;
import fz.frazionz.block.BlockHdvChest;
import fz.frazionz.block.BlockItemCrusher;
import fz.frazionz.block.BlockOnyxChest;
import fz.frazionz.block.BlockYelliteChest;
import fz.frazionz.block.BlockZDirtChest;
import fz.frazionz.block.BlockZHopper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.resources.ResourceLocation;

public class Blocks
{
    private static final Set<Block> CACHE;
    public static final Block AIR;
    public static final Block STONE;
    public static final BlockGrass GRASS;
    public static final Block DIRT;
    public static final Block COBBLESTONE;
    public static final Block PLANKS;
    public static final Block SAPLING;
    public static final Block BEDROCK;
    public static final BlockDynamicLiquid FLOWING_WATER;
    public static final BlockStaticLiquid WATER;
    public static final BlockDynamicLiquid FLOWING_LAVA;
    public static final BlockStaticLiquid LAVA;
    public static final BlockSand SAND;
    public static final Block GRAVEL;
    public static final Block GOLD_ORE;
    public static final Block IRON_ORE;
    public static final Block COAL_ORE;
    public static final Block LOG;
    public static final Block LOG2;
    public static final BlockLeaves LEAVES;
    public static final BlockLeaves LEAVES2;
    public static final Block SPONGE;
    public static final Block GLASS;
    public static final Block LAPIS_ORE;
    public static final Block LAPIS_BLOCK;
    public static final Block DISPENSER;
    public static final Block SANDSTONE;
    public static final Block NOTEBLOCK;
    public static final Block BED;
    public static final Block GOLDEN_RAIL;
    public static final Block DETECTOR_RAIL;
    public static final BlockPistonBase STICKY_PISTON;
    public static final Block WEB;
    public static final BlockTallGrass TALLGRASS;
    public static final BlockDeadBush DEADBUSH;
    public static final BlockPistonBase PISTON;
    public static final BlockPistonExtension PISTON_HEAD;
    public static final Block WOOL;
    public static final BlockPistonMoving PISTON_EXTENSION;
    public static final BlockFlower YELLOW_FLOWER;
    public static final BlockFlower RED_FLOWER;
    public static final BlockBush BROWN_MUSHROOM;
    public static final BlockBush RED_MUSHROOM;
    public static final Block GOLD_BLOCK;
    public static final Block IRON_BLOCK;
    public static final BlockSlab DOUBLE_STONE_SLAB;
    public static final BlockSlab STONE_SLAB;
    public static final Block BRICK_BLOCK;
    public static final Block TNT;
    public static final Block BOOKSHELF;
    public static final Block MOSSY_COBBLESTONE;
    public static final Block OBSIDIAN;
    public static final Block TORCH;
    public static final BlockFire FIRE;
    public static final Block MOB_SPAWNER;
    public static final Block OAK_STAIRS;
    public static final BlockChest CHEST;
    public static final BlockRedstoneWire REDSTONE_WIRE;
    public static final Block DIAMOND_ORE;
    public static final Block DIAMOND_BLOCK;
    public static final Block CRAFTING_TABLE;
    public static final Block WHEAT;
    public static final Block FARMLAND;
    public static final Block FURNACE;
    public static final Block LIT_FURNACE;
    public static final Block STANDING_SIGN;
    public static final BlockDoor OAK_DOOR;
    public static final BlockDoor SPRUCE_DOOR;
    public static final BlockDoor BIRCH_DOOR;
    public static final BlockDoor JUNGLE_DOOR;
    public static final BlockDoor ACACIA_DOOR;
    public static final BlockDoor DARK_OAK_DOOR;
    public static final Block LADDER;
    public static final Block RAIL;
    public static final Block STONE_STAIRS;
    public static final Block WALL_SIGN;
    public static final Block LEVER;
    public static final Block STONE_PRESSURE_PLATE;
    public static final BlockDoor IRON_DOOR;
    public static final Block WOODEN_PRESSURE_PLATE;
    public static final Block REDSTONE_ORE;
    public static final Block LIT_REDSTONE_ORE;
    public static final Block UNLIT_REDSTONE_TORCH;
    public static final Block REDSTONE_TORCH;
    public static final Block STONE_BUTTON;
    public static final Block SNOW_LAYER;
    public static final Block ICE;
    public static final Block SNOW;
    public static final BlockCactus CACTUS;
    public static final Block CLAY;
    public static final BlockReed REEDS;
    public static final Block JUKEBOX;
    public static final Block OAK_FENCE;
    public static final Block SPRUCE_FENCE;
    public static final Block BIRCH_FENCE;
    public static final Block JUNGLE_FENCE;
    public static final Block DARK_OAK_FENCE;
    public static final Block ACACIA_FENCE;
    public static final Block PUMPKIN;
    public static final Block NETHERRACK;
    public static final Block SOUL_SAND;
    public static final Block GLOWSTONE;
    public static final BlockPortal PORTAL;
    public static final Block LIT_PUMPKIN;
    public static final Block CAKE;
    public static final BlockRedstoneRepeater UNPOWERED_REPEATER;
    public static final BlockRedstoneRepeater POWERED_REPEATER;
    public static final Block TRAPDOOR;
    public static final Block MONSTER_EGG;
    public static final Block STONEBRICK;
    public static final Block BROWN_MUSHROOM_BLOCK;
    public static final Block RED_MUSHROOM_BLOCK;
    public static final Block IRON_BARS;
    public static final Block GLASS_PANE;
    public static final Block MELON_BLOCK;
    public static final Block PUMPKIN_STEM;
    public static final Block MELON_STEM;
    public static final Block VINE;
    public static final Block OAK_FENCE_GATE;
    public static final Block SPRUCE_FENCE_GATE;
    public static final Block BIRCH_FENCE_GATE;
    public static final Block JUNGLE_FENCE_GATE;
    public static final Block DARK_OAK_FENCE_GATE;
    public static final Block ACACIA_FENCE_GATE;
    public static final Block BRICK_STAIRS;
    public static final Block STONE_BRICK_STAIRS;
    public static final BlockMycelium MYCELIUM;
    public static final Block WATERLILY;
    public static final Block NETHER_BRICK;
    public static final Block NETHER_BRICK_FENCE;
    public static final Block NETHER_BRICK_STAIRS;
    public static final Block NETHER_WART;
    public static final Block ENCHANTING_TABLE;
    public static final Block BREWING_STAND;
    public static final BlockCauldron CAULDRON;
    public static final Block END_PORTAL;
    public static final Block END_PORTAL_FRAME;
    public static final Block END_STONE;
    public static final Block DRAGON_EGG;
    public static final Block REDSTONE_LAMP;
    public static final Block LIT_REDSTONE_LAMP;
    public static final BlockSlab DOUBLE_WOODEN_SLAB;
    public static final BlockSlab WOODEN_SLAB;
    public static final Block COCOA;
    public static final Block SANDSTONE_STAIRS;
    public static final Block EMERALD_ORE;
    public static final Block ENDER_CHEST;
    public static final BlockTripWireHook TRIPWIRE_HOOK;
    public static final Block TRIPWIRE;
    public static final Block EMERALD_BLOCK;
    public static final Block SPRUCE_STAIRS;
    public static final Block BIRCH_STAIRS;
    public static final Block JUNGLE_STAIRS;
    public static final Block COMMAND_BLOCK;
    public static final BlockBeacon BEACON;
    public static final Block COBBLESTONE_WALL;
    public static final Block FLOWER_POT;
    public static final Block CARROTS;
    public static final Block POTATOES;
    public static final Block WOODEN_BUTTON;
    public static final BlockSkull SKULL;
    public static final Block ANVIL;
    public static final Block TRAPPED_CHEST;
    public static final Block LIGHT_WEIGHTED_PRESSURE_PLATE;
    public static final Block HEAVY_WEIGHTED_PRESSURE_PLATE;
    public static final BlockRedstoneComparator UNPOWERED_COMPARATOR;
    public static final BlockRedstoneComparator POWERED_COMPARATOR;
    public static final BlockDaylightDetector DAYLIGHT_DETECTOR;
    public static final BlockDaylightDetector DAYLIGHT_DETECTOR_INVERTED;
    public static final Block REDSTONE_BLOCK;
    public static final Block QUARTZ_ORE;
    public static final BlockHopper HOPPER;
    public static final Block QUARTZ_BLOCK;
    public static final Block QUARTZ_STAIRS;
    public static final Block ACTIVATOR_RAIL;
    public static final Block DROPPER;
    public static final Block STAINED_HARDENED_CLAY;
    public static final Block BARRIER;
    public static final Block IRON_TRAPDOOR;
    public static final Block HAY_BLOCK;
    public static final Block CARPET;
    public static final Block HARDENED_CLAY;
    public static final Block COAL_BLOCK;
    public static final Block PACKED_ICE;
    public static final Block ACACIA_STAIRS;
    public static final Block DARK_OAK_STAIRS;
    public static final Block SLIME_BLOCK;
    public static final BlockDoublePlant DOUBLE_PLANT;
    public static final BlockStainedGlass STAINED_GLASS;
    public static final BlockStainedGlassPane STAINED_GLASS_PANE;
    public static final Block PRISMARINE;
    public static final Block SEA_LANTERN;
    public static final Block STANDING_BANNER;
    public static final Block WALL_BANNER;
    public static final Block RED_SANDSTONE;
    public static final Block RED_SANDSTONE_STAIRS;
    public static final BlockSlab DOUBLE_STONE_SLAB2;
    public static final BlockSlab STONE_SLAB2;
    public static final Block END_ROD;
    public static final Block CHORUS_PLANT;
    public static final Block CHORUS_FLOWER;
    public static final Block PURPUR_BLOCK;
    public static final Block PURPUR_PILLAR;
    public static final Block PURPUR_STAIRS;
    public static final BlockSlab PURPUR_DOUBLE_SLAB;
    public static final BlockSlab PURPUR_SLAB;
    public static final Block END_BRICKS;
    public static final Block BEETROOTS;
    public static final Block GRASS_PATH;
    public static final Block END_GATEWAY;
    public static final Block REPEATING_COMMAND_BLOCK;
    public static final Block CHAIN_COMMAND_BLOCK;
    public static final Block FROSTED_ICE;
    public static final Block MAGMA;
    public static final Block NETHER_WART_BLOCK;
    public static final Block RED_NETHER_BRICK;
    public static final Block BONE_BLOCK;
    public static final Block STRUCTURE_VOID;
    public static final Block OBSERVER;
    public static final Block WHITE_SHULKER_BOX;
    public static final Block ORANGE_SHULKER_BOX;
    public static final Block MAGENTA_SHULKER_BOX;
    public static final Block LIGHT_BLUE_SHULKER_BOX;
    public static final Block YELLOW_SHULKER_BOX;
    public static final Block LIME_SHULKER_BOX;
    public static final Block PINK_SHULKER_BOX;
    public static final Block GRAY_SHULKER_BOX;
    public static final Block SILVER_SHULKER_BOX;
    public static final Block CYAN_SHULKER_BOX;
    public static final Block PURPLE_SHULKER_BOX;
    public static final Block BLUE_SHULKER_BOX;
    public static final Block BROWN_SHULKER_BOX;
    public static final Block GREEN_SHULKER_BOX;
    public static final Block RED_SHULKER_BOX;
    public static final Block BLACK_SHULKER_BOX;
    public static final Block WHITE_GLAZED_TERRACOTTA;
    public static final Block ORANGE_GLAZED_TERRACOTTA;
    public static final Block MAGENTA_GLAZED_TERRACOTTA;
    public static final Block LIGHT_BLUE_GLAZED_TERRACOTTA;
    public static final Block YELLOW_GLAZED_TERRACOTTA;
    public static final Block LIME_GLAZED_TERRACOTTA;
    public static final Block PINK_GLAZED_TERRACOTTA;
    public static final Block GRAY_GLAZED_TERRACOTTA;
    public static final Block SILVER_GLAZED_TERRACOTTA;
    public static final Block CYAN_GLAZED_TERRACOTTA;
    public static final Block PURPLE_GLAZED_TERRACOTTA;
    public static final Block BLUE_GLAZED_TERRACOTTA;
    public static final Block BROWN_GLAZED_TERRACOTTA;
    public static final Block GREEN_GLAZED_TERRACOTTA;
    public static final Block RED_GLAZED_TERRACOTTA;
    public static final Block BLACK_GLAZED_TERRACOTTA;
    public static final Block CONCRETE;
    public static final Block CONCRETE_POWDER;
    public static final Block STRUCTURE_BLOCK;
    
    public static final Block YELLITE_ORE;
    public static final Block YELLITE_BLOCK;

    public static final Block BAUXITE_ORE;
    public static final Block BAUXITE_BLOCK;

    public static final Block ONYX_ORE;
    public static final Block ONYX_BLOCK;

    public static final Block FRAZION_ORE;
    public static final Block FRAZION_BLOCK;

    public static final BlockZHopper Z_HOPPER;

    public static final BlockZDirtChest DIRT_CHEST;
    public static final BlockYelliteChest YELLITE_CHEST;
    public static final BlockBauxiteChest BAUXITE_CHEST;
    public static final BlockOnyxChest ONYX_CHEST;
    public static final BlockFrazionChest FRAZION_CHEST;

    public static final BlockHdvChest HDV_CHEST;

    public static final Block YELLITE_FURNACE;
    public static final Block LIT_YELLITE_FURNACE;
    
    public static final Block BAUXITE_FURNACE;
    public static final Block LIT_BAUXITE_FURNACE;
    
    public static final Block ONYX_FURNACE;
    public static final Block LIT_ONYX_FURNACE;
    
    public static final Block FRAZION_FURNACE;
    public static final Block LIT_FRAZION_FURNACE;

    public static final Block CRISTAL_ROUGE;
    public static final Block CRISTAL_JAUNE;
    public static final Block CRISTAL_VIOLET;
    public static final Block CRISTAL_VERT;
    public static final Block CRISTAL_BLEU;

    public static final Block OBSIDIAN_YELLITE;
    public static final Block OBSIDIAN_BAUXITE;
    public static final Block OBSIDIAN_ONYX;
    public static final Block OBSIDIAN_FRAZION;

    public static final Block Z_TNT;
    
    public static final Block BAUXITE_LADDER;
    
    public static final Block CRIMSON_LOG;
    
    public static final BlockCrimsonRoots CRIMSON_ROOTS;
    
    public static final BlockCrimsonFungi CRIMSON_FUNGI;
    
    public static final Block NETHER_WART_BLOCK2;
    public static final Block SANDSTONE2;
    public static final Block STONE_ANDESITE;
    public static final Block STONE_ANDESITE_SMOOTH;
    public static final Block STONE_GRANITE;
    public static final Block STONE_GRANITE_SMOOTH;
    public static final Block STONE_DIORITE;
    public static final Block STONE_DIORITE_SMOOTH;
    
    public static final Block RANDOM_ORE;
    
    public static final Block AMELIORATOR;
    
    public static final Block YELLITE_LADDER;
    public static final Block ONYX_LADDER;
    public static final Block FRAZION_LADDER;
    
    public static final Block RENFORCED_SAND;
    
    public static final Block YELLITE_BREWING_STAND;
    public static final Block BAUXITE_BREWING_STAND;
    public static final Block ONYX_BREWING_STAND;
    public static final Block FRAZION_BREWING_STAND;
    
    public static final Block WITHER_BLOCK;
    
    public static final Block COMPACT_COBBLESTONE_X1;
    public static final Block COMPACT_COBBLESTONE_X2;
    public static final Block COMPACT_COBBLESTONE_X3;
    public static final Block COMPACT_COBBLESTONE_X4;
    public static final Block COMPACT_COBBLESTONE_X5;
    
    public static final Block REVERSE_FALL_BLOCK;
    
    public static final Block STONE_BLACKSTONE;
    public static final Block STONE_BLACKSTONE_SMOOTH;
    public static final Block BLOCK_PLACER_CHEST;
    public static final Block BLOCK_PLACER_TRAPCHEST;
    
    public static final Block TROPHY_FORGE;
    public static final Block GRIMOIRE_PEDESTAL;
    public static final BlockItemCrusher ITEM_CRUSHER;
    

    @Nullable

    /**
     * Returns the Block in the blockRegistry with the specified name.
     */
    private static Block getRegisteredBlock(String blockName)
    {
        Block block = Block.REGISTRY.getObject(new ResourceLocation(blockName));

        if (!CACHE.add(block))
        {
            throw new IllegalStateException("Invalid Block requested: " + blockName);
        }
        else
        {
            return block;
        }
    }

    static
    {
        if (!Bootstrap.isRegistered())
        {
            throw new RuntimeException("Accessed Blocks before Bootstrap!");
        }
        else
        {
            CACHE = Sets.<Block>newHashSet();
            AIR = getRegisteredBlock("air");
            STONE = getRegisteredBlock("stone");
            GRASS = (BlockGrass)getRegisteredBlock("grass");
            DIRT = getRegisteredBlock("dirt");
            COBBLESTONE = getRegisteredBlock("cobblestone");
            PLANKS = getRegisteredBlock("planks");
            SAPLING = getRegisteredBlock("sapling");
            BEDROCK = getRegisteredBlock("bedrock");
            FLOWING_WATER = (BlockDynamicLiquid)getRegisteredBlock("flowing_water");
            WATER = (BlockStaticLiquid)getRegisteredBlock("water");
            FLOWING_LAVA = (BlockDynamicLiquid)getRegisteredBlock("flowing_lava");
            LAVA = (BlockStaticLiquid)getRegisteredBlock("lava");
            SAND = (BlockSand)getRegisteredBlock("sand");
            GRAVEL = getRegisteredBlock("gravel");
            GOLD_ORE = getRegisteredBlock("gold_ore");
            IRON_ORE = getRegisteredBlock("iron_ore");
            COAL_ORE = getRegisteredBlock("coal_ore");
            LOG = getRegisteredBlock("log");
            LOG2 = getRegisteredBlock("log2");
            LEAVES = (BlockLeaves)getRegisteredBlock("leaves");
            LEAVES2 = (BlockLeaves)getRegisteredBlock("leaves2");
            SPONGE = getRegisteredBlock("sponge");
            GLASS = getRegisteredBlock("glass");
            LAPIS_ORE = getRegisteredBlock("lapis_ore");
            LAPIS_BLOCK = getRegisteredBlock("lapis_block");
            DISPENSER = getRegisteredBlock("dispenser");
            SANDSTONE = getRegisteredBlock("sandstone");
            NOTEBLOCK = getRegisteredBlock("noteblock");
            BED = getRegisteredBlock("bed");
            GOLDEN_RAIL = getRegisteredBlock("golden_rail");
            DETECTOR_RAIL = getRegisteredBlock("detector_rail");
            STICKY_PISTON = (BlockPistonBase)getRegisteredBlock("sticky_piston");
            WEB = getRegisteredBlock("web");
            TALLGRASS = (BlockTallGrass)getRegisteredBlock("tallgrass");
            DEADBUSH = (BlockDeadBush)getRegisteredBlock("deadbush");
            PISTON = (BlockPistonBase)getRegisteredBlock("piston");
            PISTON_HEAD = (BlockPistonExtension)getRegisteredBlock("piston_head");
            WOOL = getRegisteredBlock("wool");
            PISTON_EXTENSION = (BlockPistonMoving)getRegisteredBlock("piston_extension");
            YELLOW_FLOWER = (BlockFlower)getRegisteredBlock("yellow_flower");
            RED_FLOWER = (BlockFlower)getRegisteredBlock("red_flower");
            BROWN_MUSHROOM = (BlockBush)getRegisteredBlock("brown_mushroom");
            RED_MUSHROOM = (BlockBush)getRegisteredBlock("red_mushroom");
            GOLD_BLOCK = getRegisteredBlock("gold_block");
            IRON_BLOCK = getRegisteredBlock("iron_block");
            DOUBLE_STONE_SLAB = (BlockSlab)getRegisteredBlock("double_stone_slab");
            STONE_SLAB = (BlockSlab)getRegisteredBlock("stone_slab");
            BRICK_BLOCK = getRegisteredBlock("brick_block");
            TNT = getRegisteredBlock("tnt");
            BOOKSHELF = getRegisteredBlock("bookshelf");
            MOSSY_COBBLESTONE = getRegisteredBlock("mossy_cobblestone");
            OBSIDIAN = getRegisteredBlock("obsidian");
            TORCH = getRegisteredBlock("torch");
            FIRE = (BlockFire)getRegisteredBlock("fire");
            MOB_SPAWNER = getRegisteredBlock("mob_spawner");
            OAK_STAIRS = getRegisteredBlock("oak_stairs");
            CHEST = (BlockChest)getRegisteredBlock("chest");
            REDSTONE_WIRE = (BlockRedstoneWire)getRegisteredBlock("redstone_wire");
            DIAMOND_ORE = getRegisteredBlock("diamond_ore");
            DIAMOND_BLOCK = getRegisteredBlock("diamond_block");
            CRAFTING_TABLE = getRegisteredBlock("crafting_table");
            WHEAT = getRegisteredBlock("wheat");
            FARMLAND = getRegisteredBlock("farmland");
            FURNACE = getRegisteredBlock("furnace");
            LIT_FURNACE = getRegisteredBlock("lit_furnace");
            STANDING_SIGN = getRegisteredBlock("standing_sign");
            OAK_DOOR = (BlockDoor)getRegisteredBlock("wooden_door");
            SPRUCE_DOOR = (BlockDoor)getRegisteredBlock("spruce_door");
            BIRCH_DOOR = (BlockDoor)getRegisteredBlock("birch_door");
            JUNGLE_DOOR = (BlockDoor)getRegisteredBlock("jungle_door");
            ACACIA_DOOR = (BlockDoor)getRegisteredBlock("acacia_door");
            DARK_OAK_DOOR = (BlockDoor)getRegisteredBlock("dark_oak_door");
            LADDER = getRegisteredBlock("ladder");
            RAIL = getRegisteredBlock("rail");
            STONE_STAIRS = getRegisteredBlock("stone_stairs");
            WALL_SIGN = getRegisteredBlock("wall_sign");
            LEVER = getRegisteredBlock("lever");
            STONE_PRESSURE_PLATE = getRegisteredBlock("stone_pressure_plate");
            IRON_DOOR = (BlockDoor)getRegisteredBlock("iron_door");
            WOODEN_PRESSURE_PLATE = getRegisteredBlock("wooden_pressure_plate");
            REDSTONE_ORE = getRegisteredBlock("redstone_ore");
            LIT_REDSTONE_ORE = getRegisteredBlock("lit_redstone_ore");
            UNLIT_REDSTONE_TORCH = getRegisteredBlock("unlit_redstone_torch");
            REDSTONE_TORCH = getRegisteredBlock("redstone_torch");
            STONE_BUTTON = getRegisteredBlock("stone_button");
            SNOW_LAYER = getRegisteredBlock("snow_layer");
            ICE = getRegisteredBlock("ice");
            SNOW = getRegisteredBlock("snow");
            CACTUS = (BlockCactus)getRegisteredBlock("cactus");
            CLAY = getRegisteredBlock("clay");
            REEDS = (BlockReed)getRegisteredBlock("reeds");
            JUKEBOX = getRegisteredBlock("jukebox");
            OAK_FENCE = getRegisteredBlock("fence");
            SPRUCE_FENCE = getRegisteredBlock("spruce_fence");
            BIRCH_FENCE = getRegisteredBlock("birch_fence");
            JUNGLE_FENCE = getRegisteredBlock("jungle_fence");
            DARK_OAK_FENCE = getRegisteredBlock("dark_oak_fence");
            ACACIA_FENCE = getRegisteredBlock("acacia_fence");
            PUMPKIN = getRegisteredBlock("pumpkin");
            NETHERRACK = getRegisteredBlock("netherrack");
            SOUL_SAND = getRegisteredBlock("soul_sand");
            GLOWSTONE = getRegisteredBlock("glowstone");
            PORTAL = (BlockPortal)getRegisteredBlock("portal");
            LIT_PUMPKIN = getRegisteredBlock("lit_pumpkin");
            CAKE = getRegisteredBlock("cake");
            UNPOWERED_REPEATER = (BlockRedstoneRepeater)getRegisteredBlock("unpowered_repeater");
            POWERED_REPEATER = (BlockRedstoneRepeater)getRegisteredBlock("powered_repeater");
            TRAPDOOR = getRegisteredBlock("trapdoor");
            MONSTER_EGG = getRegisteredBlock("monster_egg");
            STONEBRICK = getRegisteredBlock("stonebrick");
            BROWN_MUSHROOM_BLOCK = getRegisteredBlock("brown_mushroom_block");
            RED_MUSHROOM_BLOCK = getRegisteredBlock("red_mushroom_block");
            IRON_BARS = getRegisteredBlock("iron_bars");
            GLASS_PANE = getRegisteredBlock("glass_pane");
            MELON_BLOCK = getRegisteredBlock("melon_block");
            PUMPKIN_STEM = getRegisteredBlock("pumpkin_stem");
            MELON_STEM = getRegisteredBlock("melon_stem");
            VINE = getRegisteredBlock("vine");
            OAK_FENCE_GATE = getRegisteredBlock("fence_gate");
            SPRUCE_FENCE_GATE = getRegisteredBlock("spruce_fence_gate");
            BIRCH_FENCE_GATE = getRegisteredBlock("birch_fence_gate");
            JUNGLE_FENCE_GATE = getRegisteredBlock("jungle_fence_gate");
            DARK_OAK_FENCE_GATE = getRegisteredBlock("dark_oak_fence_gate");
            ACACIA_FENCE_GATE = getRegisteredBlock("acacia_fence_gate");
            BRICK_STAIRS = getRegisteredBlock("brick_stairs");
            STONE_BRICK_STAIRS = getRegisteredBlock("stone_brick_stairs");
            MYCELIUM = (BlockMycelium)getRegisteredBlock("mycelium");
            WATERLILY = getRegisteredBlock("waterlily");
            NETHER_BRICK = getRegisteredBlock("nether_brick");
            NETHER_BRICK_FENCE = getRegisteredBlock("nether_brick_fence");
            NETHER_BRICK_STAIRS = getRegisteredBlock("nether_brick_stairs");
            NETHER_WART = getRegisteredBlock("nether_wart");
            ENCHANTING_TABLE = getRegisteredBlock("enchanting_table");
            BREWING_STAND = getRegisteredBlock("brewing_stand");
            CAULDRON = (BlockCauldron)getRegisteredBlock("cauldron");
            END_PORTAL = getRegisteredBlock("end_portal");
            END_PORTAL_FRAME = getRegisteredBlock("end_portal_frame");
            END_STONE = getRegisteredBlock("end_stone");
            DRAGON_EGG = getRegisteredBlock("dragon_egg");
            REDSTONE_LAMP = getRegisteredBlock("redstone_lamp");
            LIT_REDSTONE_LAMP = getRegisteredBlock("lit_redstone_lamp");
            DOUBLE_WOODEN_SLAB = (BlockSlab)getRegisteredBlock("double_wooden_slab");
            WOODEN_SLAB = (BlockSlab)getRegisteredBlock("wooden_slab");
            COCOA = getRegisteredBlock("cocoa");
            SANDSTONE_STAIRS = getRegisteredBlock("sandstone_stairs");
            EMERALD_ORE = getRegisteredBlock("emerald_ore");
            ENDER_CHEST = getRegisteredBlock("ender_chest");
            TRIPWIRE_HOOK = (BlockTripWireHook)getRegisteredBlock("tripwire_hook");
            TRIPWIRE = getRegisteredBlock("tripwire");
            EMERALD_BLOCK = getRegisteredBlock("emerald_block");
            SPRUCE_STAIRS = getRegisteredBlock("spruce_stairs");
            BIRCH_STAIRS = getRegisteredBlock("birch_stairs");
            JUNGLE_STAIRS = getRegisteredBlock("jungle_stairs");
            COMMAND_BLOCK = getRegisteredBlock("command_block");
            BEACON = (BlockBeacon)getRegisteredBlock("beacon");
            COBBLESTONE_WALL = getRegisteredBlock("cobblestone_wall");
            FLOWER_POT = getRegisteredBlock("flower_pot");
            CARROTS = getRegisteredBlock("carrots");
            POTATOES = getRegisteredBlock("potatoes");
            WOODEN_BUTTON = getRegisteredBlock("wooden_button");
            SKULL = (BlockSkull)getRegisteredBlock("skull");
            ANVIL = getRegisteredBlock("anvil");
            TRAPPED_CHEST = getRegisteredBlock("trapped_chest");
            LIGHT_WEIGHTED_PRESSURE_PLATE = getRegisteredBlock("light_weighted_pressure_plate");
            HEAVY_WEIGHTED_PRESSURE_PLATE = getRegisteredBlock("heavy_weighted_pressure_plate");
            UNPOWERED_COMPARATOR = (BlockRedstoneComparator)getRegisteredBlock("unpowered_comparator");
            POWERED_COMPARATOR = (BlockRedstoneComparator)getRegisteredBlock("powered_comparator");
            DAYLIGHT_DETECTOR = (BlockDaylightDetector)getRegisteredBlock("daylight_detector");
            DAYLIGHT_DETECTOR_INVERTED = (BlockDaylightDetector)getRegisteredBlock("daylight_detector_inverted");
            REDSTONE_BLOCK = getRegisteredBlock("redstone_block");
            QUARTZ_ORE = getRegisteredBlock("quartz_ore");
            HOPPER = (BlockHopper)getRegisteredBlock("hopper");
            QUARTZ_BLOCK = getRegisteredBlock("quartz_block");
            QUARTZ_STAIRS = getRegisteredBlock("quartz_stairs");
            ACTIVATOR_RAIL = getRegisteredBlock("activator_rail");
            DROPPER = getRegisteredBlock("dropper");
            STAINED_HARDENED_CLAY = getRegisteredBlock("stained_hardened_clay");
            BARRIER = getRegisteredBlock("barrier");
            IRON_TRAPDOOR = getRegisteredBlock("iron_trapdoor");
            HAY_BLOCK = getRegisteredBlock("hay_block");
            CARPET = getRegisteredBlock("carpet");
            HARDENED_CLAY = getRegisteredBlock("hardened_clay");
            COAL_BLOCK = getRegisteredBlock("coal_block");
            PACKED_ICE = getRegisteredBlock("packed_ice");
            ACACIA_STAIRS = getRegisteredBlock("acacia_stairs");
            DARK_OAK_STAIRS = getRegisteredBlock("dark_oak_stairs");
            SLIME_BLOCK = getRegisteredBlock("slime");
            DOUBLE_PLANT = (BlockDoublePlant)getRegisteredBlock("double_plant");
            STAINED_GLASS = (BlockStainedGlass)getRegisteredBlock("stained_glass");
            STAINED_GLASS_PANE = (BlockStainedGlassPane)getRegisteredBlock("stained_glass_pane");
            PRISMARINE = getRegisteredBlock("prismarine");
            SEA_LANTERN = getRegisteredBlock("sea_lantern");
            STANDING_BANNER = getRegisteredBlock("standing_banner");
            WALL_BANNER = getRegisteredBlock("wall_banner");
            RED_SANDSTONE = getRegisteredBlock("red_sandstone");
            RED_SANDSTONE_STAIRS = getRegisteredBlock("red_sandstone_stairs");
            DOUBLE_STONE_SLAB2 = (BlockSlab)getRegisteredBlock("double_stone_slab2");
            STONE_SLAB2 = (BlockSlab)getRegisteredBlock("stone_slab2");
            END_ROD = getRegisteredBlock("end_rod");
            CHORUS_PLANT = getRegisteredBlock("chorus_plant");
            CHORUS_FLOWER = getRegisteredBlock("chorus_flower");
            PURPUR_BLOCK = getRegisteredBlock("purpur_block");
            PURPUR_PILLAR = getRegisteredBlock("purpur_pillar");
            PURPUR_STAIRS = getRegisteredBlock("purpur_stairs");
            PURPUR_DOUBLE_SLAB = (BlockSlab)getRegisteredBlock("purpur_double_slab");
            PURPUR_SLAB = (BlockSlab)getRegisteredBlock("purpur_slab");
            END_BRICKS = getRegisteredBlock("end_bricks");
            BEETROOTS = getRegisteredBlock("beetroots");
            GRASS_PATH = getRegisteredBlock("grass_path");
            END_GATEWAY = getRegisteredBlock("end_gateway");
            REPEATING_COMMAND_BLOCK = getRegisteredBlock("repeating_command_block");
            CHAIN_COMMAND_BLOCK = getRegisteredBlock("chain_command_block");
            FROSTED_ICE = getRegisteredBlock("frosted_ice");
            MAGMA = getRegisteredBlock("magma");
            NETHER_WART_BLOCK = getRegisteredBlock("nether_wart_block");
            RED_NETHER_BRICK = getRegisteredBlock("red_nether_brick");
            BONE_BLOCK = getRegisteredBlock("bone_block");
            STRUCTURE_VOID = getRegisteredBlock("structure_void");
            OBSERVER = getRegisteredBlock("observer");
            WHITE_SHULKER_BOX = getRegisteredBlock("white_shulker_box");
            ORANGE_SHULKER_BOX = getRegisteredBlock("orange_shulker_box");
            MAGENTA_SHULKER_BOX = getRegisteredBlock("magenta_shulker_box");
            LIGHT_BLUE_SHULKER_BOX = getRegisteredBlock("light_blue_shulker_box");
            YELLOW_SHULKER_BOX = getRegisteredBlock("yellow_shulker_box");
            LIME_SHULKER_BOX = getRegisteredBlock("lime_shulker_box");
            PINK_SHULKER_BOX = getRegisteredBlock("pink_shulker_box");
            GRAY_SHULKER_BOX = getRegisteredBlock("gray_shulker_box");
            SILVER_SHULKER_BOX = getRegisteredBlock("silver_shulker_box");
            CYAN_SHULKER_BOX = getRegisteredBlock("cyan_shulker_box");
            PURPLE_SHULKER_BOX = getRegisteredBlock("purple_shulker_box");
            BLUE_SHULKER_BOX = getRegisteredBlock("blue_shulker_box");
            BROWN_SHULKER_BOX = getRegisteredBlock("brown_shulker_box");
            GREEN_SHULKER_BOX = getRegisteredBlock("green_shulker_box");
            RED_SHULKER_BOX = getRegisteredBlock("red_shulker_box");
            BLACK_SHULKER_BOX = getRegisteredBlock("black_shulker_box");
            WHITE_GLAZED_TERRACOTTA = getRegisteredBlock("white_glazed_terracotta");
            ORANGE_GLAZED_TERRACOTTA = getRegisteredBlock("orange_glazed_terracotta");
            MAGENTA_GLAZED_TERRACOTTA = getRegisteredBlock("magenta_glazed_terracotta");
            LIGHT_BLUE_GLAZED_TERRACOTTA = getRegisteredBlock("light_blue_glazed_terracotta");
            YELLOW_GLAZED_TERRACOTTA = getRegisteredBlock("yellow_glazed_terracotta");
            LIME_GLAZED_TERRACOTTA = getRegisteredBlock("lime_glazed_terracotta");
            PINK_GLAZED_TERRACOTTA = getRegisteredBlock("pink_glazed_terracotta");
            GRAY_GLAZED_TERRACOTTA = getRegisteredBlock("gray_glazed_terracotta");
            SILVER_GLAZED_TERRACOTTA = getRegisteredBlock("silver_glazed_terracotta");
            CYAN_GLAZED_TERRACOTTA = getRegisteredBlock("cyan_glazed_terracotta");
            PURPLE_GLAZED_TERRACOTTA = getRegisteredBlock("purple_glazed_terracotta");
            BLUE_GLAZED_TERRACOTTA = getRegisteredBlock("blue_glazed_terracotta");
            BROWN_GLAZED_TERRACOTTA = getRegisteredBlock("brown_glazed_terracotta");
            GREEN_GLAZED_TERRACOTTA = getRegisteredBlock("green_glazed_terracotta");
            RED_GLAZED_TERRACOTTA = getRegisteredBlock("red_glazed_terracotta");
            BLACK_GLAZED_TERRACOTTA = getRegisteredBlock("black_glazed_terracotta");
            CONCRETE = getRegisteredBlock("concrete");
            CONCRETE_POWDER = getRegisteredBlock("concrete_powder");
            STRUCTURE_BLOCK = getRegisteredBlock("structure_block");
            
            DIRT_CHEST = (BlockZDirtChest)getRegisteredBlock("frazionz:dirt_chest");
            
            YELLITE_ORE = getRegisteredBlock("frazionz:yellite_ore");
            YELLITE_BLOCK = getRegisteredBlock("frazionz:yellite_block");
            YELLITE_LADDER = getRegisteredBlock("frazionz:yellite_ladder");
            YELLITE_CHEST = (BlockYelliteChest)getRegisteredBlock("frazionz:yellite_chest");
            
            BAUXITE_ORE = getRegisteredBlock("frazionz:bauxite_ore");
            BAUXITE_BLOCK = getRegisteredBlock("frazionz:bauxite_block");
            BAUXITE_LADDER = getRegisteredBlock("frazionz:bauxite_ladder");
            BAUXITE_CHEST = (BlockBauxiteChest)getRegisteredBlock("frazionz:bauxite_chest");
            
            ONYX_ORE = getRegisteredBlock("frazionz:onyx_ore");
            ONYX_BLOCK = getRegisteredBlock("frazionz:onyx_block");
            ONYX_LADDER = getRegisteredBlock("frazionz:onyx_ladder");
            ONYX_CHEST = (BlockOnyxChest)getRegisteredBlock("frazionz:onyx_chest");
            
            FRAZION_ORE = getRegisteredBlock("frazionz:frazion_ore");
            FRAZION_BLOCK = getRegisteredBlock("frazionz:frazion_block");
            FRAZION_LADDER = getRegisteredBlock("frazionz:frazion_ladder");
            FRAZION_CHEST = (BlockFrazionChest)getRegisteredBlock("frazionz:frazion_chest");
            
            Z_HOPPER = (BlockZHopper)getRegisteredBlock("frazionz:z_hopper");
            HDV_CHEST = (BlockHdvChest)getRegisteredBlock("frazionz:hdv_chest");
            CRISTAL_ROUGE = getRegisteredBlock("frazionz:cristal_rouge");
            CRISTAL_JAUNE = getRegisteredBlock("frazionz:cristal_jaune");
            CRISTAL_VIOLET = getRegisteredBlock("frazionz:cristal_violet");
            CRISTAL_VERT = getRegisteredBlock("frazionz:cristal_vert");
            CRISTAL_BLEU = getRegisteredBlock("frazionz:cristal_bleu");
            OBSIDIAN_YELLITE = getRegisteredBlock("frazionz:obsidian_yellite");
            OBSIDIAN_BAUXITE = getRegisteredBlock("frazionz:obsidian_bauxite");
            OBSIDIAN_ONYX = getRegisteredBlock("frazionz:obsidian_onyx");
            OBSIDIAN_FRAZION = getRegisteredBlock("frazionz:obsidian_frazion");
            Z_TNT = getRegisteredBlock("frazionz:z_tnt");
            
            YELLITE_FURNACE = getRegisteredBlock("frazionz:yellite_furnace");
            LIT_YELLITE_FURNACE = getRegisteredBlock("frazionz:lit_yellite_furnace");
            
            BAUXITE_FURNACE = getRegisteredBlock("frazionz:bauxite_furnace");
            LIT_BAUXITE_FURNACE = getRegisteredBlock("frazionz:lit_bauxite_furnace");
            
            ONYX_FURNACE = getRegisteredBlock("frazionz:onyx_furnace");
            LIT_ONYX_FURNACE = getRegisteredBlock("frazionz:lit_onyx_furnace");
            
            FRAZION_FURNACE = getRegisteredBlock("frazionz:frazion_furnace");
            LIT_FRAZION_FURNACE = getRegisteredBlock("frazionz:lit_frazion_furnace");

            CRIMSON_LOG = getRegisteredBlock("frazionz:crimson_log");
            CRIMSON_ROOTS = (BlockCrimsonRoots)getRegisteredBlock("frazionz:crimson_roots");
            CRIMSON_FUNGI = (BlockCrimsonFungi)getRegisteredBlock("frazionz:crimson_fungi");
            
            NETHER_WART_BLOCK2 = getRegisteredBlock("frazionz:nether_wart_block2");
            SANDSTONE2 = getRegisteredBlock("frazionz:sandstone2");
            STONE_ANDESITE = getRegisteredBlock("frazionz:stone_andesite");
            STONE_ANDESITE_SMOOTH = getRegisteredBlock("frazionz:stone_andesite_smooth");
            STONE_GRANITE = getRegisteredBlock("frazionz:stone_granite");
            STONE_GRANITE_SMOOTH = getRegisteredBlock("frazionz:stone_granite_smooth");
            STONE_DIORITE = getRegisteredBlock("frazionz:stone_diorite");
            STONE_DIORITE_SMOOTH = getRegisteredBlock("frazionz:stone_diorite_smooth");
            
            RANDOM_ORE = getRegisteredBlock("frazionz:random_ore");
            
            AMELIORATOR = getRegisteredBlock("frazionz:ameliorator");
            
            RENFORCED_SAND = getRegisteredBlock("frazionz:renforced_sand");
            
            YELLITE_BREWING_STAND = getRegisteredBlock("frazionz:yellite_brewing_stand");
            BAUXITE_BREWING_STAND = getRegisteredBlock("frazionz:bauxite_brewing_stand");
            ONYX_BREWING_STAND = getRegisteredBlock("frazionz:onyx_brewing_stand");
            FRAZION_BREWING_STAND = getRegisteredBlock("frazionz:frazion_brewing_stand");
            
            WITHER_BLOCK = getRegisteredBlock("frazionz:wither_block");
            
            COMPACT_COBBLESTONE_X1 = getRegisteredBlock("frazionz:compact_cobblestone_x1");
            COMPACT_COBBLESTONE_X2 = getRegisteredBlock("frazionz:compact_cobblestone_x2");
            COMPACT_COBBLESTONE_X3 = getRegisteredBlock("frazionz:compact_cobblestone_x3");
            COMPACT_COBBLESTONE_X4 = getRegisteredBlock("frazionz:compact_cobblestone_x4");
            COMPACT_COBBLESTONE_X5 = getRegisteredBlock("frazionz:compact_cobblestone_x5");
            
            REVERSE_FALL_BLOCK = getRegisteredBlock("frazionz:reverse_fall_block");
            
            STONE_BLACKSTONE = getRegisteredBlock("frazionz:stone_blackstone");
            STONE_BLACKSTONE_SMOOTH = getRegisteredBlock("frazionz:stone_blackstone_smooth");
            BLOCK_PLACER_CHEST = getRegisteredBlock("frazionz:block_placer_chest");
            BLOCK_PLACER_TRAPCHEST = getRegisteredBlock("frazionz:block_placer_trapchest");
            TROPHY_FORGE = getRegisteredBlock("frazionz:trophy_forge");
            GRIMOIRE_PEDESTAL = getRegisteredBlock("frazionz:grimoire_pedestal");
            ITEM_CRUSHER = (BlockItemCrusher)getRegisteredBlock("frazionz:item_crusher");
            
            CACHE.clear();
        }
    }
}
