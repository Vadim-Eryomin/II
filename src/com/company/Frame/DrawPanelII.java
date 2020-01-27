package com.company.Frame;

import com.company.II.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DrawPanelII extends JPanel {
    Image image;

    static JTextField number;
    static JButton choose;
    static JButton teach;
    static ImagePanel draw;

    public DrawPanelII() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        JLabel num = new JLabel("Введите число");
        add(num, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        number = new JTextField(20);
        add(number, c);

        c.gridx = 2;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        choose = new JButton("Выбрать файл");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите рисунок для считывания");

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File file;
                if (fileChooser.showOpenDialog(fileChooser) == fileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    try {
                        IIFrame.image = ImageIO.read(file);
                        Main.check(Main.weights, Integer.parseInt(number.getText()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        add(choose, c);

        validate();
    }

    @Override
    protected void paintComponent(Graphics g) {

    }
}
