package fz.frazionz.api.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.gsonObj.BoutiqueItem;
import fz.frazionz.api.gsonObj.BoutiqueType;
import fz.frazionz.api.gsonObj.ShopItem;
import fz.frazionz.api.gsonObj.ShopType;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.resources.ResourceLocation;

public class ShopAPIDataStocker {

	public static List<ShopItem> shopItems;
	public static List<ShopType> shopTypes;
	public static List<BoutiqueItem> boutiqueItems;
	public static List<BoutiqueType> boutiqueTypes;
	
	public static List<ShopItem> getShopItemsFromType(ShopType type)
	{
		List<ShopItem> list = new ArrayList<ShopItem>();
		for(ShopItem item : shopItems)
			if(item.getShopType() == type.getId())
				list.add(item);
		return list;
	}
	
	public static List<BoutiqueItem> getBoutiqueItemsFromType(BoutiqueType type)
	{
		List<BoutiqueItem> list = new ArrayList<BoutiqueItem>();
		for(BoutiqueItem item : boutiqueItems)
			if(item.getBoutiqueType() == type.getId())
				list.add(item);
		return list;
	}
	
	public static int getShopTypeIdByName(String name) {
		int id = -1;
		for(ShopType type : shopTypes) {
			if(StringUtils.capitalize(type.getTypeName()) == StringUtils.capitalize(name))
				return type.getId();
		}
		return id;
	}
	
    /**
     * Gets the Item specified by the given text string.  First checks the item registry, then tries by parsing the
     * string as an integer ID (deprecated).  Warns the sender if we matched by parsing the ID.  Throws if the item
     * wasn't found.  Returns the item if it was found.
     */
    public static Item getItemByText(String id) throws NumberInvalidException
    {
        ResourceLocation resourcelocation = new ResourceLocation(id);
        Item item = Item.REGISTRY.getObject(resourcelocation);

        if (item == null)
        {
            throw new NumberInvalidException("commands.give.item.notFound", new Object[] {resourcelocation});
        }
        else
        {
            return item;
        }
    }
    
    public static void loadAPIData() {
		boutiqueTypes = HTTPFunctions.getAllBoutiqueTypes();
		boutiqueItems = HTTPFunctions.getAllBoutiqueItems();
		
		shopTypes = HTTPFunctions.getAllShopTypes();
		shopItems = HTTPFunctions.getAllShopItems();
		
		for(ShopType type : shopTypes) {
			type.setItems(ShopAPIDataStocker.getShopItemsFromType(type));
		}
		for(BoutiqueType type : boutiqueTypes) {
			type.setItems(ShopAPIDataStocker.getBoutiqueItemsFromType(type));
		}
    }
}
