package com.company.II;

import org.json.JSONArray;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Reader {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("0.json");
        BufferedImage image = ImageIO.read(new File("E:/образование и самообразование Вадим/II/src/com/company/train.png"));
        int[] p = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        for (int i = 0; i < p.length; i++) {
            p[i] = -p[i]>>16;
        }
        JSONArray array = new JSONArray(p);
        fw.write(array.toString());
        fw.flush();
    }

}
