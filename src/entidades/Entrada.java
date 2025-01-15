package entidades;

import java.time.LocalDate;

public class Entrada extends Entity {
    private int ID;
    private int fkUF;
    private int fkED;
    private int valor;
    private LocalDate fecha;

    public Entrada(int id, int fkUF,int fkED, int valor, LocalDate fecha) {
        super(id);
        this.fkUF = fkUF;
        this.fkED = fkED;
        this.valor = valor;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getValor() {
        return valor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFkED() {
        return fkED;
    }


    public int getFkUF() {
        return fkUF;
    }


    public void setFkUF(int fkUF) {
        this.fkUF = fkUF;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Entradas{Id=" + id + ", ID Edif=" + fkED +
                ", Departamento=" + fkUF +
                ", valor=" + valor +
                ", fecha='" + fecha + "}" + '\n';
    }


}
