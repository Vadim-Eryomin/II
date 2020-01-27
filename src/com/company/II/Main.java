package com.company.II;

import com.company.Frame.IIFrame;
import org.json.JSONArray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int[][] weights = new int[10][200];
    public static int[] koefs = new int[10];
    public static IIFrame frame;

    public static void main(String[] args) throws IOException {
        frame = new IIFrame();
    }

    public static void check(int[][] weights, int whichNumber) throws IOException {
        for (int i = 0; i < 10; i++) {
            Scanner scan = new Scanner(new FileReader(i+".json"));
            weights[i] = getWeights(scan);
        }
        int[] p = getBuffImageAsPArray();
        for (int i = 0; i < 10; i++) {
            int koef = getWeightByAnotherFile(weights[i], p);
            System.out.println(koef);
            koefs[i] = koef;
        }
        int maxIndex = 0;
        for (int i = 0; i < 10; i++) {
            if (koefs[maxIndex] < koefs[i]){
                    maxIndex = i;
            }
        }
        if (whichNumber != maxIndex){
            setWeights(weights[whichNumber], whichNumber);
        }
        IIFrame.panel.who.setText("я думаю, что это " + maxIndex);
        frame.validate();

    }

    public static void setWeights(int weights[], int whichNumber) throws IOException {
        BufferedImage image = IIFrame.image;
        int[] p = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        for (int i = 0; i < p.length; i++) {
            p[i] = -p[i] >> 16;
        }
        for (int i = 0; i < p.length; i++) {
            weights[i] = (2 * weights[i] + p[i]) / 3;
        }
        JSONArray array = new JSONArray(p);
        FileWriter fw = new FileWriter(whichNumber+".json");
        System.out.println(whichNumber+".json");
        fw.write(array.toString());
        fw.flush();
    }

    public static int[] getWeights(Scanner scanner) {
        return toArray(new JSONArray(scanner.nextLine()).toList());
    }

    public static int[] toArray(List list) {
        int a[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = Integer.parseInt(list.get(i) + "");
        }
        return a;
    }

    public static int[] getBuffImageAsPArray() throws IOException {
        int[] p = IIFrame.image.getRGB(0, 0, 10, 20, null, 0, 10);
        for (int i = 0; i < p.length; i++) {
            p[i] = -p[i] >> 16;
        }
        return p;
    }

    public static int getWeightByAnotherFile(int[] weights, int[] p) {
        int count = 0;
        for (int i = 0; i < p.length; i++) {
            count += (p[i] * weights[i] > 8000 ? 1 : 0);
        }
        return count;
    }
}