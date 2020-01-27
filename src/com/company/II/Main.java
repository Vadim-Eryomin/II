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
    public static int[] weights;

    public static void main(String[] args) throws IOException {
        IIFrame frame = new IIFrame();
        Scanner scan = new Scanner(new FileReader("1.json"));
        weights = getWeights(scan);
    }

    public static void check(int[] weights, int whichNumber) throws IOException {
        int[] p = getBuffImageAsPArray();
        int koef = getWeightByAnotherFile(weights, p);
        System.out.println(koef);
        if (koef <= 20 && whichNumber == 1) {
            setWeights(weights);
        }
    }

    public static void setWeights(int weights[]) throws IOException {
        BufferedImage image = IIFrame.image;
        int[] p = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        for (int i = 0; i < p.length; i++) {
            p[i] = -p[i] >> 16;
        }
        for (int i = 0; i < p.length; i++) {
            weights[i] = p[i] > weights[i] ? p[i] / 10 + weights[i] : weights[i] - p[i] / 10;
        }
        JSONArray array = new JSONArray(p);
        FileWriter fw = new FileWriter("1.json");
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
        System.out.println(Arrays.toString(weights));
        System.out.println(Arrays.toString(p));
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i] * weights[i]);

            count += (p[i] * weights[i] > 8000 ? 1 : 0);
        }
        return count;
    }
}