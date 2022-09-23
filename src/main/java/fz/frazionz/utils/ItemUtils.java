package fz.frazionz.utils;

import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.resources.ResourceLocation;

public class ItemUtils {

    public static Item getItemByText(String id) throws NumberInvalidException
    {
        ResourceLocation resourcelocation = new ResourceLocation(id);
        Item item = Item.REGISTRY.getObject(resourcelocation);
        return item;
    }

}
