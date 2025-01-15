package gui;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    JFrame ventana;

    public PanelManager() {
        ventana = new JFrame();
        ventana.setVisible(true);
        ventana.pack();
    }

    void mostrar(JPanel panel){
        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(BorderLayout.SOUTH,panel);
        ventana.getContentPane().validate();
        ventana.getContentPane().repaint();
        ventana.pack();
    }
}
