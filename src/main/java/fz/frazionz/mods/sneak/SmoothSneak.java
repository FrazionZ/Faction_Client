package fz.frazionz.mods.sneak;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class SmoothSneak {

	private SmoothSneakRunnable runnable;
	private Minecraft mc;
	private float eyeHeightAnimation;
	private Entity entity;
	
	public SmoothSneak(Minecraft mc, Entity entity) {
		
		this.mc = mc;
		this.entity = entity;
		this.eyeHeightAnimation = entity.getEyeHeight();
		
        this.runnable = (() -> {
            if(this.eyeHeightAnimation > entity.getEyeHeight()) {
            	this.eyeHeightAnimation -= 0.015F;
                if(this.eyeHeightAnimation < entity.getEyeHeight()) {
                	this.eyeHeightAnimation = entity.getEyeHeight();
                }
            }
            else if(this.eyeHeightAnimation < entity.getEyeHeight()) {
                this.eyeHeightAnimation += 0.015F;
                if(this.eyeHeightAnimation > entity.getEyeHeight()) {
                	this.eyeHeightAnimation = entity.getEyeHeight();
                }
            }
        });
        
        this.runnable.registerTick();
	}
	
	public float getEyeHeightAnimation() {
		return eyeHeightAnimation;
	}
	
	public void unregisterRunnable() {
		this.runnable.unregisterTick();
	}
	
	public void registerRunnable() {
		this.runnable.registerTick();
	}
	
	public boolean isRunnableRegister() {
		return this.runnable.isRegistered();
	}
	
	public Entity getEntity() {
		return entity;
	}
	
}
