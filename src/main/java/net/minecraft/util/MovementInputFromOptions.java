package net.minecraft.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class MovementInputFromOptions extends MovementInput
{
    private final GameSettings gameSettings;
    private boolean toggleSprint;
    
    private Minecraft mc = Minecraft.getMinecraft();

    public MovementInputFromOptions(GameSettings gameSettingsIn)
    {
        this.gameSettings = gameSettingsIn;
    }

    public void updatePlayerMoveState()
    {
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;

        if (this.gameSettings.keyBindForward.isKeyDown())
        {
            ++this.moveForward;
            this.forwardKeyDown = true;
        }
        else
        {
            this.forwardKeyDown = false;
        }

        if (this.gameSettings.keyBindBack.isKeyDown())
        {
            --this.moveForward;
            this.backKeyDown = true;
        }
        else
        {
            this.backKeyDown = false;
        }

        if (this.gameSettings.keyBindLeft.isKeyDown())
        {
            ++this.moveStrafe;
            this.leftKeyDown = true;
        }
        else
        {
            this.leftKeyDown = false;
        }

        if (this.gameSettings.keyBindRight.isKeyDown())
        {
            --this.moveStrafe;
            this.rightKeyDown = true;
        }
        else
        {
            this.rightKeyDown = false;
        }

        if(this.gameSettings.keyBindSprint.isKeyDown())
        {
        	this.toggleSprint = !this.toggleSprint;       	
        }
         
        
        this.jump = this.gameSettings.keyBindJump.isKeyDown();
        this.sneak = this.gameSettings.keyBindSneak.isKeyDown();
        
        if(this.mc.gameSettings.togglesprintMod == true) {
            this.mc.player.setSprinting(this.mc.gameSettings.togglesprintMod);
        }
        
        if (this.sneak)
        {
            this.moveStrafe = (float)((double)this.moveStrafe * 0.3D);
            this.moveForward = (float)((double)this.moveForward * 0.3D);
        }
    }
}
