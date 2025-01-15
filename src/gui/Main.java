package gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        PanelManager panelManager = new PanelManager();
        panelManager.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelManager.mostrar(new FormularioHome());

    }
}