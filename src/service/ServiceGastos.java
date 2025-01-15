package service;

import dao.DAOEdificio;
import dao.DAOException;
import dao.DAOGastos;
import entidades.Edificio;
import entidades.Gastos;

import java.util.ArrayList;

public class ServiceGastos {
    private DAOGastos daoGastos;

    public ServiceGastos() {
        daoGastos = new DAOGastos();
    }

    public void guardarGasto(Gastos gasto) throws ServiceException {
        try {
            daoGastos.guardar(gasto);
        } catch (DAOException e) {
            throw new ServiceException("Error al guardar el gasto");
        }
    }

    public void modificarGasto(Gastos gasto) throws ServiceException {
        try {
            daoGastos.modificar(gasto);
        } catch (DAOException e) {
            throw new ServiceException("Error al modificar el gasto");
        }
    }

    public Gastos buscarGasto(int id) throws ServiceException {
        Gastos gasto = null;
        try {
            gasto = daoGastos.buscar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error al buscar el gasto");
        }
        return gasto;
    }

    public void borrarGasto(int id) throws ServiceException {
        try {
            daoGastos.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error al eliminar el gasto");
        }
    }

    public ArrayList<Gastos> todoslosGastos() throws ServiceException {
        ArrayList<Gastos> gastos;
        try {
            gastos = daoGastos.buscarTodos();
        } catch (DAOException e) {
            throw new ServiceException("Error al buscar todos los gastos");
        }
        return gastos;
    }

    public int ultimoID(){
        int id;
        try {
            id = daoGastos.ultimoID();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
