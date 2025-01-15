package gui;

import entidades.Entrada;
import entidades.Gastos;
import service.ServiceException;
import service.ServiceIngreso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class FormularioIngreso extends JPanel {
    ServiceIngreso serviceIngreso;
    JPanel formularioIngreso;
    JPanel botones;

    JLabel jlabelIDUF;
    JLabel jLabelValor;
    JLabel jLabelFecha;

    JTextField jTextFieldIDUF;
    JTextField jTextFieldValor;
    JTextField jTextFieldFecha;

    JButton jButtonGuardar;
    JButton jButtonNuevo;
    JButton jButtonEliminar;
    JButton jButtonListar;

    PanelManager panel;

    public FormularioIngreso(int fkEd, JTextArea jTextArea){
        this.serviceIngreso = new ServiceIngreso();
        armarFormulario(fkEd, jTextArea);
    }

    public void armarFormulario(int fkEd, JTextArea jTextArea)
    {

        formularioIngreso = new JPanel();
        formularioIngreso.setLayout(new GridLayout(3,4));

        jlabelIDUF = new JLabel("ID UF");
        jLabelValor = new JLabel("Valor");
        jLabelFecha = new JLabel("Fecha");
        jTextFieldIDUF = new JTextField(10);
        jTextFieldValor = new JTextField(10);
        jTextFieldFecha = new JTextField(10);

        jTextFieldFecha.setText("YYYY-MM-DD");
        formularioIngreso.add(jlabelIDUF);
        formularioIngreso.add(jTextFieldIDUF);
        formularioIngreso.add(jLabelValor);
        formularioIngreso.add(jTextFieldValor);
        formularioIngreso.add(jLabelFecha);
        formularioIngreso.add(jTextFieldFecha);

        setLayout(new BorderLayout());
        add(formularioIngreso, BorderLayout.CENTER);

        botones = new JPanel();
        jButtonGuardar = new JButton("Guardar");
        jButtonNuevo = new JButton("Nuevo");
        jButtonListar = new JButton("Listar");
        jButtonEliminar = new JButton("Eliminar");

        botones.add(jButtonGuardar);
        botones.add(jButtonNuevo);
        botones.add(jButtonEliminar);
        botones.add(jButtonListar);
        add(botones, BorderLayout.SOUTH);

        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int ultimoID;
                try {
                    ultimoID = serviceIngreso.UltimoID();
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
                Entrada entrada = new Entrada(ultimoID + 1,Integer.parseInt(jTextFieldIDUF.getText()),fkEd,Integer.parseInt(jTextFieldValor.getText()),LocalDate.parse(jTextFieldFecha.getText()));
                try {
                    serviceIngreso.guardarIngreso(entrada);
                    JOptionPane.showMessageDialog(null,"Se ingreso correctamente la entrada");
                } catch (ServiceException e) {
                    JOptionPane.showMessageDialog(null,"No existe ese departamento","Error",JOptionPane.ERROR_MESSAGE);
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
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ID del ingreso"));
                try {
                    serviceIngreso.borrarIngreso(id);
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                }
            }
        });

        jButtonListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    ArrayList<Entrada> entradas = serviceIngreso.todoslosingresos();
                    for (int i = 0; i < entradas.size(); i++) {
                        if (entradas.get(i).getFkED() == fkEd)
                            jTextArea.append(entradas.get(i).toString());
                    }
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void vaciarComponentes()
    {
        jTextFieldIDUF.setText("");
        jTextFieldValor.setText("");
        jTextFieldFecha.setText("");
    }
}

