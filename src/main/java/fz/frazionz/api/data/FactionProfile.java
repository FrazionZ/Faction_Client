package fz.frazionz.api.data;

import java.util.UUID;

public class FactionProfile {

    private UUID uuid;
    private String money;
    private String result;

    public FactionProfile() {
    }

    public FactionProfile(UUID uuid, String money, String result) {
        this.uuid = uuid;
        this.money = money;
        this.result = result;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
