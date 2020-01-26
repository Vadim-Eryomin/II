package com.company;

import org.json.JSONArray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("!");
        Scanner scan = new Scanner(new FileReader("0.json"));
        System.out.println("!");
        int[] weights = getWeights(scan);
        System.out.println("!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("!");
        int num = scanner.nextInt();
        System.out.println("!");
        int[] p = getBuffImageAsPArray("D:/Programms/программирование/neuro/src/com/company/train.png");
        System.out.println("!");
        System.out.println(getWeightByAnotherFile(weights, p));
        System.out.println("!");

    }

    public static int getWeightFrom(int index, int[] weights) {
        return weights.length >= index ? weights[index] : 0;
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

    public static int[] getBuffImageAsPArray(String path) throws IOException {
        int[] p = ImageIO.read(new File("D:/Programms/программирование/neuro/src/com/company/train2.png")).getRGB(0,0,10,20,null,0,10);
        for (int i = 0; i < p.length; i++) {
            p[i] = -p[i]>>16;
        }
        return p;
    }

    public static int getWeightByAnotherFile(int[] weights, int[] p) {
        int count = 0;
        System.out.println(Arrays.toString(weights));
        System.out.println(Arrays.toString(p));
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]*weights[i]);

            count += (p[i] * weights[i] > 16384 ? 1 : 0);
        }
        return count;
    }
}