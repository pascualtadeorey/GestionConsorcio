package entidades;

import java.util.ArrayList;
import java.util.List;

public class Administradora {
    private int id; //PK para SQL
    private String nombre;
    private List edificios = new ArrayList<Edificio>();

    public Administradora(String nombre) {
        this.nombre = nombre;
    }

    public void agregarEdificio(Edificio edificio) {
        edificios.add(edificio);
    }

    public String listarEdificios() {
        String datos = "";
        for (int i = 0; i < edificios.size(); i++) {
            datos += ((Edificio) edificios.get(i)).getNombre() + '\n';
        }
        return datos;
    }

    public Edificio busquedaEdificio(String nombreEdificio) {
        boolean encontrado = false;
        Edificio ed = null;
        int i = 0;
        while (i < edificios.size() && !encontrado) {
            if (nombreEdificio == ((Edificio) edificios.get(i)).getNombre()) {
                encontrado = true;
                ed = (Edificio) edificios.get(i);
            }
            i++;
        }
        return ed;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List getEdificios() {
        return edificios;
    }
}




