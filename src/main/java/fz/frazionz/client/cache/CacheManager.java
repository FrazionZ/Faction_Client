package fz.frazionz.client.cache;

import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;
import org.lwjgl.Sys;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CacheManager {

    private static File cacheDir = new File( Minecraft.getMinecraft().fileAssets + File.separator + "frazionz" + File.separator + "cache");
    private List<AbstractCache> caches = new ArrayList<>();

    public CacheManager() {
        initCache();
    }

    private void initCache() {
        if(!cacheDir.exists()) {
            cacheDir.mkdirs();
        }

        caches.add(new ShopCache());
        //caches.add(new SkinCache());
        caches.add(new CapeCache());

        for(AbstractCache cache : caches) {
            cache.load();
            cache.download();
        }
    }

    public File getDir() {
        return cacheDir;
    }

    public boolean isFileInCache(String dir, String name) {
        return getCacheFile(dir, name).exists();
    }

    public File getCacheFile(String dir, String name) {
        return new File(cacheDir + File.separator + dir, name);
    }

    public BufferedImage getImage(String dir, String name) {
        try {
            return ImageIO.read(getCacheFile(dir, name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveFile(String dir, String name, String content) {
        File dirFile = new File(cacheDir, dir);
        if(!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = getCacheFile(dir, name);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            FileUtils.writeStringToFile(file, content, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveImage(BufferedImage img, String format, String dir, String name) {
        File dirFile = new File(cacheDir, dir);
        if(!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = getCacheFile(dir, name);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ImageIO.write(img, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getCacheDir() {
        return cacheDir;
    }
}
