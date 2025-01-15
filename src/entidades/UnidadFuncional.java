package entidades;

public class UnidadFuncional extends Entity {
    private int fk;
    private String nombreOcupante;
    private int metrosCuadrados;
    private int piso;
    private double valorExpensas;

    public UnidadFuncional(int id,String nombreOcupante, int metrosCuadrados, int piso, double valorExpensas,int fk) {
        super(id);
        this.nombreOcupante = nombreOcupante;
        this.metrosCuadrados = metrosCuadrados;
        this.piso = piso;
        this.valorExpensas = valorExpensas;
        this.fk = fk;
    }

    public void cambiarNombreOcupante(String nombreOcupante){
        this.nombreOcupante = nombreOcupante;
    }

    public double getValorExpensas() {
        return valorExpensas;
    }

    public String getNombreOcupante() {
        return nombreOcupante;
    }

    public int getPisos() {
        return piso;
    }

    public int getId() {
        return id;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public int getFk() {
        return fk;
    }

    @Override
    public String toString() {
        String datos = "";
        datos = "UnidadFuncional: " + id +
                " nombreOcupante: " + nombreOcupante + ", metrosCuadrados: " + metrosCuadrados
                + ", piso: " + piso + ", valorExpensas: " + valorExpensas + '\n';
        return datos;
    }

    public void setValorExpensas(double valorExpensas) {
        this.valorExpensas = valorExpensas;
    }
}

