package gui;

import entidades.Edificio;
import entidades.Entrada;
import entidades.Gastos;
import entidades.UnidadFuncional;
import service.ServiceEdificio;
import service.ServiceException;
import service.ServiceGastos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormularioAgregarEdificio extends JPanel {
    ServiceEdificio serviceEdificio;
    JPanel formularioEdificio;
    JPanel botones;

    JLabel jLabelID;
    JLabel jlabelNombre;
    JLabel jLabelUbicacion;
    JLabel jLabelPisos;

    JTextField jTextFieldID;
    JTextField jTextFieldNombre;
    JTextField jTextFieldUbicacion;
    JTextField jTextFieldPisos;

    JButton jButtonGuardar;
    JButton jButtonNuevo;
    JButton jButtonEliminar;
    JButton jButtonModificar;

    PanelManager panel;

    public FormularioAgregarEdificio() {
        this.serviceEdificio = new ServiceEdificio();
        armarFormulario();
    }

    public void armarFormulario() {

        formularioEdificio = new JPanel();
        formularioEdificio.setLayout(new GridLayout(2, 4));

        jLabelID = new JLabel("Id");
        jlabelNombre = new JLabel("Nombre");
        jLabelUbicacion = new JLabel("Ubicacion");
        jLabelPisos = new JLabel("Cantidad de Pisos");
        jTextFieldID = new JTextField(10);
        jTextFieldNombre = new JTextField(10);
        jTextFieldUbicacion = new JTextField(10);
        jTextFieldPisos = new JTextField(10);


        formularioEdificio.add(jLabelID);
        formularioEdificio.add(jTextFieldID);
        formularioEdificio.add(jlabelNombre);
        formularioEdificio.add(jTextFieldNombre);
        formularioEdificio.add(jLabelUbicacion);
        formularioEdificio.add(jTextFieldUbicacion);
        formularioEdificio.add(jLabelPisos);
        formularioEdificio.add(jTextFieldPisos);

        setLayout(new BorderLayout());
        add(formularioEdificio, BorderLayout.CENTER);

        botones = new JPanel();
        jButtonGuardar = new JButton("Guardar");
        jButtonNuevo = new JButton("Nuevo");
        jButtonEliminar = new JButton("Eliminar");
        jButtonModificar = new JButton("Modificar");

        botones.add(jButtonGuardar);
        botones.add(jButtonNuevo);
        botones.add(jButtonEliminar);
        botones.add(jButtonModificar);


        add(botones, BorderLayout.SOUTH);
        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Edificio edificio = new Edificio(
                        Integer.parseInt(jTextFieldID.getText()),
                        jTextFieldNombre.getText(),
                        jTextFieldUbicacion.getText(),
                        Integer.parseInt(jTextFieldPisos.getText()),
                        new ArrayList<Gastos>(), new ArrayList<Entrada>(), new ArrayList<UnidadFuncional>()

                );
                try {
                    serviceEdificio.guardarEdificio(edificio);
                } catch (ServiceException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vaciarComponentes();

            }
        });

        jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serviceEdificio.borrarEdificio(Integer.parseInt(jTextFieldID.getText()));
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edificio edificio = new Edificio(Integer.parseInt(jTextFieldID.getText()),
                        jTextFieldNombre.getText(),
                        jTextFieldUbicacion.getText(),
                        Integer.parseInt(jTextFieldPisos.getText()),
                        new ArrayList<Gastos>(), new ArrayList<Entrada>(), new ArrayList<UnidadFuncional>());
                try {
                    new ServiceEdificio().modificarEdificio(edificio);
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


        public void vaciarComponentes ()
        {
            jTextFieldID.setText("");
            jTextFieldNombre.setText("");
            jTextFieldUbicacion.setText("");
            jTextFieldPisos.setText("");
        }
}
