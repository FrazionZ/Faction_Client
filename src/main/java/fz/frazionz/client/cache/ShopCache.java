package fz.frazionz.client.cache;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import fz.frazionz.api.HTTPReply;
import fz.frazionz.api.HTTPUtils;

public class ShopCache extends AbstractCache {

    public ShopCache() {
        super("shop");
    }

    @Override
    public void download() {
        ShopImage[] images = getImages();
        if(images != null) {
            for(ShopImage image : images) {
                HTTPUtils.downloadFile(image.getUrl(), getDirectoryFile());
            }
        }
    }

    public static ShopImage[] getImages()
    {
        HTTPReply reply = HTTPUtils.sendGet("https://api.frazionz.net/faction/shop/thumbnail");

        if(reply.getStatusCode() == 200 || reply.getStatusCode() == 304)
        {
            JsonArray array = new JsonParser().parse(reply.getBody()).getAsJsonArray();
            ShopImage[] images = new Gson().fromJson(array, ShopImage[].class);
            if(images != null)
            {
                return images;
            }
        }

        return null;
    }

    private class ShopImage {
        private String file;
        private String sha1;

        public ShopImage(String file, String sha1) {
            this.file = file;
            this.sha1 = sha1;
        }

        public String getUrl() {
            return file;
        }

        public String getSha1() {
            return sha1;
        }
    }
}
