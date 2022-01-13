package fz.frazionz.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;

public class EffectItem {

	private Potion effect;
	private int duration;
	private int level;
	
	public EffectItem(Potion effect, int duration, int level) {
		this.effect = effect;
		this.duration = duration;
		this.level = level;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public Potion getEffect() {
		return effect;
	}
	
	public int getLevel() {
		return level;
	}
	
}
