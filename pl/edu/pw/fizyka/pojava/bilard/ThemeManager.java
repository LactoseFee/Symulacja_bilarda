package pl.edu.pw.fizyka.pojava.bilard;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    Color poolPanelBackgroundColor;
    Color poolColorGreen;
    Color cornerColor;
    Color sidePoolColor;


    public void setTheme(String theme){
        try{
            switch (theme) {
                case "default" -> {
                    UIManager.setLookAndFeel(new FlatGrayIJTheme());
                    poolPanelBackgroundColor=new Color(192,192,192);
                    poolColorGreen=new Color(80,130,60);
                    sidePoolColor=new Color(45,85,28);
                    cornerColor=new Color(54,50,25);
                }
                case "dark" -> {
                    UIManager.setLookAndFeel(new FlatDarkFlatIJTheme());
                    poolPanelBackgroundColor=new Color(38,35,35);
                    poolColorGreen=new Color(105,160,120);
                    sidePoolColor=new Color(62,90,80);
                    cornerColor=new Color(70,83,88);

                }
                case "miami" -> {
                    poolPanelBackgroundColor = new Color(152,45,124);
                    sidePoolColor = new Color(250,153,100);
                    poolColorGreen = new Color(100,160,0);
                    cornerColor = new Color(0,153,154);
                    UIManager.setLookAndFeel(new FlatGradiantoDarkFuchsiaIJTheme());
                }
            }
        } catch (Exception e){
            //e.printStackTrace();
            System.err.println("Error setting theme " + theme);
        }
    }
}
