package fz.frazionz;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class CheatEngineTask extends TimerTask {


    public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 1000L, 1000L);
    }

    @Override
    public void run() {
        try {
            String line;
            Process process = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                if (line.contains("cheat") ||  line.contains("tracker") ||  line.contains("IP Tracker") || line.contains("cengine") || line.contains("auto clicker") || line.contains("autoclick") || line.contains("slave") || line.contains("cheatengine") || line.contains("Proxifier") || line.contains("injector") ||  line.contains("timeRun") || line.contains("timetravel")) {
                    System.out.println("Error : " + line);
                    System.exit(0);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}