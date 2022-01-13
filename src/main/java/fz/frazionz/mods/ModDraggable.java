package fz.frazionz.mods;

import java.io.File;

import fz.frazionz.gui.hud.IRenderer;
import fz.frazionz.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer
{
    protected ScreenPosition pos;

    public ModDraggable()
    {
        //pos = loadPositionFromFile();
        pos = loadPositionFileTxt();
    }

    @Override
    public ScreenPosition load()
    {
        return pos;
    }

    @Override
    public void save(ScreenPosition pos)
    {
        this.pos = pos;
        //savePositionToFile();
        saveModOptions();
    }

    private File getFolder()
    {
        File folder = new File(FileManager.getModsDirectory(), this.getClass().getSimpleName());
        folder.mkdirs();
        return folder;
    }

    private void savePositionToFile()
    {
        FileManager.writeJsonToFile(new File(getFolder(), "pos.json"), pos);
    }
    
    private void saveModOptions()
    {
    	FileManager.saveModOptions(new File(getFolder(), "pos.txt"), pos);
        //FileManager.writeJsonToFile(new File(getFolder(), "pos.txt"), pos);
    }

    private ScreenPosition loadPositionFromFile()
    {
        ScreenPosition loaded = FileManager.readFromJson(new File(getFolder(), "pos.json"), ScreenPosition.class);
        
        if(loaded == null)
        {
            loaded = ScreenPosition.fromRelativePosition(1, 1);
            this.pos = loaded;
            savePositionToFile();
        }
        
        return loaded;
    }
    
    private ScreenPosition loadPositionFileTxt()
    {
        ScreenPosition loaded = FileManager.loadModOptions(new File(getFolder(), "pos.txt"));
        
        if(loaded == null)
        {
            loaded = ScreenPosition.fromRelativePosition(1, 1);
            this.pos = loaded;
            savePositionToFile();
        }
        
        return loaded;
    }

    public final int getLineOffset(ScreenPosition pos, int lineNum)
    {
        return pos.getAbsoluteX() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum)
    {
        return (font.FONT_HEIGHT + 3) * lineNum;
    }
    
}
