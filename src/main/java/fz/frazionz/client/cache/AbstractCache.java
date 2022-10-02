package fz.frazionz.client.cache;

import java.io.File;

public class AbstractCache {

    private String directory;

    public AbstractCache(String directory) {
        this.directory = directory;
    }

    public void download() {}
    public void load() {
        File dir = getDirectoryFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }
    }
    public void save() {}

    public String getDirectory() {
        return directory;
    }

    public File getDirectoryFile() {
        return new File(CacheManager.getCacheDir() + File.separator + directory);
    }
}
