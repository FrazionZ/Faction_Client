package fz.frazionz.manager;

import fz.frazionz.block.*;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.resources.ResourceLocation;

public class BlockManager {

	public static void registerBlocks() {
        registerBlock(454, "yellite_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("yellite_ore"));
        registerBlock(455, "yellite_block", (new Block(Material.IRON, MapColor.DIAMOND)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("yellite_block").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        registerBlock(456, "bauxite_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("bauxite_ore"));
        registerBlock(457, "bauxite_block", (new Block(Material.IRON, MapColor.DIAMOND)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("bauxite_block").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        registerBlock(458, "onyx_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("onyx_ore"));
        registerBlock(459, "onyx_block", (new Block(Material.IRON, MapColor.DIAMOND)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("onyx_block").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        registerBlock(460, "frazion_ore", (new BlockOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("frazion_ore"));
        registerBlock(461, "frazion_block", (new Block(Material.IRON, MapColor.DIAMOND)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("frazion_block").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        
        registerBlock(462, "z_hopper", (new BlockZHopper()).setHardness(3.0F).setResistance(8.0F).setSoundType(SoundType.METAL).setTranslationKey("z_hopper"));
        
        registerBlock(466, "hdv_chest", (new BlockHdvChest()).setHardness(2.5F).setSoundType(SoundType.METAL).setTranslationKey("hdv_chest"));
        registerBlock(463, "dirt_chest", (new BlockZDirtChest()).setHardness(2.5F).setSoundType(SoundType.GROUND).setTranslationKey("dirt_chest"));//
        registerBlock(464, "yellite_chest", (new BlockYelliteChest()).setHardness(2.5F).setResistance(5.0F).setSoundType(SoundType.METAL).setTranslationKey("yellite_chest"));
        registerBlock(465, "onyx_chest", (new BlockOnyxChest()).setHardness(2.5F).setResistance(15.0F).setSoundType(SoundType.METAL).setTranslationKey("onyx_chest"));
        
        registerBlock(469, "cristal_rouge", (new BlockCrystal(Material.GLASS, false)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("cristal_rouge").setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightOpacity(0));
        registerBlock(470, "cristal_bleu", (new BlockCrystal(Material.GLASS, false)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("cristal_bleu").setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightOpacity(0));
        registerBlock(471, "cristal_vert", (new BlockCrystal(Material.GLASS, false)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("cristal_vert").setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightOpacity(0));
        registerBlock(472, "cristal_jaune", (new BlockCrystal(Material.GLASS, false)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("cristal_jaune").setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightOpacity(0));
        registerBlock(473, "cristal_violet", (new BlockCrystal(Material.GLASS, false)).setHardness(5.0F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("cristal_violet").setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightOpacity(0));
        registerBlock(474, "obsidian_yellite", (new BlockObsidian()).setHardness(60.0F).setResistance(2000.0F).setSoundType(SoundType.STONE).setTranslationKey("obsidian_yellite"));
        registerBlock(475, "obsidian_bauxite", (new BlockObsidian()).setHardness(80.0F).setResistance(2000.0F).setSoundType(SoundType.STONE).setTranslationKey("obsidian_bauxite"));
        registerBlock(476, "obsidian_onyx", (new BlockObsidian()).setHardness(100.0F).setResistance(2000.0F).setSoundType(SoundType.STONE).setTranslationKey("obsidian_onyx"));
        registerBlock(477, "obsidian_frazion", (new BlockObsidian()).setHardness(120.0F).setResistance(2000.0F).setSoundType(SoundType.STONE).setTranslationKey("obsidian_frazion"));
        registerBlock(478, "z_tnt", (new BlockZTNT()).setHardness(1.0F).setSoundType(SoundType.PLANT).setTranslationKey("z_tnt"));
        
        registerBlock(480, "yellite_furnace", (new BlockYelliteFurnace(false)).setHardness(3.5F).setSoundType(SoundType.STONE).setTranslationKey("yellite_furnace").setCreativeTab(CreativeTabs.DECORATIONS));
        registerBlock(481, "lit_yellite_furnace", (new BlockYelliteFurnace(true)).setHardness(3.5F).setSoundType(SoundType.STONE).setLightLevel(0.875F).setTranslationKey("lit_yellite_furnace"));
        
        registerBlock(482, "bauxite_furnace", (new BlockBauxiteFurnace(false)).setHardness(3.5F).setSoundType(SoundType.STONE).setTranslationKey("bauxite_furnace").setCreativeTab(CreativeTabs.DECORATIONS));
        registerBlock(483, "lit_bauxite_furnace", (new BlockBauxiteFurnace(true)).setHardness(3.5F).setSoundType(SoundType.STONE).setLightLevel(0.875F).setTranslationKey("bauxite_furnace"));
        
        registerBlock(484, "onyx_furnace", (new BlockOnyxFurnace(false)).setHardness(3.5F).setSoundType(SoundType.STONE).setTranslationKey("onyx_furnace").setCreativeTab(CreativeTabs.DECORATIONS));
        registerBlock(485, "lit_onyx_furnace", (new BlockOnyxFurnace(true)).setHardness(3.5F).setSoundType(SoundType.STONE).setLightLevel(0.875F).setTranslationKey("onyx_furnace"));
        
        registerBlock(486, "frazion_furnace", (new BlockFrazionFurnace(false)).setHardness(3.5F).setSoundType(SoundType.STONE).setTranslationKey("frazion_furnace").setCreativeTab(CreativeTabs.DECORATIONS));
        registerBlock(487, "lit_frazion_furnace", (new BlockFrazionFurnace(true)).setHardness(3.5F).setSoundType(SoundType.STONE).setLightLevel(0.875F).setTranslationKey("frazion_furnace"));
        
        registerBlock(488, "bauxite_ladder", (new BlockBauxiteLadder()).setHardness(0.4F).setSoundType(SoundType.LADDER).setTranslationKey("bauxite_ladder"));
        
        registerBlock(489, "crimson_log", (new BlockNetherLog()).setTranslationKey("crimson_log").setSoundType(SoundType.WOOD));
        registerBlock(490, "crimson_roots", (new BlockCrimsonRoots()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("crimson_roots"));
        registerBlock(491, "crimson_fungi", (new BlockCrimsonFungi()).setHardness(0.0F).setSoundType(SoundType.PLANT).setTranslationKey("crimson_fungi"));
        
        registerBlock(492, "nether_wart_block2", (new BlockMoreNWBVariant("nether_wart_block")).setHardness(1.0F).setSoundType(SoundType.WOOD).setTranslationKey("nether_wart_block"));
        registerBlock(493, "sandstone2", (new BlockMoreSandstoneVariant("sandstone")).setHardness(0.8F).setSoundType(SoundType.STONE).setTranslationKey("sandstone"));
        registerBlock(494, "stone_andesite", (new BlockMoreAndesiteVariant("stone_andesite")).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone_andesite"));
        registerBlock(495, "stone_andesite_smooth", (new BlockMoreAndesiteSmoothVariant("stone_andesite_smooth")).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone_andesite_smooth"));
        registerBlock(496, "stone_granite", (new BlockMoreGraniteVariant("stone_granite")).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone_granite"));
        registerBlock(497, "stone_granite_smooth", (new BlockMoreGraniteSmoothVariant("stone_granite_smooth")).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone_granite_smooth"));
        registerBlock(498, "stone_diorite", (new BlockMoreDioriteVariant("stone_diorite")).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone_diorite"));
        registerBlock(499, "stone_diorite_smooth", (new BlockMoreDioriteSmoothVariant("stone_diorite_smooth")).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone_diorite_smooth"));
        
        registerBlock(500, "random_ore", (new BlockRandomOre()).setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setTranslationKey("random_ore"));
        
        registerBlock(501, "ameliorator", (new BlockAmeliorator()).setLightLevel(0.500F).setHardness(25.0F).setResistance(1000.0F).setSoundType(SoundType.STONE).setTranslationKey("ameliorator"));
        
        registerBlock(502, "yellite_ladder", (new BlockYelliteLadder()).setHardness(0.4F).setSoundType(SoundType.LADDER).setTranslationKey("yellite_ladder"));
        registerBlock(503, "onyx_ladder", (new BlockOnyxLadder()).setHardness(0.4F).setSoundType(SoundType.LADDER).setTranslationKey("onyx_ladder"));
        registerBlock(504, "frazion_ladder", (new BlockFrazionLadder()).setHardness(0.4F).setSoundType(SoundType.LADDER).setTranslationKey("frazion_ladder"));
        
        registerBlock(505, "bauxite_chest", (new BlockBauxiteChest()).setHardness(2.5F).setResistance(10.0F).setSoundType(SoundType.METAL).setTranslationKey("bauxite_chest"));
        registerBlock(506, "frazion_chest", (new BlockFrazionChest()).setHardness(2.5F).setResistance(20.0F).setSoundType(SoundType.METAL).setTranslationKey("frazion_chest"));
        
        registerBlock(507, "renforced_sand", (new BlockRenforcedSand()).setHardness(1.0F).setResistance(10.0F).setSoundType(SoundType.SAND).setTranslationKey("renforced_sand"));
        
        registerBlock(508, "yellite_brewing_stand", (new BlockYelliteBrewingStand()).setHardness(0.5F).setLightLevel(0.125F).setTranslationKey("yellite_brewing_stand"));
        registerBlock(509, "bauxite_brewing_stand", (new BlockBauxiteBrewingStand()).setHardness(0.5F).setLightLevel(0.125F).setTranslationKey("bauxite_brewing_stand"));
        registerBlock(510, "onyx_brewing_stand", (new BlockBrewingStand()).setHardness(0.5F).setLightLevel(0.125F).setTranslationKey("onyx_brewing_stand"));
        registerBlock(511, "frazion_brewing_stand", (new BlockBrewingStand()).setHardness(0.5F).setLightLevel(0.125F).setTranslationKey("frazion_brewing_stand"));
        
        registerBlock(512, "wither_block", (new BlockWitherBlock()).setHardness(10.0F).setResistance(2000.0F).setSoundType(SoundType.STONE).setTranslationKey("wither_block"));
        registerBlock(513, "compact_cobblestone_x1", (new Block(Material.ROCK)).setHardness(2.0F).setResistance(12.0F).setSoundType(SoundType.STONE).setTranslationKey("compact_cobblestone_x1").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        registerBlock(514, "compact_cobblestone_x2", (new Block(Material.ROCK)).setHardness(2.3F).setResistance(16.0F).setSoundType(SoundType.STONE).setTranslationKey("compact_cobblestone_x2").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        registerBlock(515, "compact_cobblestone_x3", (new Block(Material.ROCK)).setHardness(2.6F).setResistance(20.0F).setSoundType(SoundType.STONE).setTranslationKey("compact_cobblestone_x3").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        registerBlock(516, "compact_cobblestone_x4", (new Block(Material.ROCK)).setHardness(3.0F).setResistance(24.0F).setSoundType(SoundType.STONE).setTranslationKey("compact_cobblestone_x4").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        registerBlock(517, "compact_cobblestone_x5", (new Block(Material.ROCK)).setHardness(3.4F).setResistance(35.0F).setSoundType(SoundType.STONE).setTranslationKey("compact_cobblestone_x5").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        registerBlock(518, "reverse_fall_block", (new BlockReverseFall()).setHardness(0.25F).setResistance(10.0F).setSoundType(SoundType.SLIME).setTranslationKey("reverse_fall_block").setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
        
        
        registerBlock(519, "stone_blackstone", (new BlockDarkAndesite("stone_blackstone")).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone_blackstone"));
        registerBlock(520, "stone_blackstone_smooth", (new BlockSmoothDarkAndesite("stone_blackstone_smooth")).setHardness(1.5F).setResistance(10.0F).setSoundType(SoundType.STONE).setTranslationKey("stone_blackstone_smooth"));

        registerBlock(521, "block_placer_chest", (new BlockBlockPlacer("block_placer_chest")).setBlockUnbreakable().setResistance(6000000.0F).setSoundType(SoundType.STONE).setTranslationKey("block_placer_chest"));
        registerBlock(522, "block_placer_trapchest", (new BlockBlockPlacer("block_placer_trapchest")).setBlockUnbreakable().setResistance(6000000.0F).setSoundType(SoundType.STONE).setTranslationKey("block_placer_trapchest"));
       
        registerBlock(523, "trophy_forge", (new BlockTrophyForge()).setHardness(25.0F).setResistance(1000.0F).setSoundType(SoundType.STONE).setTranslationKey("trophy_forge"));
        registerBlock(524, "grimoire_pedestal", (new BlockGrimoirePedestal()).setHardness(25.0F).setResistance(1000.0F).setSoundType(SoundType.STONE).setTranslationKey("grimoire_pedestal"));
        registerBlock(525, "item_crusher", (new BlockItemCrusher()).setHardness(25.0F).setResistance(1000.0F).setSoundType(SoundType.STONE).setTranslationKey("item_crusher"));

        registerBlock(530, "strawberries", (new BlockStrawberries()).setTranslationKey("strawberries"));
    }
	
    private static void registerBlock(int id, ResourceLocation textualID, Block block_)
    {
        Block.REGISTRY.register(id, textualID, block_);
    }

    private static void registerBlock(int id, String textualID, Block block_)
    {
        registerBlock(id, new ResourceLocation("frazionz", textualID), block_);
    }
}
