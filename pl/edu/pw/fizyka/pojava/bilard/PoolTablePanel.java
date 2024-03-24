package pl.edu.pw.fizyka.pojava.bilard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PoolTablePanel extends JPanel {
    public static void main(String[] args) throws IOException {
        JPanel panel = new JPanel();

        BufferedImage table = ImageIO.read(new File("./bilardtable.png"));
        JLabel label = new JLabel(new ImageIcon(table));
        panel.add(label);
    }


}
