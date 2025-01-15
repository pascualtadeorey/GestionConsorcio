package service;

import dao.DAOEdificio;
import dao.DAOException;
import entidades.Edificio;

import java.util.ArrayList;

public class ServiceEdificio {
    private DAOEdificio daoEdificio;

    public ServiceEdificio() {
        daoEdificio = new DAOEdificio();
    }

    public void guardarEdificio(Edificio edificio) throws ServiceException {
        try {
            daoEdificio.guardar(edificio);
        } catch (DAOException e) {
            if (e.getMessage().contains("PRIMARY KEY")){
                throw new ServiceException("Clave duplicada, ya existe ese ID");
            }
        }
    }

    public void modificarEdificio(Edificio edificio) throws ServiceException {
        try {
            daoEdificio.modificar(edificio);
        } catch (DAOException e) {
            throw new ServiceException("Error al eliminar el edificio");
        }
    }

    public Edificio buscarEdificio(int id) throws ServiceException {
        Edificio edificio = null;
        try {
            edificio = daoEdificio.buscar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error al buscar el edificio");
        }
        return edificio;
    }

    public void borrarEdificio(int id) throws ServiceException {
        try {
            daoEdificio.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error al eliminar el edificio");
        }
    }

    public ArrayList<Edificio> todosLosEdificios() throws ServiceException {
        ArrayList<Edificio> edificios;
        try {
            edificios = daoEdificio.buscarTodos();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return edificios;
    }
}

