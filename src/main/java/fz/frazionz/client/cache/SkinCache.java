package fz.frazionz.client.cache;

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
                HTTPUtils.downloadImage(image.getUrl(), getDirectoryFile(), image.getName(), image.getSha1());
            }
        }
    }

    public ShopImage[] getImages()
    {
        HTTPReply reply = HTTPUtils.sendGet("https://api.frazionz.net/faction/shop/thumbnail");

        if(reply.getStatusCode() == 200 || reply.getStatusCode() == 304)
        {
            JsonArray array = new JsonParser().parse(reply.getBody()).getAsJsonArray();
            ShopImage[] images = new ShopImage[array.size()];
            for(int i = 0; i < array.size(); i++)
            {
                String name = array.get(i).getAsJsonObject().get("itemData").getAsJsonObject().get("id").getAsString() + ".png";
                String url = array.get(i).getAsJsonObject().get("file").getAsString();
                String sha1 = array.get(i).getAsJsonObject().get("sha1").getAsString();
                images[i] = new ShopImage(url, name, sha1);
            }
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
        private String name;

        public ShopImage(String file, String name, String sha1) {
            this.file = file;
            this.name = name;
            this.sha1 = sha1;
        }

        public String getUrl() {
            return file;
        }

        public String getSha1() {
            return sha1;
        }

        public String getName() {
            return name;
        }
    }
}
