package gui;

import entidades.UnidadFuncional;
import service.ServiceException;
import service.ServiceUnidadFuncional;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioUF extends JPanel {
        ServiceUnidadFuncional serviceUnidadFuncional;
        JPanel formularioUF;
        JPanel botones;

        JLabel jLabelID;
        JLabel jlabelNombreOcupante;
        JLabel jLabelMetrosCuadrados;
        JLabel jLabelValorExpensas;
        JLabel jLabelPiso;

        JTextField jTextFieldID;
        JTextField jTextFieldNombreOcupante;
        JTextField jTextMetrosCuadrados;
        JTextField jTextFieldValorExpensas;
        JTextField jTextFieldPiso;

        JButton jButtonGuardar;
        JButton jButtonNuevo;
        JButton jButtonModificar;
        JButton jButtonEliminar;

        PanelManager panel;

        public FormularioUF(int fkEdif){

            armarFormulario(fkEdif);
        }

        public void armarFormulario(int fkEdif)
        {

            formularioUF = new JPanel();
            formularioUF.setLayout(new GridLayout(5,1));

            jLabelID = new JLabel("Id");
            jlabelNombreOcupante = new JLabel("Nombre Ocupante");
            jLabelMetrosCuadrados = new JLabel("Metros Cuadrados");
            jLabelValorExpensas = new JLabel("Valor Expensas");
            jLabelPiso = new JLabel("Piso");
            jTextFieldID = new JTextField(10);
            jTextFieldNombreOcupante = new JTextField(10);
            jTextMetrosCuadrados = new JTextField(10);
            jTextFieldValorExpensas = new JTextField(10);
            jTextFieldPiso = new JTextField(10);

            formularioUF.add(jLabelID);
            formularioUF.add(jTextFieldID);
            formularioUF.add(jlabelNombreOcupante);
            formularioUF.add(jTextFieldNombreOcupante);
            formularioUF.add(jLabelMetrosCuadrados);
            formularioUF.add(jTextMetrosCuadrados);
            formularioUF.add(jLabelValorExpensas);
            formularioUF.add(jTextFieldValorExpensas);
            formularioUF.add(jLabelPiso);
            formularioUF.add(jTextFieldPiso);

            setLayout(new BorderLayout());
            add(formularioUF, BorderLayout.CENTER);

            botones = new JPanel();
            jButtonGuardar = new JButton("Guardar");
            jButtonNuevo = new JButton("Nuevo");
            jButtonModificar = new JButton("Modificar");
            jButtonEliminar = new JButton("Eliminar");

            botones.add(jButtonGuardar);
            botones.add(jButtonNuevo);
            botones.add(jButtonModificar);
            botones.add(jButtonEliminar);

            add(botones, BorderLayout.SOUTH);
            jButtonGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    UnidadFuncional unidadFuncional =new UnidadFuncional (Integer.parseInt(jTextFieldID.getText()),
                            jTextFieldNombreOcupante.getText(), Integer.parseInt(jTextMetrosCuadrados.getText()),
                            Integer.parseInt(jTextFieldPiso.getText()),Double.parseDouble(jTextFieldValorExpensas.getText())
                            ,fkEdif);
                    try {
                       String opcion = (JOptionPane.showInputDialog("Ingrese 1 si es registro nuevo / 2 si es modificado"));
                       if (opcion.equals("1"))
                           new ServiceUnidadFuncional().guardarUnidadFuncional(unidadFuncional);
                       else if (opcion.equals("2"))
                            new ServiceUnidadFuncional().modificarUnidadFuncional(unidadFuncional);
                    }
                    catch (ServiceException e)
                    {
                        JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            jButtonNuevo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    vaciarComponentes();

                }
            });

            jButtonModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ID de la UF"));
                    try {
                        UnidadFuncional uf = new ServiceUnidadFuncional().buscarUnidadFuncional(id);
                        jTextFieldNombreOcupante.setText(uf.getNombreOcupante());
                        jTextMetrosCuadrados.setText(Integer.toString(uf.getMetrosCuadrados()));
                        jTextFieldPiso.setText(Integer.toString(uf.getPisos()));
                        jTextFieldID.setText(Integer.toString(uf.getId()));
                        jTextFieldValorExpensas.setText(Double.toString(uf.getValorExpensas()));
                    } catch (ServiceException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            jButtonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ID de la UF"));
                    try {
                        new ServiceUnidadFuncional().borrarUnidadFuncional(id);
                    } catch (ServiceException ex) {
                        throw new RuntimeException(ex.getMessage());
                    }
                }
            });

        }

        public void vaciarComponentes()
        {
            jTextFieldID.setText("");
            jTextFieldNombreOcupante.setText("");
            jTextMetrosCuadrados.setText("");
            jTextFieldValorExpensas.setText("");
            jTextFieldPiso.setText("");
        }
    }


