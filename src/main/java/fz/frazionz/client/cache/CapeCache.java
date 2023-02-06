package fz.frazionz.client.cache;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fz.frazionz.api.HTTPEndpoints;
import fz.frazionz.api.HTTPReply;
import fz.frazionz.api.HTTPUtils;
import fz.frazionz.utils.StringUtils;

public class CapeCache extends AbstractCache {

    public CapeCache() {
        super("capes");
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
        HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.API_CAPES_LIBRARY);

        if(reply.getStatusCode() == 200 || reply.getStatusCode() == 304)
        {
            JsonArray array = new JsonParser().parse(reply.getBody()).getAsJsonArray();
            CacheImage[] images = new CacheImage[array.size()];
            for(int i = 0; i < array.size(); i++)
            {
                JsonObject cape = array.get(i).getAsJsonObject();
                String name = cape.get("id").getAsString();
                String url = HTTPEndpoints.API_CAPES_DISPLAY_BRUT_ID.replace("{ID}", cape.get("id").getAsString());
                String sha1 = cape.get("sha1").getAsString();
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
