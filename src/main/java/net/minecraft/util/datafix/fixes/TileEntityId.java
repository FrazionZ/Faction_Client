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
        
        OLD_TO_NEW_ID_MAP.put("z_hopper", "frazionz:z_hopper");
        OLD_TO_NEW_ID_MAP.put("dirt_chest", "frazionz:dirt_chest");
        OLD_TO_NEW_ID_MAP.put("hdv_chest", "frazionz:hdv_chest");
        OLD_TO_NEW_ID_MAP.put("spawner_inventory", "frazionz:spawner_inventory");
        OLD_TO_NEW_ID_MAP.put("onyx_chest", "frazionz:onyx_chest");
        OLD_TO_NEW_ID_MAP.put("yellite_furnace", "frazionz:yellite_furnace");
        OLD_TO_NEW_ID_MAP.put("bauxite_furnace", "frazionz:bauxite_furnace");
        OLD_TO_NEW_ID_MAP.put("onyx_furnace", "frazionz:onyx_furnace");
        OLD_TO_NEW_ID_MAP.put("frazion_furnace", "frazionz:frazion_furnace");
        OLD_TO_NEW_ID_MAP.put("ameliorator", "frazionz:ameliorator");
        OLD_TO_NEW_ID_MAP.put("yellite_brewing_stand", "frazionz:yellite_brewing_stand");
        OLD_TO_NEW_ID_MAP.put("bauxite_brewing_stand", "frazionz:bauxite_brewing_stand");
        OLD_TO_NEW_ID_MAP.put("onyx_brewing_stand", "frazionz:onyx_brewing_stand");
        OLD_TO_NEW_ID_MAP.put("frazion_brewing_stand", "frazionz:frazion_brewing_stand");
        OLD_TO_NEW_ID_MAP.put("trophy_forge", "frazionz:trophy_forge");
        OLD_TO_NEW_ID_MAP.put("grimoire_pedestal", "frazionz:grimoire_pedestal");
        
    }
}
