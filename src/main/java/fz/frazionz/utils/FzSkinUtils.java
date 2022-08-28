package fz.frazionz.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.src.Config;

public class FzSkinUtils
{
	// Player Skin
    public static BufferedImage parseSkin(BufferedImage p_parseSkin_0_)
    {
        int i = 64;
        int j = 64;
        int k = p_parseSkin_0_.getWidth();

        for (int l = p_parseSkin_0_.getHeight(); i < k || j < l; j *= 2)
        {
            i *= 2;
        }

        BufferedImage bufferedimage = new BufferedImage(i, j, 2);
        Graphics graphics = bufferedimage.getGraphics();
        graphics.drawImage(p_parseSkin_0_, 0, 0, (ImageObserver)null);
        graphics.dispose();
        return bufferedimage;
    }
    
    public static ResourceLocation loadSkin(GameProfile profile, TextureType imgType) {
    	
    	String username = StringUtils.lowerCase(profile.getName());
        String downloadURL = "https://api.frazionz.net/" + imgType.getFolder() + "/display?username=" + username;
        
    	ResourceLocation resourcelocation = new ResourceLocation("frazionz/" + imgType.getFolder() + "/" + String.valueOf(profile.getId().toString()));
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject itextureobject = texturemanager.getTexture(resourcelocation);

        if (itextureobject != null && itextureobject instanceof ThreadDownloadImageData)
        {
            ThreadDownloadImageData threaddownloadimagedata = (ThreadDownloadImageData)itextureobject;

            if (threaddownloadimagedata.imageFound != null){
                if (threaddownloadimagedata.imageFound.booleanValue()){
                    return resourcelocation;
                }else {
                	return resourcelocation;
                }
            }else {
            	return resourcelocation;
            }
        }
        else
        {
            File cacheFile = getProfileCacheFile(profile, imgType);
        	ThreadDownloadImageData threaddownloadimagedata1 = new ThreadDownloadImageData(cacheFile, downloadURL, (ResourceLocation)null, null);
            threaddownloadimagedata1.pipeline = true;
            texturemanager.loadTexture(resourcelocation, threaddownloadimagedata1);
    		return resourcelocation;
        }
    }
    
    public static File getProfileCacheFile(GameProfile profile, TextureType imgType) {
    	return  new File(Minecraft.getMinecraft().fileAssets + File.separator + "frazionz" + File.separator + imgType.getFolder(), String.valueOf(profile.getId().toString()));
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
