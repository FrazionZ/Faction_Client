package fz.frazionz.mods.blockrenderer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class WebExporter {

	public static void saveBlockRenderer() throws IOException {

		File inputFolder = new File("D:\\FRAZIONZ SERVER\\CODE MCP\\MCP_1.12.2_Zulu\\jars\\renders_2\\");
		File outputFolder = new File("D:\\FRAZIONZ SERVER\\CODE MCP\\MCP_1.12.2_Zulu\\jars\\dev-export\\");
		
		File exportImage = new File(outputFolder.getAbsolutePath(), "item-spritesheet.png");
		File spritesheetCutout = new File(outputFolder.getAbsolutePath(), "item-spritesheet-cutout.png");
		File exportCss = new File(outputFolder.getAbsolutePath(), "items.css");
		if(!outputFolder.exists())
			outputFolder.mkdirs();
		int fileNumber = inputFolder.list().length;
		int x = 0;
		while(x*x < fileNumber) {
			x++;
		}
		
		int size = x*128;
		List<String> cssClass = new ArrayList<>();
		
		String enchantBefore = "::before {\n content: \"\"; height: 128px;\n width: 128px;\n left: 0;\n position: absolute;\n background-image: url('/assets/themes/frazionz/img/glint.webp');\n background-size: cover;\n mix-blend-mode: color-dodge; \n}";
		String enchantAfter = "::after {\n content: \"\"; height: 128px;\n width: 128px;\n left: 0;\n position: absolute;\n background-image: url('/assets/themes/frazionz/img/item-spritesheet-cutout.webp');\n background-position: inherit; \n}";
		
		cssClass.add(".mcsprite {\n background-repeat: no-repeat;\n display: block;\n background-image: url('/assets/themes/frazionz/img/item-spritesheet.webp');\n height: 128px;\n width: 128px;\n position: absolute;\n}");
		cssClass.add(".enchanted" + enchantBefore);
		cssClass.add(".enchanted" + enchantAfter);
		
		BufferedImage spritesheet = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = spritesheet.createGraphics();
		
		File[] files = inputFolder.listFiles();
		
		Arrays.sort(files, (f1, f2) -> {
			return Integer.parseInt(f1.getName().split("-")[0]) < Integer.parseInt(f2.getName().split("-")[0]) ? -1 : 1;
		});
		
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				if(i*x+j < files.length) {
					File file = files[i*x+j];
					BufferedImage item = ImageIO.read(file); 
					int a = j*128;
					int b = i*128;
					g2d.drawImage(item, a, b, null);
					
					String[] fileInfo = file.getName().replaceAll(".png", "").split("_");
					String[] fileName = fileInfo[0].split("-");
					
					String className = ".mcpsrite-" + fileName[0] + "-" + fileName[1];
					String spriteClass = className + " {\n";
					spriteClass += " background-position: -" + a + "px -" + b + "px;\n";
					spriteClass += "}\n";
					if(fileInfo.length > 1 && fileInfo[1].contains("enchanted")) {
						spriteClass += className + enchantBefore;
						spriteClass += className + enchantAfter;
					}
					
					cssClass.add(spriteClass);
				}
			}
		}
		g2d.dispose();
		
		saveImage(spritesheet, "png", exportImage);
		System.out.println("Image Spritesheet has been exported.");
		
		saveImage(spritesheet, "png", spritesheetCutout);
		System.out.println("Item Spritesheet Cutout has been exported.");
		
		writeFile(exportCss, cssClass);
		System.out.println("Css File has been written.");
	}
	
	public static void saveImage(RenderedImage image, String format, File output) {
		try {
			ImageIO.write(image, format, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFile(File exportFile, List<String> lines) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(exportFile);
			for(String line : lines)
				fileWriter.write(line + "\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
