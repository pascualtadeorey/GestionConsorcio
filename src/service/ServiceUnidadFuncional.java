package service;

import dao.DAOException;
import dao.DAOUnidadFuncional;
import entidades.UnidadFuncional;

import java.util.ArrayList;

public class ServiceUnidadFuncional {

    private DAOUnidadFuncional daoUnidadFuncional;

    public ServiceUnidadFuncional(){
        daoUnidadFuncional = new DAOUnidadFuncional();
    }

    public void guardarUnidadFuncional(UnidadFuncional unidadFuncional) throws ServiceException{
        try {
            daoUnidadFuncional.guardar(unidadFuncional);
        } catch (DAOException e) {
            throw new ServiceException("Error al guardar el departamento");
        }
    }

    public void modificarUnidadFuncional(UnidadFuncional unidadFuncional) throws ServiceException{
        try {
            daoUnidadFuncional.modificar(unidadFuncional);
        } catch (DAOException e) {
            throw new ServiceException("Error al modificar el departamento");
        }
    }

    public UnidadFuncional buscarUnidadFuncional(int id) throws ServiceException{
        UnidadFuncional uf = null;
        try {
            uf=daoUnidadFuncional.buscar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error al buscar el departamento");
        }
        return uf;
    }

    public void borrarUnidadFuncional(int id) throws ServiceException{
        try {
            daoUnidadFuncional.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error al eliminar el departamento");
        }
    }

    public ArrayList<UnidadFuncional> todosLasUF() throws ServiceException{
        ArrayList<UnidadFuncional> unidadFuncionales;
        try {
            unidadFuncionales = daoUnidadFuncional.buscarTodos();
        } catch (DAOException e) {
            throw new ServiceException("Error al buscar todos los departamentos");
        }
        return unidadFuncionales;
    }

    public ArrayList<UnidadFuncional> buscarTodasLasUFDeUnEdificio(int id) throws ServiceException{
        ArrayList<UnidadFuncional> unidadFuncionales;
        try {
            unidadFuncionales = daoUnidadFuncional.buscarTodasLasUFDeUnEdificio(id);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return unidadFuncionales;
    }
}
