package com.company.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IIFrame extends JFrame {
    public static void main(String[] args) {
        new IIFrame();
    }
    public static BufferedImage image = null;
    static Graphics g = GraphicsCreator.create();

    static {
        try {
            image = ImageIO.read(new File("E:/образование и самообразование Вадим/II/src/com/company/train.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IIFrame() throws HeadlessException {
        setTitle("II");
        setSize(400,200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        DrawPanelII panel = new DrawPanelII();
        add(panel);
        validate();
    }

}
