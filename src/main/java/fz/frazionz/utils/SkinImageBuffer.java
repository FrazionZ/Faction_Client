package fz.frazionz.utils;

import java.awt.image.BufferedImage;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.resources.ResourceLocation;

public class SkinImageBuffer extends ImageBufferDownload
{
    private AbstractClientPlayer player;
    private ResourceLocation resourceLocation;

    public SkinImageBuffer(AbstractClientPlayer p_i21_1_, ResourceLocation p_i21_2_)
    {
        this.player = p_i21_1_;
        this.resourceLocation = p_i21_2_;
    }

    public BufferedImage parseUserSkin(BufferedImage image)
    {
        return FzSkinUtils.parseSkin(image);
    }

    public void skinAvailable()
    {
        if (this.player != null)
        {
            this.player.setLocationOfSkin(this.resourceLocation);
        }
    }

    public void cleanup()
    {
        this.player = null;
    }
}
