package dao;

import entidades.UnidadFuncional;

import java.sql.*;
import java.util.ArrayList;

public class DAOUnidadFuncional extends DAO<UnidadFuncional> implements IDAO<UnidadFuncional> {
    @Override
    public void guardar(UnidadFuncional elemento) throws DAOException {
        this.connectToDB();
        preparedStatement = setQuery("INSERT INTO UNIDADFUNCIONAL VALUES (?,?,?,?,?,?)");
        try {
            preparedStatement.setInt(1, elemento.getId());
            preparedStatement.setString(2, elemento.getNombreOcupante());
            preparedStatement.setInt(3, elemento.getMetrosCuadrados());
            preparedStatement.setInt(4, elemento.getPisos());
            preparedStatement.setDouble(5, elemento.getValorExpensas());
            preparedStatement.setInt(6, elemento.getFk());
            preparedStatement.executeUpdate(); //se ejecuta la consulta
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }

    }

    @Override
    public void modificar(UnidadFuncional elemento) throws DAOException {
        this.connectToDB();
        preparedStatement = setQuery("UPDATE UNIDADFUNCIONAL SET NOMBREOCUPANTE=?,METROSCUADRADOS=?,PISO=?,VALOREXPENSA=? WHERE ID=?");
        try {
            preparedStatement.setString(1, elemento.getNombreOcupante());
            preparedStatement.setInt(2, elemento.getMetrosCuadrados());
            preparedStatement.setInt(3, elemento.getPisos());
            preparedStatement.setDouble(4, elemento.getValorExpensas());
            preparedStatement.setInt(5, elemento.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }

    }


    @Override
    public void eliminar(int id) throws DAOException {
        this.connectToDB();
        preparedStatement = setQuery("DELETE FROM UNIDADFUNCIONAL WHERE ID=?");
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }

    }

    @Override
    public UnidadFuncional buscar(int id) throws DAOException {
        UnidadFuncional unidadFuncional = null;
        this.connectToDB();
        preparedStatement = setQuery("SELECT * FROM UNIDADFUNCIONAL WHERE ID=?");
        try {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                unidadFuncional = new UnidadFuncional(rs.getInt("ID"), rs.getString("NOMBREOCUPANTE"), rs.getInt("METROSCUADRADOS"), rs.getInt("PISO"), rs.getDouble("VALOREXPENSA"),rs.getInt("ID_EDIF"));
            }
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        } finally {
           closeConnections();
        }
        return unidadFuncional;
    }

    @Override
    public ArrayList<UnidadFuncional> buscarTodos() throws DAOException {
        UnidadFuncional unidadFuncional = null;
        ArrayList<UnidadFuncional> unidadFuncionales = new ArrayList<>();
        this.connectToDB();
        preparedStatement = setQuery("SELECT * FROM UNIDADFUNCIONAL");
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                unidadFuncional = new UnidadFuncional(rs.getInt("ID"), rs.getString("NOMBREOCUPANTE"), rs.getInt("METROSCUADRADOS"), rs.getInt("PISO"), rs.getDouble("VALOREXPENSA"),rs.getInt("ID_EDIF"));
                unidadFuncionales.add(unidadFuncional);
            }
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }
        return unidadFuncionales;
    }

    public ArrayList<UnidadFuncional> buscarTodasLasUFDeUnEdificio(int id) throws DAOException {
        UnidadFuncional unidadFuncional = null;
        ArrayList<UnidadFuncional> unidadFuncionales = new ArrayList<>();
        this.connectToDB();
        preparedStatement = setQuery("SELECT * FROM UNIDADFUNCIONAL WHERE ID_EDIF = ?");
        try {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                unidadFuncional = new UnidadFuncional(rs.getInt("ID"), rs.getString("NOMBREOCUPANTE"), rs.getInt("METROSCUADRADOS"), rs.getInt("PISO"), rs.getDouble("VALOREXPENSA"),rs.getInt("ID_EDIF"));
                unidadFuncionales.add(unidadFuncional);
            }
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }
        return unidadFuncionales;
    }
}
