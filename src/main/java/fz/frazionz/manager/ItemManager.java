package fz.frazionz.manager;

import fz.frazionz.block.BlockDarkAndesite;
import fz.frazionz.block.BlockMoreAndesiteSmoothVariant;
import fz.frazionz.block.BlockMoreAndesiteVariant;
import fz.frazionz.block.BlockMoreDioriteSmoothVariant;
import fz.frazionz.block.BlockMoreDioriteVariant;
import fz.frazionz.block.BlockMoreGraniteSmoothVariant;
import fz.frazionz.block.BlockMoreGraniteVariant;
import fz.frazionz.block.BlockMoreNWBVariant;
import fz.frazionz.block.BlockMoreSandstoneVariant;
import fz.frazionz.block.BlockSmoothDarkAndesite;
import fz.frazionz.item.ItemBanana;
import fz.frazionz.item.ItemBauxiteApple;
import fz.frazionz.item.ItemBigXp;
import fz.frazionz.item.ItemBlockCobblestoneCompact;
import fz.frazionz.item.ItemBlockLore;
import fz.frazionz.item.ItemBottleXP;
import fz.frazionz.item.ItemDonuts;
import fz.frazionz.item.ItemDynamite;
import fz.frazionz.item.ItemDynamiteArrow;
import fz.frazionz.item.ItemEffect;
import fz.frazionz.item.ItemFrazionApple;
import fz.frazionz.item.ItemFrazionHoe;
import fz.frazionz.item.ItemHammer;
import fz.frazionz.item.ItemLegendaryAxe;
import fz.frazionz.item.ItemLegendaryDagger;
import fz.frazionz.item.ItemLegendaryScythe;
import fz.frazionz.item.ItemLegendarySword;
import fz.frazionz.item.ItemLore;
import fz.frazionz.item.ItemMultiTool;
import fz.frazionz.item.ItemOnyxApple;
import fz.frazionz.item.ItemPizza;
import fz.frazionz.item.ItemShulkerTrophy;
import fz.frazionz.item.ItemSkeletonTrophy;
import fz.frazionz.item.ItemSpawnerPickaxe;
import fz.frazionz.item.ItemUltraBow;
import fz.frazionz.item.ItemWitheredBoneMeal;
import fz.frazionz.item.ItemYelliteApple;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.resources.ResourceLocation;

public class ItemManager {

	public static void registerItems() {
		
		// ORE //
        registerItem(1000, "yellite", (new ItemLore(" ","\u00A73\u00bb \u00A7bDu plus fort au plus faible \u00A73\u00ab"," ", "\u00A7d\u00bb Frazion", "\u00A78\u00bb Onyx", "\u00A76\u00bb Bauxite", "\u00A7e\u00A7l\u00bb Yellite \u00ab", " ")).setFull3D().setTranslationKey("yellite").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1001, "bauxite", (new ItemLore(" ","\u00A73\u00bb \u00A7bDu plus fort au plus faible \u00A73\u00ab", " ","\u00A7d\u00bb Frazion", "\u00A78\u00bb Onyx", "\u00A76\u00A7l\u00bb Bauxite \u00ab", "\u00A7e\u00bb Yellite", " ")).setFull3D().setTranslationKey("bauxite").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1002, "onyx", (new ItemLore(" ","\u00A73\u00bb \u00A7bDu plus fort au plus faible \u00A73\u00ab", " ","\u00A7d\u00bb Frazion", "\u00A78\u00A7l\u00bb Onyx \u00ab", "\u00A76\u00bb Bauxite", "\u00A7e\u00bb Yellite", " ")).setFull3D().setTranslationKey("onyx").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1003, "frazion_powder", (new ItemLore(" ","\u00A73\u00bb \u00A7bDu plus fort au plus faible \u00A73\u00ab", " ", "\u00A7d\u00A7l\u00bb Frazion \u00ab", "\u00A78\u00bb Onyx", "\u00A76\u00bb Bauxite", "\u00A7e\u00bb Yellite", " ")).setFull3D().setTranslationKey("frazion_powder").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1004, "frazion", (new ItemLore(" ","\u00A73\u00bb \u00A7bDu plus fort au plus faible \u00A73\u00ab", " ", "\u00A7d\u00A7l\u00bb Frazion \u00ab", "\u00A78\u00bb Onyx", "\u00A76\u00bb Bauxite", "\u00A7e\u00bb Yellite", " ")).setFull3D().setTranslationKey("frazion").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1005, "cosmic_powder", (new Item()).setFull3D().setTranslationKey("cosmic_powder").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1006, "cosmic_nugget", (new Item()).setFull3D().setTranslationKey("cosmic_nugget").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1007, "cosmic_ingot", (new Item()).setFull3D().setTranslationKey("cosmic_ingot").setCreativeTab(CreativeTabs.MATERIALS));
        // ARMOR //
        registerItem(1008, "yellite_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.YELLITE, 5, EntityEquipmentSlot.HEAD)).setTranslationKey("yellite_helmet"));
        registerItem(1009, "yellite_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.YELLITE, 5, EntityEquipmentSlot.CHEST)).setTranslationKey("yellite_chestplate"));
        registerItem(1010, "yellite_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.YELLITE, 5, EntityEquipmentSlot.LEGS)).setTranslationKey("yellite_leggings"));
        registerItem(1011, "yellite_boots", (new ItemArmor(ItemArmor.ArmorMaterial.YELLITE, 5, EntityEquipmentSlot.FEET)).setTranslationKey("yellite_boots"));
        registerItem(1012, "yellite_sword", (new ItemSword(Item.ToolMaterial.YELLITE)).setTranslationKey("yellite_sword"));
        registerItem(1013, "yellite_shovel", (new ItemSpade(Item.ToolMaterial.YELLITE)).setTranslationKey("yellite_shovel"));
        registerItem(1014, "yellite_pickaxe", (new ItemPickaxe(Item.ToolMaterial.YELLITE)).setTranslationKey("yellite_pickaxe"));
        registerItem(1015, "yellite_axe", (new ItemAxe(Item.ToolMaterial.YELLITE)).setTranslationKey("yellite_axe"));
        registerItem(1016, "yellite_hoe", (new ItemHoe(Item.ToolMaterial.YELLITE)).setTranslationKey("yellite_hoe"));
        registerItemBlock(Blocks.YELLITE_BLOCK);
        registerItemBlock(Blocks.YELLITE_ORE);
        registerItem(1017, "bauxite_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.BAUXITE, 6, EntityEquipmentSlot.HEAD)).setTranslationKey("bauxite_helmet"));
        registerItem(1018, "bauxite_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.BAUXITE, 6, EntityEquipmentSlot.CHEST)).setTranslationKey("bauxite_chestplate"));
        registerItem(1019, "bauxite_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.BAUXITE, 6, EntityEquipmentSlot.LEGS)).setTranslationKey("bauxite_leggings"));
        registerItem(1020, "bauxite_boots", (new ItemArmor(ItemArmor.ArmorMaterial.BAUXITE, 6, EntityEquipmentSlot.FEET)).setTranslationKey("bauxite_boots"));
        registerItem(1021, "bauxite_sword", (new ItemSword(Item.ToolMaterial.BAUXITE)).setTranslationKey("bauxite_sword"));
        registerItem(1022, "bauxite_shovel", (new ItemSpade(Item.ToolMaterial.BAUXITE)).setTranslationKey("bauxite_shovel"));
        registerItem(1023, "bauxite_pickaxe", (new ItemPickaxe(Item.ToolMaterial.BAUXITE)).setTranslationKey("bauxite_pickaxe"));
        registerItem(1024, "bauxite_axe", (new ItemAxe(Item.ToolMaterial.BAUXITE)).setTranslationKey("bauxite_axe"));
        registerItem(1025, "bauxite_hoe", (new ItemHoe(Item.ToolMaterial.BAUXITE)).setTranslationKey("bauxite_hoe"));
        registerItemBlock(Blocks.BAUXITE_ORE);
        registerItemBlock(Blocks.BAUXITE_BLOCK);
        registerItem(1026, "onyx_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.ONYX, 7, EntityEquipmentSlot.HEAD)).setTranslationKey("onyx_helmet"));
        registerItem(1027, "onyx_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.ONYX, 7, EntityEquipmentSlot.CHEST)).setTranslationKey("onyx_chestplate"));
        registerItem(1028, "onyx_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.ONYX, 7, EntityEquipmentSlot.LEGS)).setTranslationKey("onyx_leggings"));
        registerItem(1029, "onyx_boots", (new ItemArmor(ItemArmor.ArmorMaterial.ONYX, 7, EntityEquipmentSlot.FEET)).setTranslationKey("onyx_boots"));
        registerItem(1030, "onyx_sword", (new ItemSword(Item.ToolMaterial.ONYX)).setTranslationKey("onyx_sword"));
        registerItem(1031, "onyx_shovel", (new ItemSpade(Item.ToolMaterial.ONYX)).setTranslationKey("onyx_shovel"));
        registerItem(1032, "onyx_pickaxe", (new ItemPickaxe(Item.ToolMaterial.ONYX)).setTranslationKey("onyx_pickaxe"));
        registerItem(1033, "onyx_axe", (new ItemAxe(Item.ToolMaterial.ONYX)).setTranslationKey("onyx_axe"));
        registerItem(1034, "onyx_hoe", (new ItemHoe(Item.ToolMaterial.ONYX)).setTranslationKey("onyx_hoe"));
        registerItemBlock(Blocks.ONYX_ORE);
        registerItemBlock(Blocks.ONYX_BLOCK);
        registerItem(1035, "frazion_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_45, 8, EntityEquipmentSlot.HEAD)).setTranslationKey("frazion_helmet"));
        registerItem(1036, "frazion_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_45, 8, EntityEquipmentSlot.CHEST)).setTranslationKey("frazion_chestplate"));
        registerItem(1037, "frazion_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_45, 8, EntityEquipmentSlot.LEGS)).setTranslationKey("frazion_leggings"));
        registerItem(1038, "frazion_boots", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_45, 8, EntityEquipmentSlot.FEET)).setTranslationKey("frazion_boots"));
        registerItem(1039, "frazion_sword", (new ItemSword(Item.ToolMaterial.FRAZION)).setTranslationKey("frazion_sword"));
        registerItem(1040, "frazion_shovel", (new ItemSpade(Item.ToolMaterial.FRAZION)).setTranslationKey("frazion_shovel"));
        registerItem(1041, "frazion_pickaxe", (new ItemPickaxe(Item.ToolMaterial.FRAZION)).setTranslationKey("frazion_pickaxe"));
        registerItem(1042, "frazion_axe", (new ItemAxe(Item.ToolMaterial.FRAZION)).setTranslationKey("frazion_axe"));
        registerItem(1043, "frazion_hoe", (new ItemFrazionHoe(Item.ToolMaterial.FRAZION)).setTranslationKey("frazion_hoe"));
        registerItemBlock(Blocks.FRAZION_ORE);
        registerItemBlock(Blocks.FRAZION_BLOCK);
        registerItem(1044, "ultra_bow", (new ItemUltraBow()).setTranslationKey("ultra_bow"));
        
        registerItem(1057, "fz_record_1", (new ItemRecord("fz1", SoundEvents.FZ_RECORD_1)).setTranslationKey("fz_record_1"));
        registerItem(1058, "fz_record_2", (new ItemRecord("fz2", SoundEvents.FZ_RECORD_2)).setTranslationKey("fz_record_2"));
        registerItem(1059, "fz_record_3", (new ItemRecord("fz3", SoundEvents.FZ_RECORD_3)).setTranslationKey("fz_record_3"));
        
        registerItemBlock(Blocks.Z_HOPPER, (new ItemBlockLore(Blocks.Z_HOPPER, " ", "\u00A76\u00bb \u00A7ePermet de récupérer sur une zone de 9x9x4.5"," ","\u00A76\u00bb \u00A7eCapacit\u00E9 : \u00A7618 Slots"," ")));
        registerItemBlock(Blocks.DIRT_CHEST, (new ItemBlockLore(Blocks.DIRT_CHEST, " ", "\u00A76\u00bb \u00A7eCapacit\u00E9 : \u00A761024 Slots", " ")));
        registerItemBlock(Blocks.YELLITE_CHEST, (new ItemBlockLore(Blocks.YELLITE_CHEST, " ", "\u00A76\u00bb \u00A7eCapacit\u00E9 : \u00A7654 Slots", " ")));
        registerItemBlock(Blocks.BAUXITE_CHEST, (new ItemBlockLore(Blocks.BAUXITE_CHEST, " ", "\u00A76\u00bb \u00A7eCapacit\u00E9 : \u00A7672 Slots", " ")));
        registerItemBlock(Blocks.ONYX_CHEST, (new ItemBlockLore(Blocks.ONYX_CHEST, " ", "\u00A76\u00bb \u00A7eCapacit\u00E9 : \u00A7696 Slots", " ")));
        registerItemBlock(Blocks.FRAZION_CHEST, (new ItemBlockLore(Blocks.FRAZION_CHEST, " ", "\u00A76\u00bb \u00A7eCapacit\u00E9 : \u00A76144 Slots", " ")));
        registerItem(1060, "frazion_hammer", (new ItemHammer(Item.ToolMaterial.FRAZION_HAMMER)).setTranslationKey("frazion_hammer"));
        registerItem(1061, "yellite_multitool", (new ItemMultiTool(Item.ToolMaterial.YELLITE)).setTranslationKey("yellite_multitool"));
        registerItem(1062, "bauxite_multitool", (new ItemMultiTool(Item.ToolMaterial.BAUXITE)).setTranslationKey("bauxite_multitool"));
        registerItem(1063, "onyx_multitool", (new ItemMultiTool(Item.ToolMaterial.ONYX)).setTranslationKey("onyx_multitool"));
        registerItem(1064, "frazion_multitool", (new ItemMultiTool(Item.ToolMaterial.FRAZION)).setTranslationKey("frazion_multitool"));
        registerItemBlock(Blocks.YELLITE_FURNACE, (new ItemBlockLore(Blocks.YELLITE_FURNACE, " ", "\u00A76\u00bb \u00A7eCuisson Rapide", "\u00A76\u00bb \u00A7e2 Slots", " ")));
        registerItemBlock(Blocks.BAUXITE_FURNACE, (new ItemBlockLore(Blocks.BAUXITE_FURNACE, " ", "\u00A76\u00bb \u00A7eCuisson Rapide", "\u00A76\u00bb \u00A7e3 Slots", " ")));
        registerItemBlock(Blocks.ONYX_FURNACE, (new ItemBlockLore(Blocks.ONYX_FURNACE, " ", "\u00A76\u00bb \u00A7eCuisson Ultra Rapide", "\u00A76\u00bb \u00A7e4 Slots", " ")));
        registerItemBlock(Blocks.FRAZION_FURNACE, (new ItemBlockLore(Blocks.FRAZION_FURNACE, " ", "\u00A76\u00bb \u00A7eCuisson ULTRA Rapide", "\u00A76\u00bb \u00A7e6 Slots", "\u00A76\u00bb \u00A7eNe fonctionne qu'avec de la Blaze Rod", " ")));
        registerItemBlock(Blocks.CRISTAL_ROUGE);
        registerItemBlock(Blocks.CRISTAL_JAUNE);
        registerItemBlock(Blocks.CRISTAL_VIOLET);
        registerItemBlock(Blocks.CRISTAL_VERT);
        registerItemBlock(Blocks.CRISTAL_BLEU);
        registerItemBlock(Blocks.OBSIDIAN_YELLITE, (new ItemBlock(Blocks.OBSIDIAN_YELLITE)));
        registerItemBlock(Blocks.OBSIDIAN_BAUXITE, (new ItemBlock(Blocks.OBSIDIAN_BAUXITE)));
        registerItemBlock(Blocks.OBSIDIAN_ONYX, (new ItemBlock(Blocks.OBSIDIAN_ONYX)));
        registerItemBlock(Blocks.OBSIDIAN_FRAZION, (new ItemBlock(Blocks.OBSIDIAN_FRAZION)));
        registerItemBlock(Blocks.Z_TNT);
        registerItemBlock(Blocks.BAUXITE_BLOCK);
        registerItem(1065, "key_farm", (new Item()).setFull3D().setTranslationKey("key_farm").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1066, "key_vote", (new Item()).setFull3D().setTranslationKey("key_vote").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1067, "key_common", (new Item()).setFull3D().setTranslationKey("key_common").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1068, "key_rare", (new Item()).setFull3D().setTranslationKey("key_rare").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1069, "key_legendary", (new Item()).setFull3D().setTranslationKey("key_legendary").setCreativeTab(CreativeTabs.MATERIALS));

        registerItem(1078, "strawberry", (new ItemSeedFood(2, 1.0F, Blocks.STRAWBERRIES, Blocks.FARMLAND)).setTranslationKey("strawberry"));
        registerItem(1079, "banana", (new ItemBanana(4, 2.5F, true)).setAlwaysEdible().setTranslationKey("banana"));
        registerItem(1080, "pizza", (new ItemPizza(10, 2.5F, true)).setAlwaysEdible().setTranslationKey("pizza"));
        registerItem(1081, "donuts", (new ItemDonuts(4, 2.0F, false)).setAlwaysEdible().setTranslationKey("donuts"));
        
        registerItem(1082, "yellite_apple", (new ItemYelliteApple(4, 2.0F, false)).setAlwaysEdible().setTranslationKey("yellite_apple"));
        registerItem(1083, "bauxite_apple", (new ItemBauxiteApple(4, 2.0F, false)).setAlwaysEdible().setTranslationKey("bauxite_apple"));
        registerItem(1084, "onyx_apple", (new ItemOnyxApple(4, 2.0F, false)).setAlwaysEdible().setTranslationKey("onyx_apple"));
        registerItem(1085, "frazion_apple", (new ItemFrazionApple(4, 2.0F, false)).setAlwaysEdible().setTranslationKey("frazion_apple"));
        
        registerItemBlock(Blocks.BAUXITE_LADDER);
        registerItemBlock(Blocks.YELLITE_LADDER);
        registerItemBlock(Blocks.ONYX_LADDER);
        registerItemBlock(Blocks.FRAZION_LADDER, (new ItemBlockLore(Blocks.FRAZION_LADDER, " ", "\u00A76\u00bb \u00A7e\u00A7eTa destin\u00E9, est, d'ALLER VERS L'ESPACE !", " ")));
        
        registerItemBlock(Blocks.CRIMSON_LOG);
        registerItemBlock(Blocks.CRIMSON_ROOTS);
        registerItemBlock(Blocks.CRIMSON_FUNGI);
        
        registerItemBlock(Blocks.NETHER_WART_BLOCK2, (new ItemMultiTexture(Blocks.NETHER_WART_BLOCK2, Blocks.NETHER_WART_BLOCK2, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockMoreNWBVariant.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("nether_wart_block"));
        
        registerItemBlock(Blocks.SANDSTONE2, (new ItemMultiTexture(Blocks.SANDSTONE2, Blocks.SANDSTONE2, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockMoreSandstoneVariant.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("sandstone"));
        
        
        
        registerItemBlock(Blocks.STONE_ANDESITE, (new ItemMultiTexture(Blocks.STONE_ANDESITE, Blocks.STONE_ANDESITE, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockMoreAndesiteVariant.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("stone_andesite"));
        
        registerItemBlock(Blocks.STONE_ANDESITE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_ANDESITE_SMOOTH, Blocks.STONE_ANDESITE_SMOOTH, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockMoreAndesiteSmoothVariant.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("stone_andesite_smooth"));
        
        
        
        registerItemBlock(Blocks.STONE_GRANITE, (new ItemMultiTexture(Blocks.STONE_GRANITE, Blocks.STONE_GRANITE, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockMoreGraniteVariant.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("stone_granite"));
        
        registerItemBlock(Blocks.STONE_GRANITE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_GRANITE_SMOOTH, Blocks.STONE_GRANITE_SMOOTH, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockMoreGraniteSmoothVariant.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("stone_granite_smooth"));
        
        
        
        registerItemBlock(Blocks.STONE_DIORITE, (new ItemMultiTexture(Blocks.STONE_DIORITE, Blocks.STONE_DIORITE, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockMoreDioriteVariant.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("stone_diorite"));
        
        registerItemBlock(Blocks.STONE_DIORITE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_DIORITE_SMOOTH, Blocks.STONE_DIORITE_SMOOTH, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockMoreDioriteSmoothVariant.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("stone_diorite_smooth"));
        
        
        registerItemBlock(Blocks.RANDOM_ORE, (new ItemBlockLore(Blocks.RANDOM_ORE, " ", "\u00A76\u00bb \u00A7e\u00A7nChance d'obtenir les minerais : ", " ", "\u00A76\u00bb \u00A7eYellite :\u00A76 53.49%", "\u00A76\u00bb \u00A7eBauxite :\u00A76 35%", "\u00A76\u00bb \u00A7eOnyx :\u00A76 10%", "\u00A76\u00bb \u00A7eFrazion Powder :\u00A76 1%", "\u00A76\u00bb \u00A7eCosmic Powder :\u00A76 0.5%", "\u00A76\u00bb \u00A7eCosmic Nugget :\u00A76 0.01%", " ")));
        
        registerItemBlock(Blocks.AMELIORATOR);
        
        registerItem(1086, "nether_string", (new Item()).setFull3D().setTranslationKey("nether_string").setCreativeTab(CreativeTabs.MATERIALS));        
        registerItem(1087, "renforced_string", (new Item()).setFull3D().setTranslationKey("renforced_string").setCreativeTab(CreativeTabs.MATERIALS));
        
        registerItem(1095, "big_xp", (new ItemBigXp()).setTranslationKey("big_xp").setCreativeTab(CreativeTabs.MATERIALS));
        
        registerItem(1096, "loot_powder", (new ItemLore()).setFull3D().setTranslationKey("loot_powder").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1097, "farm_powder", (new ItemLore()).setFull3D().setTranslationKey("farm_powder").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1098, "farm_nugget", (new ItemLore()).setFull3D().setTranslationKey("farm_nugget").setCreativeTab(CreativeTabs.MATERIALS));
        
        registerItem(1099, "yellite_stick", (new Item()).setFull3D().setTranslationKey("yellite_stick").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1100, "bauxite_stick", (new Item()).setFull3D().setTranslationKey("bauxite_stick").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1101, "onyx_stick", (new Item()).setFull3D().setTranslationKey("onyx_stick").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1102, "frazion_stick", (new Item()).setFull3D().setTranslationKey("frazion_stick").setCreativeTab(CreativeTabs.MATERIALS));
        
        registerItem(1103, "frazion_helmet_70", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_70, 9, EntityEquipmentSlot.HEAD)).setTranslationKey("frazion_helmet_70"));
        registerItem(1104, "frazion_chestplate_70", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_70, 9, EntityEquipmentSlot.CHEST)).setTranslationKey("frazion_chestplate_70"));
        registerItem(1105, "frazion_leggings_70", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_70, 9, EntityEquipmentSlot.LEGS)).setTranslationKey("frazion_leggings_70"));
        registerItem(1106, "frazion_boots_70", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_70, 9, EntityEquipmentSlot.FEET)).setTranslationKey("frazion_boots_70"));
        
        registerItem(1107, "frazion_helmet_100", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_100, 10, EntityEquipmentSlot.HEAD)).setTranslationKey("frazion_helmet_100"));
        registerItem(1108, "frazion_chestplate_100", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_100, 10, EntityEquipmentSlot.CHEST)).setTranslationKey("frazion_chestplate_100"));
        registerItem(1109, "frazion_leggings_100", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_100, 10, EntityEquipmentSlot.LEGS)).setTranslationKey("frazion_leggings_100"));
        registerItem(1110, "frazion_boots_100", (new ItemArmor(ItemArmor.ArmorMaterial.FRAZION_100, 10, EntityEquipmentSlot.FEET)).setTranslationKey("frazion_boots_100"));
        
        registerItem(1111, "travelers_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.TRAVELERS, 11, EntityEquipmentSlot.HEAD)).setTranslationKey("travelers_helmet"));
        registerItem(1112, "travelers_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.TRAVELERS, 11, EntityEquipmentSlot.CHEST)).setTranslationKey("travelers_chestplate"));
        registerItem(1113, "travelers_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.TRAVELERS, 11, EntityEquipmentSlot.LEGS)).setTranslationKey("travelers_leggings"));
        registerItem(1114, "travelers_boots", (new ItemArmor(ItemArmor.ArmorMaterial.TRAVELERS, 11, EntityEquipmentSlot.FEET)).setTranslationKey("travelers_boots"));
        
        registerItem(1115, "legendary_axe", (new ItemLegendaryAxe(Item.ToolMaterial.LEGENDARY_AXE)).setTranslationKey("legendary_axe"));
        registerItem(1116, "legendary_sword", (new ItemLegendarySword(Item.ToolMaterial.LEGENDARY_SWORD)).setTranslationKey("legendary_sword"));
        registerItem(1117, "legendary_dagger", (new ItemLegendaryDagger(Item.ToolMaterial.LEGENDARY_DAGGER)).setTranslationKey("legendary_dagger"));
        
        registerItem(1118, "fz_record_4", (new ItemRecord("fz4", SoundEvents.FZ_RECORD_4)).setTranslationKey("fz_record_4"));
        registerItem(1119, "fz_record_5", (new ItemRecord("fz5", SoundEvents.FZ_RECORD_5)).setTranslationKey("fz_record_5"));
        registerItem(1120, "fz_record_6", (new ItemRecord("fz6", SoundEvents.FZ_RECORD_6)).setTranslationKey("fz_record_6"));
        registerItem(1121, "fz_record_7", (new ItemRecord("fz7", SoundEvents.FZ_RECORD_7)).setTranslationKey("fz_record_7"));
        registerItem(1122, "fz_record_8", (new ItemRecord("fz8", SoundEvents.FZ_RECORD_8)).setTranslationKey("fz_record_8"));
        registerItem(1123, "fz_record_9", (new ItemRecord("fz9", SoundEvents.FZ_RECORD_9)).setTranslationKey("fz_record_9"));
        registerItem(1124, "fz_record_10", (new ItemRecord("fz10", SoundEvents.FZ_RECORD_10)).setTranslationKey("fz_record_10"));
        
        registerItem(1125, "legendary_scythe", (new ItemLegendaryScythe(Item.ToolMaterial.LEGENDARY_SCYTHE)).setTranslationKey("legendary_scythe"));
        
        registerItem(1200, "dynamite", (new ItemDynamite()).setTranslationKey("dynamite").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1201, "dynamite_arrow", (new ItemDynamiteArrow()).setTranslationKey("dynamite_arrow"));
        
        registerItem(1205, "spawner_pickaxe", (new ItemSpawnerPickaxe()).setTranslationKey("spawner_pickaxe"));
        
        registerItem(1206, "frazion_dagger", (new ItemSword(Item.ToolMaterial.FRAZION_DAGGER)).setTranslationKey("frazion_dagger"));
        
        registerItem(1207, "billet", (new Item()).setTranslationKey("billet").setCreativeTab(CreativeTabs.MATERIALS));
        
        registerItem(1208, "farm_sword", (new ItemSword(Item.ToolMaterial.FARM_SWORD)).setTranslationKey("farm_sword"));
        
        registerItem(1209, "yellite_brewing_stand", (new ItemBlockSpecial(Blocks.YELLITE_BREWING_STAND)).setTranslationKey("yellite_brewing_stand").setCreativeTab(CreativeTabs.BREWING));
        registerItem(1210, "bauxite_brewing_stand", (new ItemBlockSpecial(Blocks.BAUXITE_BREWING_STAND)).setTranslationKey("bauxite_brewing_stand").setCreativeTab(CreativeTabs.BREWING));
        registerItem(1211, "onyx_brewing_stand", (new ItemBlockSpecial(Blocks.ONYX_BREWING_STAND)).setTranslationKey("onyx_brewing_stand").setCreativeTab(CreativeTabs.BREWING));
        registerItem(1212, "frazion_brewing_stand", (new ItemBlockSpecial(Blocks.FRAZION_BREWING_STAND)).setTranslationKey("frazion_brewing_stand").setCreativeTab(CreativeTabs.BREWING));
        
        registerItemBlock(Blocks.RENFORCED_SAND, (new ItemBlock(Blocks.RENFORCED_SAND)));
        
        registerItem(1213, "obsidian_tower", (new Item()).setFull3D().setTranslationKey("obsidian_tower").setCreativeTab(CreativeTabs.MATERIALS));
        
        registerItem(1214, "booster_xp", (new ItemEffect()).setFull3D().setTranslationKey("booster_xp").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1215, "booster_aptitude", (new ItemEffect()).setFull3D().setTranslationKey("booster_aptitude").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1216, "booster_repair", (new ItemEffect()).setFull3D().setTranslationKey("booster_repair").setCreativeTab(CreativeTabs.MATERIALS));
        
        registerItem(1217, "withered_bone", (new Item()).setTranslationKey("withered_bone").setFull3D().setCreativeTab(CreativeTabs.MISC));
        registerItem(1218, "withered_bone_meal", (new ItemWitheredBoneMeal()).setTranslationKey("withered_bone_meal").setFull3D().setCreativeTab(CreativeTabs.MISC));
        
        registerItem(1219, "bottle_xp", (new ItemBottleXP()).setTranslationKey("bottle_xp"));
        registerItem(1220, "faction_token", (new ItemLore(" ", "\u00A76\u00bb \u00A7eClique droit pour ajouter le Token à ta Faction.", " ")).setTranslationKey("faction_token").setCreativeTab(CreativeTabs.MATERIALS));
        
        registerItemBlock(Blocks.WITHER_BLOCK, (new ItemBlockLore(Blocks.WITHER_BLOCK, " ", "\u00A76\u00bb \u00A7eIndestructible aux Wither", " ", "\u00A76\u00bb \u00A7e\u00A7nChance de destruction par Tnt", " ", "\u00A76\u00bb \u00A7eTnt :\u00A76 5%", "\u00A76\u00bb \u00A7eZ Tnt :\u00A76 100%", " ")));
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X1, (new ItemBlockCobblestoneCompact(Blocks.COMPACT_COBBLESTONE_X1, 1)));
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X2, (new ItemBlockCobblestoneCompact(Blocks.COMPACT_COBBLESTONE_X2, 2)));
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X3, (new ItemBlockCobblestoneCompact(Blocks.COMPACT_COBBLESTONE_X3, 3)));
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X4, (new ItemBlockCobblestoneCompact(Blocks.COMPACT_COBBLESTONE_X4, 4)));
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X5, (new ItemBlockCobblestoneCompact(Blocks.COMPACT_COBBLESTONE_X5, 5)));
        
        registerItemBlock(Blocks.REVERSE_FALL_BLOCK, (new ItemBlockLore(Blocks.REVERSE_FALL_BLOCK, " ", "\u00A76\u00bb \u00A7eAucun dégats chute en tombant dessus.", "\u00A76\u00bb \u00A7eCependant, si vous avez l'effet No Fall, vous en prendrez.", " ")));
    
        registerItemBlock(Blocks.STONE_BLACKSTONE, (new ItemMultiTexture(Blocks.STONE_BLACKSTONE, Blocks.STONE_BLACKSTONE, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockDarkAndesite.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("stone_blackstone"));
        registerItemBlock(Blocks.STONE_BLACKSTONE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_BLACKSTONE_SMOOTH, Blocks.STONE_BLACKSTONE_SMOOTH, new ItemMultiTexture.Mapper()
        {
            public String apply(ItemStack p_apply_1_)
            {
                return BlockSmoothDarkAndesite.VariantType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
            }
        })).setTranslationKey("stone_blackstone_smooth"));
        
        registerItemBlock(Blocks.BLOCK_PLACER_CHEST);
        registerItemBlock(Blocks.BLOCK_PLACER_TRAPCHEST);
        
        registerItemBlock(Blocks.TROPHY_FORGE);
        registerItemBlock(Blocks.ITEM_CRUSHER);
        registerItemBlock(Blocks.GRIMOIRE_PEDESTAL);
		
        registerItem(1250, "trophy_bat", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_bat"));
        registerItem(1251, "trophy_blaze", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_blaze"));
        registerItem(1252, "trophy_creeper", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_creeper"));
        registerItem(1253, "trophy_enderman", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_enderman"));
        registerItem(1254, "trophy_ghast", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_ghast"));
        registerItem(1255, "trophy_guardian", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_guardian"));
        registerItem(1256, "trophy_villager", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_villager"));
        registerItem(1257, "trophy_shulker", (new ItemShulkerTrophy()).setFull3D().setTranslationKey("trophy_shulker"));
        registerItem(1258, "trophy_spider", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_spider"));
        registerItem(1259, "trophy_skeleton", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_skeleton"));
        registerItem(1260, "trophy_slime", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_slime"));
        registerItem(1261, "trophy_squid", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_squid"));
        registerItem(1262, "trophy_pig", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_pig"));
        registerItem(1263, "trophy_sheep", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_sheep"));
        registerItem(1264, "trophy_iron_golem", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_iron_golem"));
        registerItem(1265, "trophy_silverfish", (new ItemSkeletonTrophy()).setFull3D().setTranslationKey("trophy_silverfish"));
        
        registerItem(1300, "rune_anti_malus", (new Item()).setFull3D().setTranslationKey("rune_anti_malus").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1301, "rune_bonus", (new Item()).setFull3D().setTranslationKey("rune_bonus").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1302, "rune_chance", (new Item()).setFull3D().setTranslationKey("rune_chance").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1303, "rune_damage", (new Item()).setFull3D().setTranslationKey("rune_damage").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1304, "rune_health", (new Item()).setFull3D().setTranslationKey("rune_health").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1305, "rune_mining", (new Item()).setFull3D().setTranslationKey("rune_mining").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1306, "rune_regeneration", (new Item()).setFull3D().setTranslationKey("rune_regeneration").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1307, "rune_resistance", (new Item()).setFull3D().setTranslationKey("rune_resistance").setCreativeTab(CreativeTabs.MATERIALS));
        registerItem(1308, "rune_speed", (new Item()).setFull3D().setTranslationKey("rune_speed").setCreativeTab(CreativeTabs.MATERIALS));

        registerItem(1320, "iron_tooth", (new Item()).setFull3D().setTranslationKey("iron_tooth").setCreativeTab(CreativeTabs.MATERIALS));
        
	}
	
	
    /**
     * Register a default ItemBlock for the given Block.
     */
    private static void registerItemBlock(Block blockIn)
    {
        registerItemBlock(blockIn, new ItemBlock(blockIn));
    }

    /**
     * Register the given Item as the ItemBlock for the given Block.
     */
    protected static void registerItemBlock(Block blockIn, Item itemIn)
    {
        registerItem(Block.getIdFromBlock(blockIn), Block.REGISTRY.getNameForObject(blockIn), itemIn);
        Item.BLOCK_TO_ITEM.put(blockIn, itemIn);
    }

    private static void registerItem(int id, String textualID, Item itemIn)
    {
        registerItem(id, new ResourceLocation("frazionz", textualID), itemIn);
    }

    private static void registerItem(int id, ResourceLocation textualID, Item itemIn)
    {
        Item.REGISTRY.register(id, textualID, itemIn);
    }
	
}
