package fz.frazionz.client.recipes;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.JsonUtils;

public class ItemCrusherRecipes {
    private final static Map<ItemStack, CrushResult> RECIPES = Maps.newHashMap();
    private final static Logger LOGGER = LogManager.getLogger();
    
    static
    {
    	parseJsonRecipes();
    }

	/**
     * Adds a forging recipe using an ItemStack as the input for the recipe.
     */
    public static void addRecipes(ItemStack item, CrushItem... items)
    {
    	RECIPES.put(item, new CrushResult(items));
    }
    
    public static void addRecipes(ItemStack item, CrushResult items)
    {
    	RECIPES.put(item, items);
    }
    
    public static ItemStack[] getResult(ItemStack item)
    {
        Iterator<Entry<ItemStack, CrushResult>> it = RECIPES.entrySet().iterator();

        ItemStack[] result = null;
        while(it.hasNext())
        {
            Entry <ItemStack, CrushResult>entry = it.next();
            if (areItemStackEqual(entry.getKey(), item))
            {
                return entry.getValue().getItems();
            }
        }
        
    	return null;
    }

    public Map<ItemStack, CrushResult> getRecipes()
    {
        return RECIPES;
    }
    
    private static boolean areItemStackEqual(ItemStack key1, ItemStack key2) {
            if(key1.isEmpty() && !key2.isEmpty()) return false;
            if(!key1.isEmpty() && key2.isEmpty()) return false;
            if(key1.getItem() != key2.getItem()) return false;
            if(key1.getItemDamage() != key2.getItemDamage()) return false;
        return true;
     
    } 
    
    private static boolean parseJsonRecipes()
    {
        FileSystem filesystem = null;
        Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
        boolean flag1;

        try
        {
            URL url = ItemCrusherRecipes.class.getResource("/assets/.mcassetsroot");

            if (url != null)
            {
                URI uri = url.toURI();
                Path path;

                if ("file".equals(uri.getScheme()))
                {
                    path = Paths.get(ItemCrusherRecipes.class.getResource("/assets/frazionz/custom_recipes/item_crusher").toURI());
                }
                else
                {
                    if (!"jar".equals(uri.getScheme()))
                    {
                        LOGGER.error("Unsupported scheme " + uri + " trying to list all recipes");
                        boolean flag2 = false;
                        return flag2;
                    }

                    filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                    path = filesystem.getPath("/assets/frazionz/custom_recipes/item_crusher");
                }

                Iterator<Path> iterator = Files.walk(path).iterator();

                while (iterator.hasNext())
                {
                    Path path1 = iterator.next();

                    if ("json".equals(FilenameUtils.getExtension(path1.toString())))
                    {
                        Path path2 = path.relativize(path1);
                        String s = FilenameUtils.removeExtension(path2.toString()).replaceAll("\\\\", "/");
                        ResourceLocation resourcelocation = new ResourceLocation(s);
                        BufferedReader bufferedreader = null;

                        try
                        {
                            boolean flag;

                            try
                            {
                                bufferedreader = Files.newBufferedReader(path1);
                                parseRecipeJson((JsonObject)JsonUtils.fromJson(gson, bufferedreader, JsonObject.class));
                            }
                            catch (JsonParseException jsonparseexception)
                            {
                                LOGGER.error("Parsing error loading recipe " + resourcelocation, (Throwable)jsonparseexception);
                                flag = false;
                                return flag;
                            }
                            catch (IOException ioexception)
                            {
                                LOGGER.error("Couldn't read recipe " + resourcelocation + " from " + path1, (Throwable)ioexception);
                                flag = false;
                                return flag;
                            }
                        }
                        finally
                        {
                            IOUtils.closeQuietly((Reader)bufferedreader);
                        }
                    }
                }

                return true;
            }

            LOGGER.error("Couldn't find .mcassetsroot");
            flag1 = false;
        }
        catch (IOException | URISyntaxException urisyntaxexception)
        {
            LOGGER.error("Couldn't get a list of all recipe files", (Throwable)urisyntaxexception);
            flag1 = false;
            return flag1;
        }
        finally
        {
            IOUtils.closeQuietly((Closeable)filesystem);
        }

        return flag1;
    }
    
    private static void parseRecipeJson(JsonObject json)
    {
    	String itemName = JsonUtils.getString(json, "item");
        Item item = Item.getByNameOrId(itemName);
        if(item == null) {
        	LOGGER.warn("Item: " + itemName + " doesn't exist.");
        	return;
        }
        CrushResult result = new CrushResult(JsonUtils.getJsonArray(json, "results"));
        addRecipes(new ItemStack(item), result);
    }
    
    static class CrushResult {
    	
    	private CrushItem[] stacks;
    	
    	public CrushResult(CrushItem[] stacks) {
    		this.stacks = stacks;
    	}
    	
    	public CrushResult(JsonArray json) {
    		List<CrushItem> items = new ArrayList<>();
    		for(int i = 0; i < json.size(); i++) {
    			CrushItem item = new CrushItem(json.get(i).getAsJsonObject());
    			if(item.isValid())
    				items.add(item);
    		}
    		this.stacks = new CrushItem[items.size()];
    		for(int i = 0; i < items.size(); i++)
    			stacks[i] = items.get(i);
    	}
    	
    	public ItemStack[] getItems() {
    		ItemStack[] stack = new ItemStack[stacks.length];
    		for(int i = 0; i < stacks.length; i++) {
                if(stacks[i].randomChance())
                    stack[i] = stacks[i].getItem();
            }
    		return stack;
    	}
    }
    
    static class CrushItem {
    	
    	private int min;
    	private int max;
    	private Item item;
    	private short itemDamage = 0;
        private float chance = 1.0f;

        CrushItem(int min, int max, Item item) {
            this.min = min;
            this.max = max;
            this.item = item;
        }

    	CrushItem(int min, int max, Item item, float chance) {
    		this.min = min;
    		this.max = max;
    		this.item = item;
            this.chance = chance;
    	}
    	
    	CrushItem(JsonObject json) {
    		String itemName = JsonUtils.getString(json, "item");
    		Item item = Item.getByNameOrId(itemName);
            if(item == null) {
            	LOGGER.error("Item Result: " + itemName + " doesn't exist.");
            }
            this.item = item;
            
    		this.min = JsonUtils.getInt(json, "min");
    		this.max = JsonUtils.getInt(json, "max");
    		if(min > max) {
    			int tmpMin = min;
    			min = max;
    			max = tmpMin;
    		}

            if(json.has("chance")) {
                this.chance = JsonUtils.getFloat(json, "chance");
                if(this.chance > 1.0f)
                    this.chance = 1.0f;
                if(this.chance < 0.0f)
                    this.chance = 0.0f;
            }

    		if(json.has("meta"))
    			itemDamage = json.get("meta").getAsShort();
    	}
    	
    	public void setItemDamage(short itemDamage) {
			this.itemDamage = itemDamage;
		}
    	
    	ItemStack getItem() {
			return new ItemStack(item, min + new Random().nextInt(max-min), itemDamage);
    	}
    	
    	boolean isValid() {
    		return item != null;
    	}

        boolean randomChance() {
            return new Random().nextFloat() <= chance;
        }
    	
    }
}
