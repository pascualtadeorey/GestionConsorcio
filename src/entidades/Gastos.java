package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Gastos extends Entity {
    private int id_edif; //fk
    private String concepto;
    private int valor;
    private LocalDate fecha;


    public Gastos(int id,int id_edif,String concepto, int valor, LocalDate fecha) {
        super(id);
        this.id_edif = id_edif;
        this.concepto = concepto;
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        String formattedString = fecha.format(formatter);
        return formattedString;
    }

    public int getId_edif() {
        return id_edif;
    }

    public void setId_edif(int id_edif) {
        this.id_edif = id_edif;
    }



    @Override
    public String toString() {
        return id +
                ", concepto='" + concepto + '\'' +
                ", valor=" + valor + '\'' +
                ", fecha=" + fecha + '\n';
    }
}
