package gui;

import entidades.Edificio;
import entidades.Entrada;
import entidades.Gastos;
import entidades.UnidadFuncional;
import service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FormularioBuscarEdificio extends JPanel {
    JPanel formularioBuscarEdificio;
    JPanel botones;
    JPanel areaTexto;

    JButton jButtonBuscar;
    JButton jButtonListarUF;
    JButton jButtonAgregarUF;
    JButton jButtonAgregarGasto;
    JButton jButtonAgregarEntrada;
    JButton jButtonListarExpensas;
    JButton jButtonConsolidacion;
    JComboBox jComboBoxEdificios;
    JTextArea jTextArea;

    public FormularioBuscarEdificio() {
        armarFormulario();
    }

    public void armarFormulario() {
        formularioBuscarEdificio = new JPanel();
        formularioBuscarEdificio.setLayout(new GridLayout(2, 5));

        jButtonBuscar = new JButton("Buscar");
        jButtonListarUF = new JButton("Listar UF");
        jButtonAgregarUF = new JButton("Agregar UF");
        jButtonAgregarEntrada = new JButton("Entrada");
        jButtonAgregarGasto = new JButton("Gasto");
        jButtonListarExpensas = new JButton("Listar Expensas");
        jButtonConsolidacion = new JButton("Consolidacion");
        jTextArea = new JTextArea();

        ArrayList<Edificio> listaEdificios;
        ServiceEdificio serviceEdificio = new ServiceEdificio();
        try {
            listaEdificios = serviceEdificio.todosLosEdificios();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        jComboBoxEdificios = new JComboBox();
        for (int i = 0; i < listaEdificios.size(); i++) {
            jComboBoxEdificios.addItem(listaEdificios.get(i));
        }
        formularioBuscarEdificio.add(jComboBoxEdificios);
        formularioBuscarEdificio.add(jTextArea);
        setLayout(new BorderLayout());
        add(formularioBuscarEdificio, BorderLayout.NORTH);

        areaTexto = new JPanel();
        areaTexto.setLayout(new BorderLayout());
        jTextArea.setRows(10);
        areaTexto.add(jTextArea);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);


        add(areaTexto, BorderLayout.CENTER);


        botones = new JPanel();
        botones.add(jButtonBuscar);
        botones.add(jButtonListarUF);
        botones.add(jButtonAgregarUF);
        botones.add(jButtonAgregarEntrada);
        botones.add(jButtonAgregarGasto);
        botones.add(jButtonListarExpensas);
        botones.add(jButtonConsolidacion);
        add(botones, BorderLayout.SOUTH);

        jButtonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
                jTextArea.setText(((Edificio) jComboBoxEdificios.getSelectedItem()).tostring());
            }
        });

        jButtonAgregarUF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEdificio = ((Edificio) jComboBoxEdificios.getSelectedItem()).getId();
                PanelManager panelManager = new PanelManager();
                panelManager.mostrar(new FormularioUF(idEdificio));
            }
        });

        jButtonListarUF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List lista;
                int idEdificio = ((Edificio) jComboBoxEdificios.getSelectedItem()).getId();
                jTextArea.setText("");
                try {
                    lista = new ServiceUnidadFuncional().buscarTodasLasUFDeUnEdificio(idEdificio);
                    if (lista.size() > 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            jTextArea.append(((UnidadFuncional) lista.get(i)).toString());
                        }
                    } else {
                        jTextArea.setText("El edificio no tiene Unidades Funcionales cargadas");
                    }
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jButtonAgregarGasto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEdificio = ((Edificio) jComboBoxEdificios.getSelectedItem()).getId();
                PanelManager panelManager = new PanelManager();
                panelManager.mostrar(new FormularioGastos(idEdificio, jTextArea));
            }
        });

        jButtonAgregarEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEdificio = ((Edificio) jComboBoxEdificios.getSelectedItem()).getId();
                PanelManager panelManager = new PanelManager();
                panelManager.mostrar(new FormularioIngreso(idEdificio, jTextArea));
            }
        });

        jButtonListarExpensas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Gastos> listaGastos = new ArrayList<>();
                ArrayList<UnidadFuncional> listaUnidadesFuncionales = new ArrayList<>();
                try {
                    listaGastos = new ServiceGastos().todoslosGastos();
                    listaUnidadesFuncionales = new ServiceUnidadFuncional().todosLasUF();
                    Edificio ed = (Edificio) jComboBoxEdificios.getSelectedItem();
                    Edificio edificio = new Edificio(ed.getId(), ed.getNombre(), ed.getUbicacion(), ed.getPisos(), new ArrayList<Gastos>(), new ArrayList<Entrada>(), new ArrayList<UnidadFuncional>());
                    edificio.asignarGastos(listaGastos);
                    edificio.agregarUF(listaUnidadesFuncionales);
                    String mes = (JOptionPane.showInputDialog("Ingrese el mes a liquidar Ej: 01 al 12"));
                    int gastoTotalMensual = edificio.calcularGastoMes(mes);
                    int gastoProporcionalMensual = gastoTotalMensual / edificio.listaUnidadFuncionales().size();

                    jTextArea.setText("");

                    for (int i = 0; i < edificio.listaUnidadFuncionales().size(); i++) {
                        edificio.getUnidadFuncional(i).setValorExpensas(gastoProporcionalMensual + edificio.getUnidadFuncional(i).getValorExpensas());
                        jTextArea.append(edificio.getUnidadFuncional(i).toString());
                    }

                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jButtonConsolidacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.setText("");
                ArrayList<Entrada> listaEntradas = new ArrayList<>();
                ArrayList<Gastos> listaGastos = new ArrayList<>();
                try {
                    listaEntradas = new ServiceIngreso().todoslosingresos();
                    listaGastos = new ServiceGastos().todoslosGastos();
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                Edificio ed = (Edificio) jComboBoxEdificios.getSelectedItem();
                Edificio edificio = new Edificio(ed.getId(), ed.getNombre(), ed.getUbicacion(), ed.getPisos(), new ArrayList<Gastos>(), new ArrayList<Entrada>(), new ArrayList<UnidadFuncional>());
                edificio.asignarEntradas(listaEntradas);
                edificio.asignarGastos(listaGastos);
                LocalDate fechaDesde = (LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha desde")));
                LocalDate fechaHasta = (LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha hasta")));

                jTextArea.append("Total de Ingreso: " + edificio.totalEntradas(fechaDesde, fechaHasta));
                jTextArea.append("\nTotal de Gastos: " + edificio.totalSalidas(fechaDesde, fechaHasta));
            }
        });

    }
}

