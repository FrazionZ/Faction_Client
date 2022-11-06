package fz.frazionz.client.recipes;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import com.google.gson.*;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrophyForgeRecipes
{
    private final static Map<ItemStack[], ItemStack> recipes = Maps.newHashMap();
    private final static Logger LOGGER = LogManager.getLogger();
    
    static
    {
        parseJsonRecipes();
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
    public static ItemStack getResult(ItemStack[] ingredients)
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

    private static boolean parseJsonRecipes() {
        FileSystem filesystem = null;
        Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
        boolean flag;

        try {
            URL url = ItemCrusherRecipes.class.getResource("/assets/.mcassetsroot");

            if (url != null) {
                URI uri = url.toURI();
                Path path;

                if ("file".equals(uri.getScheme())) {
                    path = Paths.get(ItemCrusherRecipes.class.getResource("/assets/frazionz/custom_recipes/trophy_forge").toURI());
                } else {
                    if (!"jar".equals(uri.getScheme())) {
                        LOGGER.error("Unsupported scheme " + uri + " trying to list all recipes");
                        return false;
                    }

                    filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                    path = filesystem.getPath("/assets/frazionz/custom_recipes/trophy_forge");
                }

                Iterator<Path> iterator = Files.walk(path).iterator();

                while (iterator.hasNext()) {
                    Path path1 = iterator.next();

                    if ("json".equals(FilenameUtils.getExtension(path1.toString()))) {
                        Path path2 = path.relativize(path1);
                        String s = FilenameUtils.removeExtension(path2.toString()).replaceAll("\\\\", "/");
                        ResourceLocation resourcelocation = new ResourceLocation(s);
                        BufferedReader bufferedreader = null;

                        try {
                            try {
                                bufferedreader = Files.newBufferedReader(path1);
                                parseRecipeJson(JsonUtils.fromJson(gson, bufferedreader, JsonObject.class));
                            } catch (JsonParseException jsonparseexception) {
                                LOGGER.error("Parsing error loading recipe " + resourcelocation, (Throwable) jsonparseexception);
                                return false;
                            } catch (IOException ioexception) {
                                LOGGER.error("Couldn't read recipe " + resourcelocation + " from " + path1, (Throwable) ioexception);
                                return false;
                            }
                        } finally {
                            IOUtils.closeQuietly((Reader) bufferedreader);
                        }
                    }
                }

                return true;
            }

            LOGGER.error("Couldn't find .mcassetsroot");
            flag = false;
        } catch (IOException | URISyntaxException urisyntaxexception) {
            LOGGER.error("Couldn't get a list of all recipe files", (Throwable) urisyntaxexception);
            return false;
        } finally {
            IOUtils.closeQuietly(filesystem);
        }

        return flag;
    }

    private static void parseRecipeJson(JsonObject json) {
        if(!json.has("pattern") && !json.has("key") && !json.has("result"))
            return;

        Map<Character, ItemStack> ingredientMap = new HashMap<>();
        ingredientMap.put(' ', ItemStack.EMPTY);
        ItemStack[] ingredients = new ItemStack[9];

        // Keys
        JsonObject keys = json.getAsJsonObject("key");

        for(Map.Entry<String, JsonElement> entry : keys.entrySet()) {
            if(entry.getKey().length() != 1)
                continue;

            if(!entry.getValue().isJsonObject())
                continue;

            JsonObject ingredient = entry.getValue().getAsJsonObject();
            if(!ingredient.has("item")) {
                LOGGER.error("Missing item, expected to find a string");
                return;
            }

            Item item = Item.getByNameOrId(ingredient.get("item").getAsString());
            if(item == null) {
                LOGGER.warn("Item: " + ingredient.get("item").getAsString() + " doesn't exist.");
                return;
            }
            ItemStack stack = new ItemStack(item, 1);

            if(ingredient.has("data"))
                stack.setItemDamage(ingredient.get("data").getAsInt());

            if(ingredientMap.containsKey(entry.getKey().charAt(0))) {
                LOGGER.error("Duplicate key '" + entry.getKey() + "' in recipe");
                return;
            }
            ingredientMap.put(entry.getKey().toCharArray()[0], stack);
        }

        // Pattern
        JsonArray pattern = json.getAsJsonArray("pattern");
        if(pattern.size() != 3)
            return;
        for(int i = 0; i < pattern.size(); i++) {
            String s = pattern.get(i).getAsString();
            if (s.length() != 3) {
                LOGGER.error("Invalid pattern: malformed pattern '" + s + "', expected size of 3");
                return;
            }
            for(int j = 0; j < 3; j++) {
                char c = s.charAt(j);
                if(!keys.has(String.valueOf(c))) {
                    LOGGER.error("Invalid pattern: unknown key '" + c + "'");
                    return;
                }

                ingredients[i * 3 + j] = ingredientMap.get(c);
            }
        }

        if(!json.has("result")) {
            LOGGER.error("Missing result, expected to find a string");
            return;
        }

        // Result
        JsonObject result = json.getAsJsonObject("result");
        if(!result.has("item")) {
            LOGGER.error("Missing item, expected to find a string");
            return;
        }
        Item resultItem = Item.getByNameOrId(result.get("item").getAsString());
        if(resultItem == null) {
            LOGGER.warn("Item: " + result.get("item").getAsString() + " doesn't exist.");
            return;
        }
        ItemStack resultStack = new ItemStack(resultItem, 1);
        if(result.has("data"))
            resultStack.setItemDamage(result.get("data").getAsInt());

        // Add recipe
        addTrophyForgeRecipes(ingredients[0], ingredients[1], ingredients[2], ingredients[3], ingredients[4], ingredients[5], ingredients[6], ingredients[7], ingredients[8], resultStack);
    }

    public Map<ItemStack[], ItemStack> getRecipes()
    {
        return recipes;
    }
}
