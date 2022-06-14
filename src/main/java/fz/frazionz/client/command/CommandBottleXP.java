package fz.frazionz.client.command;

import org.apache.commons.lang3.StringUtils;

import fz.frazionz.item.ItemBottleXP;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandBottleXP extends CommandBase
{
	
	Minecraft mc = Minecraft.getMinecraft();
    /**
     * Gets the name of the command
     */
    public String getName()
    {
        return "bottlexp";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    /**
     * Gets the usage string for the command.
     */
    public String getUsage(ICommandSender sender)
    {
        return "commands.bottlexp.usage";
    }

    /**
     * Callback for when the command is executed
     */
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
        if (args.length <= 0)
        {
            int level = entityplayer.getExperienceLevel();
        	ItemStack item = new ItemStack(Items.BOTTLEXP, level, true);
        	ItemBottleXP bottleXp = (ItemBottleXP) item.getItem();
        	
            if(entityplayer.inventory.getFirstEmptyStack() != -1 || entityplayer.inventory.containsAtLeast(item, 1)) {
            	
				if(level < bottleXp.getMinLevel()) {
					entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous devez avoir \u00A76" + bottleXp.getMinLevel() + " \u00A7eniveaux Minimum."));
				}
				else if(level > bottleXp.getMaxLevel() ) {
					entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eLa bottle ne peut pas avoir que \u00A76" + bottleXp.getMaxLevel() + " \u00A7eniveaux Maximum. Essayez avec \u00A76\"/bottlexp 100\"\u00A7e."));
				}
				
				else {
					entityplayer.inventory.addItemStackToInventory(item);
					entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous venez de mettre en bouteille \u00A76" + level + " \u00A7elevels."));
					entityplayer.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 0.5F, 1.0F);
					entityplayer.addExperienceLevel(-level, true);
					
					entityplayer.inventoryContainer.detectAndSendChanges();
				}
            	
            }
            else {
				entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous devez avoir une place dans votre inventaire."));
            }
        }
        else
        {
        	if(StringUtils.isNumeric(args[0])) {
            	int bottleLevel = Integer.parseInt(args[0]);
            	ItemStack item = new ItemStack(Items.BOTTLEXP, bottleLevel, true);
            	ItemBottleXP bottleXp = (ItemBottleXP) item.getItem();
            	
                if(entityplayer.inventory.getFirstEmptyStack() != -1 || entityplayer.inventory.containsAtLeast(item, 1)) {
                	
    				if(Integer.parseInt(args[0]) < bottleXp.getMinLevel()) {
    					entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eLa bottle doit être de \u00A76" + bottleXp.getMinLevel() + " \u00A7eniveaux Minimum."));
    				}
    				else if(Integer.parseInt(args[0]) > bottleXp.getMaxLevel() ) {
    					entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eLa bottle ne peut pas avoir que \u00A76" + bottleXp.getMaxLevel() + " \u00A7eniveaux Maximum. Essayez avec \u00A76\"/bottlexp 100\"\u00A7e."));
    				}
    				
    				else if(Integer.parseInt(args[0]) > entityplayer.getExperienceLevel()) {
    					entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous n'avez pas assez de niveaux."));
    				}
    				
    				else {
    					entityplayer.inventory.addItemStackToInventory(item);
    					entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous venez de mettre en bouteille \u00A76" + bottleLevel + " \u00A7elevels."));
    					entityplayer.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 0.5F, 1.0F);
    					entityplayer.addExperienceLevel(-bottleLevel, true);
    					
    					entityplayer.inventoryContainer.detectAndSendChanges();
    				}
                }
                else {
    				entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous devez avoir une place dans votre inventaire."));
                }
        	}
        	else {
        		entityplayer.sendMessage(new TextComponentTranslation("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous devez mettre un nombre précis."));
        	}
            
        }
    }
}
