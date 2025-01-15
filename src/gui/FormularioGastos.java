package gui;

import entidades.Gastos;
import service.ServiceEdificio;
import service.ServiceException;
import service.ServiceGastos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class FormularioGastos extends JPanel {
    ServiceGastos serviceGastos;
    JPanel formularioGastos;
    JPanel botones;

    JLabel jlabelConcepto;
    JLabel jLabelValor;
    JLabel jLabelFecha;

    JTextField jTextFieldConcepto;
    JTextField jTextFieldValor;
    JTextField jTextFieldFecha;

    JButton jButtonGuardar;
    JButton jButtonNuevo;
    JButton jButtonListar;
    JButton jButtonEliminar;

    PanelManager panel;

    public FormularioGastos(int fk_edif, JTextArea jTextArea){
        this.serviceGastos = new ServiceGastos();
        armarFormulario(fk_edif,jTextArea) ;
    }

    public void armarFormulario(int fk_edif,JTextArea jTextArea)
    {

        formularioGastos = new JPanel();
        formularioGastos.setLayout(new GridLayout(3,4));


        jlabelConcepto = new JLabel("Concepto");
        jLabelValor = new JLabel("Valor");
        jLabelFecha = new JLabel("Fecha");
        jTextFieldConcepto = new JTextField(10);
        jTextFieldValor = new JTextField(10);
        jTextFieldFecha = new JTextField(10);
        jTextFieldFecha.setText("YYYY-MM-DD");


        formularioGastos.add(jlabelConcepto);
        formularioGastos.add(jTextFieldConcepto);
        formularioGastos.add(jLabelValor);
        formularioGastos.add(jTextFieldValor);
        formularioGastos.add(jLabelFecha);
        formularioGastos.add(jTextFieldFecha);

        setLayout(new BorderLayout());
        add(formularioGastos, BorderLayout.CENTER);

        botones = new JPanel();
        jButtonGuardar = new JButton("Guardar");
        jButtonNuevo = new JButton("Nuevo");
        jButtonListar = new JButton("Listar");
        jButtonEliminar = new JButton("Eliminar");

        botones.add(jButtonGuardar);
        botones.add(jButtonNuevo);
        botones.add(jButtonListar);
        botones.add(jButtonEliminar);

        add(botones, BorderLayout.SOUTH);


        jButtonGuardar.addActionListener(new ActionListener() {
            int ultimoID;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ultimoID = new ServiceGastos().ultimoID();
                Gastos gastos = new Gastos(ultimoID + 1,fk_edif,jTextFieldConcepto.getText(),Integer.parseInt(jTextFieldValor.getText()),LocalDate.parse(jTextFieldFecha.getText()));
                try {
                    serviceGastos.guardarGasto(gastos);
                    JOptionPane.showMessageDialog(null,"Se ingreso correctamente el gasto");
                } catch (ServiceException e) {
                    JOptionPane.showMessageDialog(null, "Error al guardar el gasto", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vaciarComponentes();
            }
        });

        jButtonListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    ArrayList<Gastos> gastos = serviceGastos.todoslosGastos();
                    for (int i = 0; i < gastos.size(); i++) {
                        if (gastos.get(i).getId_edif() == fk_edif)
                            jTextArea.append(gastos.get(i).toString());
                    }
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ID del gasto"));
                try {
                    serviceGastos.borrarGasto(id);
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                }
            }
        });
    }



    public void vaciarComponentes()
    {
        jTextFieldConcepto.setText("");
        jTextFieldValor.setText("");
        jTextFieldFecha.setText("");
    }
}

