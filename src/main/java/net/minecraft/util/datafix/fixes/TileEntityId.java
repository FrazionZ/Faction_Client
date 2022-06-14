package net.minecraft.util.datafix.fixes;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

public class TileEntityId implements IFixableData
{
    private static final Map<String, String> OLD_TO_NEW_ID_MAP = Maps.<String, String>newHashMap();

    public int getFixVersion()
    {
        return 704;
    }

    public NBTTagCompound fixTagCompound(NBTTagCompound compound)
    {
        String s = OLD_TO_NEW_ID_MAP.get(compound.getString("id"));

        if (s != null)
        {
            compound.setString("id", s);
        }

        return compound;
    }

    static
    {
        OLD_TO_NEW_ID_MAP.put("Airportal", "minecraft:end_portal");
        OLD_TO_NEW_ID_MAP.put("Banner", "minecraft:banner");
        OLD_TO_NEW_ID_MAP.put("Beacon", "minecraft:beacon");
        OLD_TO_NEW_ID_MAP.put("Cauldron", "minecraft:brewing_stand");
        OLD_TO_NEW_ID_MAP.put("Chest", "minecraft:chest");
        OLD_TO_NEW_ID_MAP.put("Comparator", "minecraft:comparator");
        OLD_TO_NEW_ID_MAP.put("Control", "minecraft:command_block");
        OLD_TO_NEW_ID_MAP.put("DLDetector", "minecraft:daylight_detector");
        OLD_TO_NEW_ID_MAP.put("Dropper", "minecraft:dropper");
        OLD_TO_NEW_ID_MAP.put("EnchantTable", "minecraft:enchanting_table");
        OLD_TO_NEW_ID_MAP.put("EndGateway", "minecraft:end_gateway");
        OLD_TO_NEW_ID_MAP.put("EnderChest", "minecraft:ender_chest");
        OLD_TO_NEW_ID_MAP.put("FlowerPot", "minecraft:flower_pot");
        OLD_TO_NEW_ID_MAP.put("Furnace", "minecraft:furnace");
        OLD_TO_NEW_ID_MAP.put("Hopper", "minecraft:hopper");
        OLD_TO_NEW_ID_MAP.put("MobSpawner", "minecraft:mob_spawner");
        OLD_TO_NEW_ID_MAP.put("Music", "minecraft:noteblock");
        OLD_TO_NEW_ID_MAP.put("Piston", "minecraft:piston");
        OLD_TO_NEW_ID_MAP.put("RecordPlayer", "minecraft:jukebox");
        OLD_TO_NEW_ID_MAP.put("Sign", "minecraft:sign");
        OLD_TO_NEW_ID_MAP.put("Skull", "minecraft:skull");
        OLD_TO_NEW_ID_MAP.put("Structure", "minecraft:structure_block");
        OLD_TO_NEW_ID_MAP.put("Trap", "minecraft:dispenser");
        
        OLD_TO_NEW_ID_MAP.put("z_hopper", "minecraft:z_hopper");
        OLD_TO_NEW_ID_MAP.put("dirt_chest", "minecraft:dirt_chest");
        OLD_TO_NEW_ID_MAP.put("hdv_chest", "minecraft:hdv_chest");
        OLD_TO_NEW_ID_MAP.put("spawner_inventory", "minecraft:spawner_inventory");
        OLD_TO_NEW_ID_MAP.put("onyx_chest", "minecraft:onyx_chest");
        OLD_TO_NEW_ID_MAP.put("yellite_furnace", "minecraft:yellite_furnace");
        OLD_TO_NEW_ID_MAP.put("bauxite_furnace", "minecraft:bauxite_furnace");
        OLD_TO_NEW_ID_MAP.put("onyx_furnace", "minecraft:onyx_furnace");
        OLD_TO_NEW_ID_MAP.put("frazion_furnace", "minecraft:frazion_furnace");
        OLD_TO_NEW_ID_MAP.put("ameliorator", "minecraft:ameliorator");
        OLD_TO_NEW_ID_MAP.put("yellite_brewing_stand", "minecraft:yellite_brewing_stand");
        OLD_TO_NEW_ID_MAP.put("bauxite_brewing_stand", "minecraft:bauxite_brewing_stand");
        OLD_TO_NEW_ID_MAP.put("onyx_brewing_stand", "minecraft:onyx_brewing_stand");
        OLD_TO_NEW_ID_MAP.put("frazion_brewing_stand", "minecraft:frazion_brewing_stand");
        OLD_TO_NEW_ID_MAP.put("trophy_forge", "minecraft:trophy_forge");
    
    }
}
