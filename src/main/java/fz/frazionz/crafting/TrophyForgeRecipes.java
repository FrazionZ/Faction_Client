package fz.frazionz.crafting;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import fz.frazionz.item.ItemTrophy;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TrophyForgeRecipes
{
    private final static Map<ItemStack[], ItemStack> recipes = Maps.<ItemStack[], ItemStack>newHashMap();
    
    static
    {
    	addRecipes(null, Items.RUNE, null, Items.RUNE, Items.ENDER_PEARL, Items.RUNE, null, Items.RUNE, null, new ItemStack(Items.TROPHY_ENDERMAN));
    	addRecipes(Items.FARM_NUGGET, Items.BONE, Items.FARM_NUGGET, Items.BONE, Items.FARM_KEY, Items.BONE, Items.FARM_NUGGET, Items.BONE, Items.FARM_NUGGET, new ItemStack(Items.TROPHY_SKELETON));
    	addRecipes(Items.FARM_NUGGET, Items.SHULKER_SHELL, Items.FARM_NUGGET, Items.SHULKER_SHELL, Items.FARM_KEY, Items.SHULKER_SHELL, Items.FARM_NUGGET, Items.SHULKER_SHELL, Items.FARM_NUGGET, new ItemStack(Items.TROPHY_SHULKER));
    }

    /**
     * Adds a forging recipe using an Item as the input item.
     */
    public static void addRecipes(Item i1, Item i2, Item i3, Item i4, Item i5, Item i6, Item i7, Item i8, Item i9, ItemStack itemStack)
    {
    	addTrophyForgeRecipes(new ItemStack(i1), new ItemStack(i2), new ItemStack(i3), new ItemStack(i4), new ItemStack(i5), new ItemStack(i6), new ItemStack(i7), new ItemStack(i8), new ItemStack(i9), itemStack);
    }

	/**
     * Adds a forging recipe using an ItemStack as the input for the recipe.
     */
    public static void addTrophyForgeRecipes(ItemStack i1, ItemStack i2, ItemStack i3, ItemStack i4, ItemStack i5, ItemStack i6, ItemStack i7, ItemStack i8, ItemStack i9, ItemStack result)
    {
        recipes.put(new ItemStack[]{i1, i2, i3, i4, i5, i6, i7, i8, i9}, result);
    }

    /**
     * Returns the forging result of an item.
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
    
    public static ItemStack getTrophyForgeResult(ItemStack[] ingredients)
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

    public Map<ItemStack[], ItemStack> getTrophyForgeResult()
    {
        return this.recipes;
    }
}
