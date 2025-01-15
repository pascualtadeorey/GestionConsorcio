package service;

import dao.DAOException;
import dao.DAOIngreso;
import entidades.Edificio;
import entidades.Entrada;
import entidades.Gastos;

import java.util.ArrayList;

public class ServiceIngreso {
    private DAOIngreso daoIngreso;

    public ServiceIngreso() {
        daoIngreso = new DAOIngreso();
    }

    public void guardarIngreso(Entrada entrada) throws ServiceException {
        try {
            daoIngreso.guardar(entrada);
        } catch (DAOException e) {
            throw new ServiceException("Error al guardar el ingreso");
        }
    }

    public void modificarIngreso(Entrada entrada) throws ServiceException {
        try {
            daoIngreso.modificar(entrada);
        } catch (DAOException e) {
            throw new ServiceException("Error al modificar el ingreso");
        }
    }

    public Entrada buscarIngreso(int id) throws ServiceException {
        Entrada entrada = null;
        try {
            entrada = daoIngreso.buscar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error al buscar el ingreso");
        }
        return entrada;
    }

    public void borrarIngreso(int id) throws ServiceException {
        try {
            daoIngreso.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error al eliminar el ingreso");
        }
    }

    public ArrayList<Entrada> todoslosingresos() throws ServiceException {
        ArrayList<Entrada> entradas;
        try {
            entradas = daoIngreso.buscarTodos();
        } catch (DAOException e) {
            throw new ServiceException("Error al buscar todos los ingresos");
        }
        return entradas;
    }

    public int UltimoID() throws ServiceException{
        int id = 0;
        try {
            id = daoIngreso.ultimoID();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return id;
        }
    }
}
