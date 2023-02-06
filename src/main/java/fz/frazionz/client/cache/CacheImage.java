package fz.frazionz.client.cache;

public class CacheImage {

    private String file;
    private String sha1;
    private String name;

    public CacheImage(String file, String name, String sha1) {
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
