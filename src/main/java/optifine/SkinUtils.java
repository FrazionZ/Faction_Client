package optifine;

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
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;

public class SkinUtils
{
    public static void downloadSkin(AbstractClientPlayer p_downloadSkin_0_)
    {
        String s = p_downloadSkin_0_.getNameClear();

        if (s != null && !s.isEmpty())
        {
            String s1 = "https://frazionz.net/api/skins/" + s;
            ResourceLocation resourcelocation = new ResourceLocation("frazionz/skins/" + s);
            TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
            ITextureObject itextureobject = texturemanager.getTexture(resourcelocation);

            if (itextureobject != null && itextureobject instanceof ThreadDownloadImageData)
            {
                ThreadDownloadImageData threaddownloadimagedata = (ThreadDownloadImageData)itextureobject;

                if (threaddownloadimagedata.imageFound != null)
                {
                    if (threaddownloadimagedata.imageFound.booleanValue())
                    {
                        p_downloadSkin_0_.setLocationOfSkin(resourcelocation);
                    }
                    return;
                }
            }
            
            File cacheFile = new File(Minecraft.getMinecraft().mcDataDir, "assets/frazionz/skins/" + StringUtils.lowerCase(s));
            SkinImageBuffer Skinimagebuffer = new SkinImageBuffer(p_downloadSkin_0_, resourcelocation);
            ThreadDownloadImageData threaddownloadimagedata1 = new ThreadDownloadImageData(null, s1, (ResourceLocation)null, Skinimagebuffer);
            threaddownloadimagedata1.pipeline = true;
            texturemanager.loadTexture(resourcelocation, threaddownloadimagedata1);
        }
    }

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
    
    public static ResourceLocation loadSkin(GameProfile profile) {
    	
    	String s = StringUtils.lowerCase(profile.getName());
    	ResourceLocation resourcelocation = new ResourceLocation("frazionz/skins/" + s);
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
        }else {
        	String urlDl = "https://frazionz.net/api/skins/" + s;
        	File cacheFile = new File(Minecraft.getMinecraft().mcDataDir, "assets/frazionz/skins/" + StringUtils.lowerCase(s));
        	ThreadDownloadImageData threaddownloadimagedata1 = new ThreadDownloadImageData(cacheFile, urlDl, (ResourceLocation)null, null);
            threaddownloadimagedata1.pipeline = true;
            texturemanager.loadTexture(resourcelocation, threaddownloadimagedata1);
    		return resourcelocation;
        }
    }
}
