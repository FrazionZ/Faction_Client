package fz.frazionz.api.data;

public class FZProfile {

    private boolean result;
    private int fzid;
    private String name;
    private String id;

    public FZProfile(boolean result, int fzid, String name, String id) {
        this.result = result;
        this.fzid = fzid;
        this.name = name;
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public int getFzid() {
        return fzid;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
