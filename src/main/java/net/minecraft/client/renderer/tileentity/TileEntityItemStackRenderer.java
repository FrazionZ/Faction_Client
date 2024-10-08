package net.minecraft.client.renderer.tileentity;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.mojang.authlib.GameProfile;

import fz.frazionz.tileentity.TileEntityBauxiteChest;
import fz.frazionz.tileentity.TileEntityDirtChest;
import fz.frazionz.tileentity.TileEntityFrazionChest;
import fz.frazionz.tileentity.TileEntityHdvChest;
import fz.frazionz.tileentity.TileEntityOnyxChest;
import fz.frazionz.tileentity.TileEntityYelliteChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShield;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;

public class TileEntityItemStackRenderer
{
    private static final TileEntityShulkerBox[] SHULKER_BOXES = new TileEntityShulkerBox[16];
    public static TileEntityItemStackRenderer instance;
    private final TileEntityChest chestBasic = new TileEntityChest(BlockChest.Type.BASIC);
    private final TileEntityChest chestTrap = new TileEntityChest(BlockChest.Type.TRAP);
    private final TileEntityEnderChest enderChest = new TileEntityEnderChest();
    private final TileEntityBanner banner = new TileEntityBanner();
    private final TileEntityBed bed = new TileEntityBed();
    private final TileEntitySkull skull = new TileEntitySkull();
    private final ModelShield modelShield = new ModelShield();

    private final TileEntityDirtChest dirt_chest = new TileEntityDirtChest();
    private final TileEntityYelliteChest yellite_chest = new TileEntityYelliteChest();
    private final TileEntityBauxiteChest bauxite_chest = new TileEntityBauxiteChest();
    private final TileEntityFrazionChest frazion_chest = new TileEntityFrazionChest();
    private final TileEntityHdvChest hdv_chest = new TileEntityHdvChest();
    private final TileEntityOnyxChest onyx_chest = new TileEntityOnyxChest();

    public void renderByItem(ItemStack itemStackIn)
    {
        this.renderByItem(itemStackIn, 1.0F);
    }

    public void renderByItem(ItemStack itemStackIn, float partialTicks)
    {
        Item item = itemStackIn.getItem();

        if (item == Items.BANNER)
        {
            this.banner.setItemValues(itemStackIn, false);
            TileEntityRendererDispatcher.instance.render(this.banner, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Items.BED)
        {
            this.bed.setItemValues(itemStackIn);
            TileEntityRendererDispatcher.instance.render(this.bed, 0.0D, 0.0D, 0.0D, 0.0F);
        }
        else if (item == Items.SHIELD)
        {
            if (itemStackIn.getSubCompound("BlockEntityTag") != null)
            {
                this.banner.setItemValues(itemStackIn, true);
                Minecraft.getMinecraft().getTextureManager().bindTexture(BannerTextures.SHIELD_DESIGNS.getResourceLocation(this.banner.getPatternResourceLocation(), this.banner.getPatternList(), this.banner.getColorList()));
            }
            else
            {
                Minecraft.getMinecraft().getTextureManager().bindTexture(BannerTextures.SHIELD_BASE_TEXTURE);
            }

            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            this.modelShield.render();
            GlStateManager.popMatrix();
        }
        else if (item == Items.SKULL)
        {
            GameProfile gameprofile = null;

            if (itemStackIn.hasTagCompound())
            {
                NBTTagCompound nbttagcompound = itemStackIn.getTagCompound();

                if (nbttagcompound.hasKey("SkullOwner", 10))
                {
                    gameprofile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                }
                else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner")))
                {
                    GameProfile gameprofile1 = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
                    gameprofile = TileEntitySkull.updateGameProfile(gameprofile1);
                    nbttagcompound.removeTag("SkullOwner");
                    nbttagcompound.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), gameprofile));
                }
            }

            if (TileEntitySkullRenderer.instance != null)
            {
                GlStateManager.pushMatrix();
                GlStateManager.disableCull();
                TileEntitySkullRenderer.instance.renderSkull(0.0F, 0.0F, 0.0F, EnumFacing.UP, 180.0F, itemStackIn.getMetadata(), gameprofile, -1, 0.0F);
                GlStateManager.enableCull();
                GlStateManager.popMatrix();
            }
        }
        else if (item == Item.getItemFromBlock(Blocks.ENDER_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.enderChest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Item.getItemFromBlock(Blocks.TRAPPED_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.chestTrap, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (Block.getBlockFromItem(item) instanceof BlockShulkerBox)
        {
            TileEntityRendererDispatcher.instance.render(SHULKER_BOXES[BlockShulkerBox.getColorFromItem(item).getMetadata()], 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Item.getItemFromBlock(Blocks.DIRT_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.dirt_chest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Item.getItemFromBlock(Blocks.YELLITE_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.yellite_chest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Item.getItemFromBlock(Blocks.BAUXITE_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.bauxite_chest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Item.getItemFromBlock(Blocks.FRAZION_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.frazion_chest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Item.getItemFromBlock(Blocks.HDV_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.hdv_chest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else if (item == Item.getItemFromBlock(Blocks.ONYX_CHEST))
        {
            TileEntityRendererDispatcher.instance.render(this.onyx_chest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
        else
        {
            TileEntityRendererDispatcher.instance.render(this.chestBasic, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
        }
    }

    static
    {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values())
        {
            SHULKER_BOXES[enumdyecolor.getMetadata()] = new TileEntityShulkerBox(enumdyecolor);
        }

        instance = new TileEntityItemStackRenderer();
    }
}
