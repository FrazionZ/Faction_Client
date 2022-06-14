package fz.frazionz.item.crafting;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AmelioratorRecipes
{
    private final static Map<ItemStack[], ItemStack> recipes = Maps.<ItemStack[], ItemStack>newHashMap();

    /**
     * Returns an instance of FurnaceRecipes.
     */

    private static Item frazion = Items.FRAZION;
    private static Item farm_nugget = Items.FARM_NUGGET;
    
    static
    {
        addRecipes(frazion, frazion, frazion, null, null, null, frazion, frazion, Items.ONYX_HELMET, new ItemStack(Items.FRAZION_HELMET));
        addRecipes(frazion, frazion, frazion, frazion, frazion, frazion, frazion, frazion, Items.ONYX_CHESTPLATE, new ItemStack(Items.FRAZION_CHESTPLATE));
        addRecipes(frazion, frazion, frazion, frazion, null, frazion, frazion, frazion, Items.ONYX_LEGGINGS, new ItemStack(Items.FRAZION_LEGGINGS));
        addRecipes(null, null, frazion, frazion, null, frazion, frazion, null, Items.ONYX_BOOTS, new ItemStack(Items.FRAZION_BOOTS));
        
        addRecipes(frazion, frazion, frazion, null, null, null, frazion, frazion, Items.FRAZION_HELMET, new ItemStack(Items.FRAZION_HELMET_70));
        addRecipes(frazion, frazion, frazion, frazion, frazion, frazion, frazion, frazion, Items.FRAZION_CHESTPLATE, new ItemStack(Items.FRAZION_CHESTPLATE_70));
        addRecipes(frazion, frazion, frazion, frazion, null, frazion, frazion, frazion, Items.FRAZION_LEGGINGS, new ItemStack(Items.FRAZION_LEGGINGS_70));
        addRecipes(null, null, frazion, frazion, null, frazion, frazion, null, Items.FRAZION_BOOTS, new ItemStack(Items.FRAZION_BOOTS_70));
        
        addRecipes(frazion, frazion, frazion, null, null, null, frazion, frazion, Items.FRAZION_HELMET_70, new ItemStack(Items.FRAZION_HELMET_100));
        addRecipes(frazion, frazion, frazion, frazion, frazion, frazion, frazion, frazion, Items.FRAZION_CHESTPLATE_70, new ItemStack(Items.FRAZION_CHESTPLATE_100));
        addRecipes(frazion, frazion, frazion, frazion, null, frazion, frazion, frazion, Items.FRAZION_LEGGINGS_70, new ItemStack(Items.FRAZION_LEGGINGS_100));
        addRecipes(null, null, frazion, frazion, null, frazion, frazion, null, Items.FRAZION_BOOTS_70, new ItemStack(Items.FRAZION_BOOTS_100));
        
        addRecipes(null, null, frazion, null, Items.ONYX_STICK, null, frazion, null, Items.ONYX_SWORD, new ItemStack(Items.FRAZION_SWORD));
        
        addRecipes(frazion, null, Items.RENFORCED_STRING, Items.RENFORCED_STRING, Items.RENFORCED_STRING, null, frazion, frazion, Items.BOW, new ItemStack(Items.ULTRA_BOW));
        
        addRecipes(frazion, null, frazion, frazion, frazion, Items.ONYX_STICK, frazion, frazion, Items.ONYX_STICK, new ItemStack(Items.FRAZION_HAMMER));
        
        addRecipes(frazion, frazion, null, null, Items.ONYX_STICK, null, null, frazion, Items.ONYX_STICK, new ItemStack(Items.FRAZION_PICKAXE));
        
        addRecipes(null, null, null, null, Items.ONYX_STICK, frazion, frazion, frazion, Items.ONYX_STICK, new ItemStack(Items.FRAZION_AXE));
        
        addRecipes(frazion, null, null, null, Items.ONYX_STICK, null, null, null, Items.ONYX_STICK, new ItemStack(Items.FRAZION_SHOVEL));
        
        addRecipes(frazion, null, null, null, Items.ONYX_STICK, null, null, frazion, Items.ONYX_STICK, new ItemStack(Items.FRAZION_HOE));
        
        addRecipes(null, frazion, null, null, null, Items.ONYX_STICK, null, null, frazion, new ItemStack(Items.FRAZION_DAGGER));
        
        addAmelioratorRecipes(new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.FRAZION_PICKAXE), new ItemStack(Items.SPAWNER_PICKAXE));
        
        addAmelioratorRecipes(new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.YELLITE_BLOCK), new ItemStack(Blocks.YELLITE_BLOCK), new ItemStack(Blocks.YELLITE_BLOCK), new ItemStack(Blocks.YELLITE_BLOCK), new ItemStack(Blocks.YELLITE_BLOCK), new ItemStack(Blocks.YELLITE_BLOCK), new ItemStack(Blocks.YELLITE_BLOCK), new ItemStack(Blocks.HOPPER), new ItemStack(Blocks.Z_HOPPER));
        
        addAmelioratorRecipes(new ItemStack(Blocks.TNT), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Blocks.TNT), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Blocks.TNT), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Blocks.TNT), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.Z_TNT));
        
        addRecipes(farm_nugget,	farm_nugget, farm_nugget, null, null, null, farm_nugget, farm_nugget, Items.ONYX_HELMET, new ItemStack(Items.TRAVELERS_HELMET));
        addRecipes(farm_nugget, farm_nugget, farm_nugget, farm_nugget, farm_nugget, farm_nugget, farm_nugget, farm_nugget, Items.ONYX_CHESTPLATE, new ItemStack(Items.TRAVELERS_CHESTPLATE));
        addRecipes(farm_nugget, farm_nugget, farm_nugget, farm_nugget, null, farm_nugget, farm_nugget, farm_nugget, Items.ONYX_LEGGINGS, new ItemStack(Items.TRAVELERS_LEGGINGS));
        addRecipes(null, null, farm_nugget, farm_nugget, null, farm_nugget, farm_nugget, null, Items.ONYX_BOOTS, new ItemStack(Items.TRAVELERS_BOOTS));
        
        addAmelioratorRecipes(new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.COSMIC_POWDER), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Items.FRAZION_SWORD), new ItemStack(Items.FARM_SWORD));
        
        addRecipes(Items.COSMIC_INGOT, Items.COSMIC_INGOT, null, null, Items.FRAZION_STICK, Items.COSMIC_INGOT, Items.COSMIC_INGOT, Items.COSMIC_INGOT, Items.FRAZION_STICK, new ItemStack(Items.LEGENDARY_SCYTHE));
        addRecipes(Items.COSMIC_INGOT, Items.COSMIC_INGOT, Items.COSMIC_INGOT, null, Items.FRAZION_STICK, null, Items.COSMIC_INGOT, Items.COSMIC_INGOT, Items.FRAZION_DAGGER, new ItemStack(Items.LEGENDARY_DAGGER));
        addRecipes(Items.COSMIC_INGOT, Items.COSMIC_INGOT, Items.COSMIC_INGOT, null, Items.FRAZION_STICK, null, Items.COSMIC_INGOT, Items.COSMIC_INGOT, Items.FRAZION_SWORD, new ItemStack(Items.LEGENDARY_SWORD));
        addRecipes(Items.COSMIC_INGOT, Items.COSMIC_INGOT, Items.COSMIC_INGOT, null, Items.FRAZION_STICK, null, Items.COSMIC_INGOT, Items.COSMIC_INGOT, Items.FRAZION_AXE, new ItemStack(Items.LEGENDARY_AXE));

        addRecipes(Items.FRAZION_SHOVEL, Items.FRAZION_PICKAXE, null, null, Items.FRAZION_STICK, null, null, Items.FRAZION_AXE, Items.FRAZION_STICK, new ItemStack(Items.FRAZION_MULTITOOL));      
        addAmelioratorRecipes(new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.FRAZION), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.FRAZION), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.FRAZION), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.FRAZION), new ItemStack(Blocks.ONYX_CHEST), new ItemStack(Blocks.FRAZION_CHEST));
        addAmelioratorRecipes(new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.FRAZION), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.FRAZION), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.FRAZION), new ItemStack(Items.FRAZION_POWDER), new ItemStack(Items.FRAZION), new ItemStack(Blocks.ONYX_FURNACE), new ItemStack(Blocks.FRAZION_FURNACE));
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public static void addRecipes(Item i1, Item i2, Item i3, Item i4, Item i5, Item i6, Item i7, Item i8, Item i9, ItemStack itemStack)
    {
    	addAmelioratorRecipes(new ItemStack(i1), new ItemStack(i2), new ItemStack(i3), new ItemStack(i4), new ItemStack(i5), new ItemStack(i6), new ItemStack(i7), new ItemStack(i8), new ItemStack(i9), itemStack);
    }

    private static void addRecipes(Item i1, Item i2, Item i3, Item i4, Item i5, Item i6, ItemStack itemStack) {
    	addAmelioratorRecipes(new ItemStack(i1), new ItemStack(i2), new ItemStack(i3), new ItemStack(i4), new ItemStack(i5), new ItemStack(i6), null, null, null, itemStack);
        
	}

	/**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    
    public static void addAmelioratorRecipes(ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5, ItemStack i6, ItemStack i7, ItemStack i8, ItemStack i9, ItemStack result)
    {
        recipes.put(new ItemStack[]{i1, i2, i3, i4, i5, i6, i7, i8, i9}, result);
    }

    /**
     * Returns the smelting result of an item.
     */

    

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    
    private static boolean areKeysEqual(ItemStack[] key1, ItemStack[] key2) {
    	 
        if(key1.length != key2.length) return false;
     
     
     
        for(int i = 0; i < key1.length; i++) {
     
            ItemStack s1 = key1[i];
     
            ItemStack s2 = key2[i];
     
            if(s1.isEmpty() && !s2.isEmpty()) return false;
     
            if(!s1.isEmpty() && s2.isEmpty()) return false;
     
            if(s1.getItem() != s2.getItem()) return false;
     
            if(s1.getItemDamage() != s2.getItemDamage()) return false;
     
        }
     
        return true;
     
    } 
    
    public static ItemStack getAmelioratorResult(ItemStack[] ingredients)
    {
        Iterator<Entry<ItemStack[], ItemStack>> it = recipes.entrySet().iterator();
        
        while(it.hasNext())
        {
            Entry <ItemStack[], ItemStack>entry = it.next();
            if (areKeysEqual(entry.getKey(), ingredients)) 
            {
                return entry.getValue();
            }
        }
        return null;
    }
    
    private static boolean areSlotItemsEqual(ItemStack[] key1, ItemStack[] key2) {
   	 
        if(key1.length != key2.length) return false;
     
     
     
        for(int i = 0; i < key1.length; i++) {
     
            ItemStack s1 = key1[i];
     
            ItemStack s2 = key2[i];
     
            if(s1.isEmpty() && !s2.isEmpty()) return false;
     
            if(!s1.isEmpty() && s2.isEmpty()) return false;
     
            if(s1.getItem() != s2.getItem()) return false;
     
            if(s1.getItemDamage() != s2.getItemDamage()) return false;
     
        }
     
        return true;
     
    }

    public Map<ItemStack[], ItemStack> getAmelioratorResult()
    {
        return this.recipes;
    }
}
