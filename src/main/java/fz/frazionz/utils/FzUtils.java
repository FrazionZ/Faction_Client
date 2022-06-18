package fz.frazionz.utils;

import fz.frazionz.Client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class FzUtils {

    public static File getLauncherDir()
    {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            return new File(System.getProperty("user.home") + "\\AppData\\Roaming\\.FrazionzLauncher\\Launcher");
        else if (os.contains("mac"))
            return new File(System.getProperty("user.home") + "/Library/Application Support/FrazionzLauncher/Launcher");
        else
            return new File(System.getProperty("user.home") + "/.FrazionzLauncher/Launcher");
    }

    public static BufferedImage getDynamicTextureFromUrl(String url) throws IOException {
        InputStream in = new URL(url).openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] byteArray = out.toByteArray();
        ByteArrayInputStream inByte = new ByteArrayInputStream(byteArray);
        BufferedImage read = ImageIO.read(inByte);
        return read;
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    public static String convertMoney(double money){
    	return String.format("%,.2f", money);
    }

}
