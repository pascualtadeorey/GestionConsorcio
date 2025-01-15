package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioHome extends JPanel {

    JPanel formularioHome;
    JPanel botones;

    JButton jButtonAgregarEdificio;
    JButton jButtonBuscarEdificio;


    public FormularioHome() {
        armarFormulario();
    }

    public void armarFormulario() {
        formularioHome = new JPanel();
        formularioHome.setLayout(new GridLayout(2, 1));


        jButtonAgregarEdificio = new JButton("Agregar Edificio");
        jButtonBuscarEdificio = new JButton("Buscar Edificio");


        botones = new JPanel();
        botones.add(jButtonAgregarEdificio);
        botones.add(jButtonBuscarEdificio);
        add(botones, BorderLayout.SOUTH);

        jButtonAgregarEdificio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager panelManager = new PanelManager();
                panelManager.mostrar(new FormularioAgregarEdificio());
            }
        });

        jButtonBuscarEdificio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager panelManager = new PanelManager();
                panelManager.mostrar(new FormularioBuscarEdificio());
            }
        });

    }
}
