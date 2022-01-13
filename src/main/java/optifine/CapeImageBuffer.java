package optifine;

import java.awt.image.BufferedImage;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.util.ResourceLocation;

public class CapeImageBuffer extends ImageBufferDownload
{
    private AbstractClientPlayer player;
    private ResourceLocation resourceLocation;

    public CapeImageBuffer(AbstractClientPlayer player, ResourceLocation ressourceLocation)
    {
        this.player = player;
        this.resourceLocation = ressourceLocation;
    }

    public BufferedImage parseUserSkin(BufferedImage image)
    {
        return CapeUtils.parseCape(image);
    }

    public void skinAvailable()
    {
        if (this.player != null)
        {
            this.player.setLocationOfCape(this.resourceLocation);
        }
    }

    public void cleanup()
    {
        this.player = null;
    }
}
