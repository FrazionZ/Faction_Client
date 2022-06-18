package fz.frazionz.api.data;

import java.util.UUID;

public class FactionProfile {

    private UUID uuid;
    private double money;
    private int kill;
    private int death;
    private int achievementPoints;
    private int totalPoints;

    public FactionProfile() {
    }

    public FactionProfile(UUID uuid, double money, int kill, int death, int achievementPoints, int totalPoints) {
        this.uuid = uuid;
        this.money = money;
        this.kill = kill;
        this.death = death;
        this.achievementPoints = achievementPoints;
        this.totalPoints = totalPoints;
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(Object money) {
        this.money = (double) money;
    }
    
    public int getAchievementPoints() {
		return achievementPoints;
	}
    
    public void setAchievementPoints(Object achievementPoints) {
		this.achievementPoints = (int) achievementPoints;
	}
    
    public int getDeath() {
		return death;
	}
    
    public void setDeath(Object death) {
		this.death = (int) death;
	}
    
    public int getKill() {
		return kill;
	}
    
    public void setKill(Object kill) {
		this.kill = (int) kill;
	}
    
    public int getTotalPoints() {
		return totalPoints;
	}
    
    public void setTotalPoints(Object totalPoints) {
		this.totalPoints = (int) totalPoints;
	}
}
