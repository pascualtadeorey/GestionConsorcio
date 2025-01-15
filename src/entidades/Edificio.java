package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Edificio extends Entity {
    private String nombre;
    private String ubicacion;
    private int pisos;
    private List departamentos;
    private List gastos;
    private List entradas;

    public Edificio(int id, String nombre, String ubicacion, int pisos, ArrayList gastos, ArrayList entradas, ArrayList departamentos) {
        super(id);
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.pisos = pisos;
        this.gastos = gastos;
        this.entradas = entradas;
        this.departamentos = departamentos;
    }


    public void agregarUF(ArrayList<UnidadFuncional> list) {
        for (int i = 0; i < list.size(); i++) {
            if (this.id == list.get(i).getFk())
                departamentos.add(list.get(i));
        }
    }

    public void asignarEntradas(ArrayList<Entrada> listaEntradas){
        for (int i = 0; i < listaEntradas.size(); i++) {
            if (this.id == listaEntradas.get(i).getFkED())
                entradas.add(listaEntradas.get(i));
        }
    }
    public void asignarGastos(ArrayList<Gastos> listaGastos){
        for (int i = 0; i < listaGastos.size(); i++) {
            if (this.id == listaGastos.get(i).getId_edif())
                this.gastos.add(listaGastos.get(i));
        }
    }

    public int calcularGastoMes(String mes){
        int totalGastos = 0;
        for (int i = 0; i < this.gastos.size(); i++) {
            if (Integer.parseInt(mes) == (((Gastos)this.gastos.get(i)).getFecha().getMonthValue()))
                totalGastos = totalGastos + ((Gastos) this.gastos.get(i)).getValor();
        }
        return totalGastos;
    }

    public int totalEntradas(LocalDate f1,LocalDate f2){
        int totalEntradas = 0;
        for (int i = 0; i < this.entradas.size(); i++) {
            if (f1.isBefore(((Entrada)this.entradas.get(i)).getFecha()))
                if(f2.isAfter(((Entrada)this.entradas.get(i)).getFecha()))
                    totalEntradas += ((Entrada) this.entradas.get(i)).getValor();
        }
        return totalEntradas;
    }

    public int totalSalidas(LocalDate f1,LocalDate f2){
        int totalSalidas = 0;
        for (int i = 0; i < this.gastos.size(); i++) {
            if (f1.isBefore(((Gastos)this.gastos.get(i)).getFecha()))
                if(f2.isAfter(((Gastos)this.gastos.get(i)).getFecha()))
                    totalSalidas += ((Gastos) this.gastos.get(i)).getValor();
        }
        return totalSalidas;
    }

    public UnidadFuncional getUnidadFuncional(int i){
        return (UnidadFuncional) departamentos.get(i);
    }

    public ArrayList<UnidadFuncional> listaUnidadFuncionales(){
        ArrayList lista = new ArrayList<UnidadFuncional>();
        for (int i = 0; i < departamentos.size(); i++) {
            lista.add(departamentos.get(i));
        }
        return lista;
    }

    public String getNombre() {
        return nombre;
    }


    public String getUbicacion() {
        return ubicacion;
    }

    public int getPisos() {
        return pisos;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String tostring() {
        return "Edificio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", pisos=" + pisos + '\'';
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setPisos(int pisos) {
        this.pisos = pisos;
    }
}
