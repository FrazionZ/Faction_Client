package net.minecraft.client.entity;

import java.io.File;
import java.util.concurrent.Executors;

import javax.annotation.Nullable;

import com.mojang.authlib.GameProfile;

import fz.frazionz.api.HTTPEndpoints;
import fz.frazionz.api.HTTPFunctions;
import fz.frazionz.api.gsonObj.UserSkinsInfo;
import fz.frazionz.utils.FzSkinUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityShoulderRiding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.src.Config;
import net.minecraft.util.StringUtils;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.optifine.player.PlayerConfigurations;
import net.optifine.reflect.Reflector;
import org.lwjgl.Sys;

public abstract class AbstractClientPlayer extends EntityPlayer
{
    private NetworkPlayerInfo playerInfo;
    public float rotateElytraX;
    public float rotateElytraY;
    public float rotateElytraZ;
    private ResourceLocation capeLocation = null;
    private ResourceLocation skinLocation = null;
    private UserSkinsInfo playerSkinsInfo = null;
    private long reloadCapeTimeMs = 0L;
    private boolean elytraOfCape = false;
    private String nameClear = null;
    public EntityShoulderRiding entityShoulderLeft;
    public EntityShoulderRiding entityShoulderRight;
    private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");

    public AbstractClientPlayer(World worldIn, GameProfile playerProfile)
    {
        super(worldIn, playerProfile);
        this.nameClear = playerProfile.getName();

        if (this.nameClear != null && !this.nameClear.isEmpty())
        {
            this.nameClear = StringUtils.stripControlCodes(this.nameClear);
        }

        this.updateSkins();
        PlayerConfigurations.getPlayerConfiguration(this);
    }
    
    public UserSkinsInfo getPlayerSkinsInfo() {
    	return this.playerSkinsInfo;
    }

    /**
     * Returns true if the player is in spectator mode.
     */
    public boolean isSpectator()
    {
        NetworkPlayerInfo networkplayerinfo = Minecraft.getMinecraft().getConnection().getPlayerInfo(this.getGameProfile().getId());
        return networkplayerinfo != null && networkplayerinfo.getGameType() == GameType.SPECTATOR;
    }

    public boolean isCreative()
    {
        NetworkPlayerInfo networkplayerinfo = Minecraft.getMinecraft().getConnection().getPlayerInfo(this.getGameProfile().getId());
        return networkplayerinfo != null && networkplayerinfo.getGameType() == GameType.CREATIVE;
    }

    /**
     * Checks if this instance of AbstractClientPlayer has any associated player data.
     */
    public boolean hasPlayerInfo()
    {
        return this.getPlayerInfo() != null;
    }

    @Nullable
    protected NetworkPlayerInfo getPlayerInfo()
    {
        if (this.playerInfo == null)
        {
            this.playerInfo = Minecraft.getMinecraft().getConnection().getPlayerInfo(this.getUniqueID());
        }

        return this.playerInfo;
    }

    /**
     * Returns true if the player has an associated skin.
     */
    public boolean hasSkin()
    {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo != null && networkplayerinfo.hasLocationSkin();
    }

    /**
     * Returns the ResourceLocation associated with the player's skin
     */
    public ResourceLocation getSkinLocation()
    {
        if (this.skinLocation != null)
        {
            return this.skinLocation;
        }
        else {
	        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
	        return networkplayerinfo == null ? DefaultPlayerSkin.getDefaultSkin(this.getUniqueID()) : networkplayerinfo.getLocationSkin();
        }
    }

    @Nullable
    public ResourceLocation getCapeLocation()
    {
        if (!Config.isShowCapes())
        {
            return null;
        }
        else
        {
            /*if (this.reloadCapeTimeMs != 0L && System.currentTimeMillis() > this.reloadCapeTimeMs)
            {
                CapeUtils.reloadCape(this);
                this.reloadCapeTimeMs = 0L;
            }*/

            if (this.capeLocation != null)
            {
                return this.capeLocation;
            }
            else
            {
                NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
                return networkplayerinfo == null ? null : networkplayerinfo.getLocationCape();
            }
        }
    }

    public boolean isPlayerInfoSet()
    {
        return this.getPlayerInfo() != null;
    }

    @Nullable

    /**
     * Gets the special Elytra texture for the player.
     */
    public ResourceLocation getLocationElytra()
    {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo == null ? null : networkplayerinfo.getLocationElytra();
    }

    public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation resourceLocationIn, String username)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject itextureobject = texturemanager.getTexture(resourceLocationIn);

        if (itextureobject == null)
        {
            itextureobject = new ThreadDownloadImageData(
                    (File)null,
                    String.format(HTTPEndpoints.API_USER_UUID_SKIN_DISPLAY.replace("{UUID}", StringUtils.stripControlCodes(username))),
                    DefaultPlayerSkin.getDefaultSkin(getOfflineUUID(username)),
                    new ImageBufferDownload()
            );
            texturemanager.loadTexture(resourceLocationIn, itextureobject);
        }

        return (ThreadDownloadImageData)itextureobject;
    }

    /**
     * Returns true if the username has an associated skin.
     */
    public static ResourceLocation getSkinLocation(String username)
    {
        return new ResourceLocation("frazionz", "cache/skins/" + StringUtils.stripControlCodes(username));
    }

    public String getSkinType()
    {
    	if(this.playerSkinsInfo != null && this.playerSkinsInfo.isSkinExist())
    		return this.playerSkinsInfo.getSkinType().getTps();
    	else
    		return UserSkinsInfo.SkinType.CLASSIC.getTps();
    }

    public float getFovModifier()
    {
        float f = 1.0F;

        if (this.capabilities.isFlying)
        {
            f *= 1.1F;
        }

        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        f = (float)((double)f * (((iattributeinstance.getAttributeValue()) / (double)this.capabilities.getWalkSpeed() + 1.0D) / 2.0D));

        if (this.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f))
        {
            f = 1.0F;
        }

        if (this.isHandActive() && this.getActiveItemStack().getItem() instanceof ItemBow)
        {
            int i = this.getItemInUseMaxCount();
            float f1 = (float)i / 20.0F;

            if (f1 > 1.0F)
            {
                f1 = 1.0F;
            }
            else
            {
                f1 = f1 * f1;
            }

            f *= 1.0F - f1 * 0.15F;
        }

        return Reflector.ForgeHooksClient_getOffsetFOV.exists() ? Reflector.callFloat(Reflector.ForgeHooksClient_getOffsetFOV, this, f) : f;
    }

    public String getNameClear()
    {
        return this.nameClear;
    }


    public boolean hasElytraCape()
    {
        ResourceLocation resourcelocation = this.getCapeLocation();

        if (resourcelocation == null)
        {
            return false;
        }
        else
        {
            return resourcelocation == this.capeLocation ? this.elytraOfCape : true;
        }
    }

    public void setElytraOfCape(boolean elytraOfCape)
    {
        this.elytraOfCape = elytraOfCape;
    }

    public boolean isElytraOfCape()
    {
        return this.elytraOfCape;
    }

    public long getReloadCapeTimeMs()
    {
        return this.reloadCapeTimeMs;
    }

    public void setReloadCapeTimeMs(long time)
    {
        this.reloadCapeTimeMs = time;
    }


    /**
     * Sets the location of the player's skin.
     * @param resourceLocation
     */
    public void setSkinLocation(ResourceLocation resourceLocation)
    {
        this.skinLocation = resourceLocation;
    }

    /**
     * Sets the location of the player's cape.
     * @param resourceLocation
     */
    public void setCapeLocation(ResourceLocation resourceLocation)
    {
        this.capeLocation = resourceLocation;
    }

    /**
     * Update the player's skin and cape.
     */
    private void updateSkins() {
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    playerSkinsInfo = HTTPFunctions.getPlayerSkinInfo(getUniqueID());


                    try {
                        skinLocation = FzSkinUtils.loadSkin(getGameProfile(), playerSkinsInfo);
                    } catch (Exception e) {
                        System.out.println("Error loading skin for " + getGameProfile().getName());
                        e.printStackTrace();
                    }
                    try {
                        playerSkinsInfo.setCapeId(HTTPFunctions.getPlayerCapeId(getUniqueID()));
                        capeLocation = FzSkinUtils.loadCape(getGameProfile(), playerSkinsInfo);
                    } catch (Exception e) {
                        System.out.println("Error loading cape for " + getGameProfile().getName());
                        e.printStackTrace();
                    }
                }
                catch (Exception e) {
                    System.out.println("Error loading skin info for " + getGameProfile().getName());
                    e.printStackTrace();
                }
            }
        });
    }
}
