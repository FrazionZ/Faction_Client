package net.minecraft.util.datafix.walkers;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockEntityTag implements IDataWalker
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<String, String> NEW_TO_OLD_ID_MAP = Maps.<String, String>newHashMap();
    private static final Map<String, String> ITEM_ID_TO_BLOCK_ENTITY_ID = Maps.<String, String>newHashMap();

    @Nullable
    private static String getBlockEntityID(int blockID, String p_188267_1_)
    {
        return blockID < 515 ? (String)NEW_TO_OLD_ID_MAP.get((new ResourceLocation(p_188267_1_)).toString()) : (String)ITEM_ID_TO_BLOCK_ENTITY_ID.get((new ResourceLocation(p_188267_1_)).toString());
    }

    public NBTTagCompound process(IDataFixer fixer, NBTTagCompound compound, int versionIn)
    {
        if (!compound.hasKey("tag", 10))
        {
            return compound;
        }
        else
        {
            NBTTagCompound nbttagcompound = compound.getCompoundTag("tag");

            if (nbttagcompound.hasKey("BlockEntityTag", 10))
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("BlockEntityTag");
                String s = compound.getString("id");
                String s1 = getBlockEntityID(versionIn, s);
                boolean flag;

                if (s1 == null)
                {
                    LOGGER.warn("Unable to resolve BlockEntity for ItemInstance: {}", (Object)s);
                    flag = false;
                }
                else
                {
                    flag = !nbttagcompound1.hasKey("id");
                    nbttagcompound1.setString("id", s1);
                }

                fixer.process(FixTypes.BLOCK_ENTITY, nbttagcompound1, versionIn);

                if (flag)
                {
                    nbttagcompound1.removeTag("id");
                }
            }

            return compound;
        }
    }

    static
    {
        Map<String, String> map = NEW_TO_OLD_ID_MAP;
        map.put("minecraft:furnace", "Furnace");
        map.put("minecraft:lit_furnace", "Furnace");
        
        map.put("minecraft:yellite_furnace", "yellite_furnace");
        map.put("minecraft:lit_yellite_furnace", "yellite_furnace");
        map.put("minecraft:bauxite_furnace", "bauxite_furnace");
        map.put("minecraft:lit_bauxite_furnace", "bauxite_furnace");
        map.put("minecraft:onyx_furnace", "onyx_furnace");
        map.put("minecraft:lit_onyx_furnace", "onyx_furnace");
        map.put("minecraft:frazion_furnace", "frazion_furnace");
        map.put("minecraft:lit_frazion_furnace", "frazion_furnace");
        map.put("frazionz:ameliorator", "ameliorator");
        map.put("minecraft:trophy_forge", "trophy_forge");
        map.put("minecraft:grimoire_pedestal", "grimoire_pedestal");
        
        map.put("minecraft:chest", "Chest");
        
        map.put("minecraft:dirt_chest", "dirt_chest");
        map.put("minecraft:yellite_chest", "yellite_chest");
        map.put("minecraft:bauxite_chest", "bauxite_chest");
        map.put("minecraft:frazion_chest", "frazion_chest");
        map.put("minecraft:hdv_chest", "hdv_chest");
        map.put("minecraft:onyx_chest", "onyx_chest");
        
        map.put("minecraft:trapped_chest", "Chest");
        map.put("minecraft:ender_chest", "EnderChest");
        map.put("minecraft:jukebox", "RecordPlayer");
        map.put("minecraft:dispenser", "Trap");
        map.put("minecraft:dropper", "Dropper");
        map.put("minecraft:sign", "Sign");
        map.put("minecraft:mob_spawner", "MobSpawner");
        map.put("minecraft:noteblock", "Music");
        map.put("minecraft:brewing_stand", "Cauldron");
        map.put("minecraft:enhanting_table", "EnchantTable");
        map.put("minecraft:command_block", "CommandBlock");
        map.put("minecraft:beacon", "Beacon");
        map.put("minecraft:skull", "Skull");
        map.put("minecraft:daylight_detector", "DLDetector");
        map.put("minecraft:hopper", "Hopper");
        map.put("minecraft:z_hopper", "z_hopper");

        map.put("minecraft:banner", "Banner");
        map.put("minecraft:flower_pot", "FlowerPot");
        map.put("minecraft:repeating_command_block", "CommandBlock");
        map.put("minecraft:chain_command_block", "CommandBlock");
        map.put("minecraft:standing_sign", "Sign");
        map.put("minecraft:wall_sign", "Sign");
        map.put("minecraft:piston_head", "Piston");
        map.put("minecraft:daylight_detector_inverted", "DLDetector");
        map.put("minecraft:unpowered_comparator", "Comparator");
        map.put("minecraft:powered_comparator", "Comparator");
        map.put("minecraft:wall_banner", "Banner");
        map.put("minecraft:standing_banner", "Banner");
        map.put("minecraft:structure_block", "Structure");
        map.put("minecraft:end_portal", "Airportal");
        map.put("minecraft:end_gateway", "EndGateway");
        map.put("minecraft:shield", "Shield");
        
        map.put("minecraft:spawner_inventory", "spawner_inventory");
        map.put("minecraft:yellite_brewing_stand", "yellite_brewing_stand");
        map.put("minecraft:bauxite_brewing_stand", "bauxite_brewing_stand");
        map.put("minecraft:onyx_brewing_stand", "onyx_brewing_stand");
        map.put("minecraft:frazion_brewing_stand", "frazion_brewing_stand");
        
        map = ITEM_ID_TO_BLOCK_ENTITY_ID;
        map.put("minecraft:furnace", "minecraft:furnace");
        map.put("minecraft:lit_furnace", "minecraft:furnace");
        map.put("minecraft:chest", "minecraft:chest");
        map.put("minecraft:trapped_chest", "minecraft:chest");
        map.put("minecraft:ender_chest", "minecraft:enderchest");
        map.put("minecraft:jukebox", "minecraft:jukebox");
        map.put("minecraft:dispenser", "minecraft:dispenser");
        map.put("minecraft:dropper", "minecraft:dropper");
        map.put("minecraft:sign", "minecraft:sign");
        map.put("minecraft:mob_spawner", "minecraft:mob_spawner");
        map.put("minecraft:noteblock", "minecraft:noteblock");
        map.put("minecraft:brewing_stand", "minecraft:brewing_stand");
        map.put("minecraft:enhanting_table", "minecraft:enchanting_table");
        map.put("minecraft:command_block", "minecraft:command_block");
        map.put("minecraft:beacon", "minecraft:beacon");
        map.put("minecraft:skull", "minecraft:skull");
        map.put("minecraft:daylight_detector", "minecraft:daylight_detector");
        map.put("minecraft:hopper", "minecraft:hopper");
        map.put("minecraft:banner", "minecraft:banner");
        map.put("minecraft:flower_pot", "minecraft:flower_pot");
        map.put("minecraft:repeating_command_block", "minecraft:command_block");
        map.put("minecraft:chain_command_block", "minecraft:command_block");
        map.put("minecraft:shulker_box", "minecraft:shulker_box");
        map.put("minecraft:white_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:orange_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:magenta_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:light_blue_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:yellow_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:lime_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:pink_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:gray_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:silver_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:cyan_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:purple_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:blue_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:brown_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:green_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:red_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:black_shulker_box", "minecraft:shulker_box");
        map.put("minecraft:bed", "minecraft:bed");
        map.put("minecraft:standing_sign", "minecraft:sign");
        map.put("minecraft:wall_sign", "minecraft:sign");
        map.put("minecraft:piston_head", "minecraft:piston");
        map.put("minecraft:daylight_detector_inverted", "minecraft:daylight_detector");
        map.put("minecraft:unpowered_comparator", "minecraft:comparator");
        map.put("minecraft:powered_comparator", "minecraft:comparator");
        map.put("minecraft:wall_banner", "minecraft:banner");
        map.put("minecraft:standing_banner", "minecraft:banner");
        map.put("minecraft:structure_block", "minecraft:structure_block");
        map.put("minecraft:end_portal", "minecraft:end_portal");
        map.put("minecraft:end_gateway", "minecraft:end_gateway");
        map.put("minecraft:shield", "minecraft:shield");
    
        map.put("minecraft:yellite_furnace", "minecraft:yellite_furnace");
        map.put("minecraft:lit_yellite_furnace", "minecraft:yellite_furnace");
        map.put("minecraft:bauxite_furnace", "minecraft:bauxite_furnace");
        map.put("minecraft:lit_bauxite_furnace", "minecraft:bauxite_furnace");
        map.put("minecraft:onyx_furnace", "minecraft:onyx_furnace");
        map.put("minecraft:lit_onyx_furnace", "minecraft:onyx_furnace");
        map.put("minecraft:frazion_furnace", "minecraft:frazion_furnace");
        map.put("minecraft:lit_frazion_furnace", "minecraft:frazion_furnace");
        map.put("frazionz:ameliorator", "frazionz:ameliorator");
        map.put("minecraft:trophy_forge", "minecraft:trophy_forge");
        map.put("minecraft:dirt_chest", "minecraft:dirt_chest");
        map.put("minecraft:yellite_chest", "minecraft:yellite_chest");
        map.put("minecraft:bauxite_chest", "minecraft:bauxite_chest");
        map.put("minecraft:frazion_chest", "minecraft:frazion_chest");
        map.put("minecraft:hdv_chest", "minecraft:hdv_chest");
        map.put("minecraft:spawner_inventory", "minecraft:spawner_inventory");
        map.put("minecraft:onyx_chest", "minecraft:onyx_chest");
        map.put("minecraft:z_hopper", "minecraft:z_hopper");
        map.put("minecraft:yellite_brewing_stand", "minecraft:yellite_brewing_stand");
        map.put("minecraft:bauxite_brewing_stand", "minecraft:bauxite_brewing_stand");
        map.put("minecraft:onyx_brewing_stand", "minecraft:onyx_brewing_stand");
        map.put("minecraft:frazion_brewing_stand", "minecraft:frazion_brewing_stand");
        map.put("minecraft:grimoire_pedestal", "minecraft:grimoire_pedestal");
    
    }
}
