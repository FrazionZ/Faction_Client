package fz.frazionz.client.cache;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import fz.frazionz.api.HTTPReply;
import fz.frazionz.api.HTTPUtils;

public class SkinCache extends AbstractCache {

    public SkinCache() {
        super("skins");
    }

    @Override
    public void download() {
        CacheImage[] images = getImages();
        if(images != null) {
            for(CacheImage image : images) {
                HTTPUtils.downloadImage(image.getUrl(), getDirectoryFile(), image.getName(), image.getSha1());
            }
        }
    }

    public CacheImage[] getImages()
    {
        HTTPReply reply = HTTPUtils.sendGet("https://api.frazionz.net/faction/shop/thumbnail");

        if(reply.getStatusCode() == 200 || reply.getStatusCode() == 304)
        {
            JsonArray array = new JsonParser().parse(reply.getBody()).getAsJsonArray();
            CacheImage[] images = new CacheImage[array.size()];
            for(int i = 0; i < array.size(); i++)
            {
                String name = array.get(i).getAsJsonObject().get("itemData").getAsJsonObject().get("id").getAsString() + ".png";
                String url = array.get(i).getAsJsonObject().get("file").getAsString();
                String sha1 = array.get(i).getAsJsonObject().get("sha1").getAsString();
                images[i] = new CacheImage(url, name, sha1);
            }
            if(images != null)
            {
                return images;
            }
        }

        return null;
    }
}
