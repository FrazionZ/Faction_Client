package fz.frazionz.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import fz.frazionz.api.HTTPEndpoints;
import fz.frazionz.api.HTTPUtils;
import fz.frazionz.api.SHA1Utils;
import fz.frazionz.api.gsonObj.UserSkinsInfo;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.optifine.texture.TextureType;
import org.lwjgl.Sys;

public class FzSkinUtils
{
	// Player Skin
    public static BufferedImage parseSkin(BufferedImage skinImage)
    {
        int i = 64;
        int j = 64;
        int k = skinImage.getWidth();

        for (int l = skinImage.getHeight(); i < k || j < l; j *= 2)
        {
            i *= 2;
        }

        BufferedImage bufferedimage = new BufferedImage(i, j, 2);
        Graphics graphics = bufferedimage.getGraphics();
        graphics.drawImage(skinImage, 0, 0, (ImageObserver)null);
        graphics.dispose();
        return bufferedimage;
    }

    public static ResourceLocation loadCape(GameProfile profile, UserSkinsInfo userSkinsInfo) {
        if(userSkinsInfo == null) {
            return null;
        }
        if(userSkinsInfo.getCapeId() == -1) {
            return null;
        }
        String id = String.valueOf(userSkinsInfo.getCapeId());
        return loadTexture(
                id,
                TextureType.CAPE,
                HTTPEndpoints.API_CAPES_DISPLAY_BRUT_ID.replace("{ID}", id),
                new ResourceLocation("frazionz", "cache/capes/" + id),
                null
        );
    }

    public static ResourceLocation loadSkin(GameProfile profile, UserSkinsInfo userSkinsInfo) {
        if(userSkinsInfo == null || !userSkinsInfo.isSkinExist() || !userSkinsInfo.hasSkin()) {
            return null;
        }
        String id = profile.getId().toString();
        return loadTexture(
                id,
                TextureType.SKIN,
                HTTPEndpoints.API_USER_UUID_SKIN_DISPLAY.replace("{UUID}", id),
                new ResourceLocation("frazionz", "cache/skins/" + id),
                userSkinsInfo.getSkinSHA1()
        );
    }
    
    public static ResourceLocation loadTexture(String fileName, TextureType textureType, String downloadURL, ResourceLocation resourceLocation, String sha1)
    {
        TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject itextureObject = textureManager.getTexture(resourceLocation);

        if(itextureObject instanceof ThreadDownloadImageData) {
            System.out.println("itextureObject is ThreadDownloadImageData");
            File cacheFile = ((ThreadDownloadImageData) itextureObject).getCacheFile();
            try {
                if (!SHA1Utils.calcSHA1(cacheFile).equals(sha1)) {
                    cacheFile.delete();
                    ThreadDownloadImageData threaddownloadimagedata1 = new ThreadDownloadImageData(cacheFile, downloadURL, null, null);
                    threaddownloadimagedata1.pipeline = true;
                    textureManager.loadTexture(resourceLocation, threaddownloadimagedata1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("itextureObject is null");
            File cacheFile = getCacheFile(fileName, textureType);
            try {
                if (!SHA1Utils.calcSHA1(cacheFile).equals(sha1)) {
                    cacheFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ThreadDownloadImageData threaddownloadimagedata1 = new ThreadDownloadImageData(cacheFile, downloadURL, null, null);
            threaddownloadimagedata1.pipeline = true;
            textureManager.loadTexture(resourceLocation, threaddownloadimagedata1);
        }
        return resourceLocation;
    }

    public static File getCacheFile(String fileName, TextureType imgType) {
    	return  new File(
                Minecraft.getMinecraft().fileAssets + File.separator + "frazionz" + File.separator + "cache" + File.separator + imgType.getFolder(), fileName);
    }
    
    public enum TextureType {
    	
    	SKIN("skins"),
    	CAPE("capes"),
    	;
    	
    	private String folder;
    	
    	TextureType(String folder) {
    		this.folder = folder;
    	}
    	
    	public String getFolder() {
			return folder;
		}
    	
    }
}
