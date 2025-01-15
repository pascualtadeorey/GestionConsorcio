package dao;

import entidades.Edificio;
import entidades.Entrada;
import entidades.Gastos;
import entidades.UnidadFuncional;

import java.sql.*;
import java.util.ArrayList;

public class DAOEdificio extends DAO<Edificio> {
    @Override
    public void guardar(Edificio elemento) throws DAOException {
        connectToDB();
        preparedStatement = setQuery("INSERT INTO EDIFICIO VALUES (?,?,?,?)");
        try {
            preparedStatement.setInt(1,elemento.getId());
            preparedStatement.setString(2,elemento.getNombre());
            preparedStatement.setString(3,elemento.getUbicacion());
            preparedStatement.setInt(4,elemento.getPisos());
            preparedStatement.executeUpdate(); //se ejecuta la consulta

        }
        catch (SQLException e){ //ver si hay error de sql
            throw new DAOException(e.getMessage());
        }
        finally {
            closeConnections();
        }
    }
 
    @Override
    public void modificar(Edificio elemento) throws DAOException {
        connectToDB();
        preparedStatement = setQuery("UPDATE EDIFICIO SET NOMBRE=?,UBICACION=?,CANTPISOS=? WHERE ID=?");
        try {
            preparedStatement.setString(1,elemento.getNombre());
            preparedStatement.setString(2,elemento.getUbicacion());
            preparedStatement.setInt(3,elemento.getPisos());
            preparedStatement.setInt(4, elemento.getId());
            preparedStatement.executeUpdate(); //se ejecuta la consulta
        }
        catch (SQLException e){ //ver si hay error de sql
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
            }
        }


    @Override
    public void eliminar(int id) throws DAOException {
        connectToDB();
        preparedStatement = setQuery("DELETE FROM EDIFICIO WHERE ID=?");
        try {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate(); //se ejecuta la consulta
        }
        catch (SQLException e){ //ver si hay error de sql
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
        }
    }

    @Override
    public Edificio buscar(int id) throws DAOException {
       connectToDB();
       preparedStatement = setQuery("SELECT * FROM EDIFICIO WHERE ID=?");
        Edificio edificio = null;
        try {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery(); //se ejecuta la consulta
            while (rs.next()) {
                edificio = new Edificio(rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("UBICACION"),
                        rs.getInt("CANTPISOS"),
                new ArrayList<Gastos>(),new ArrayList<Entrada>(),new ArrayList<UnidadFuncional>());
            }
        }
        catch (SQLException e){ //ver si hay error de sql
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
        }
        return edificio;
    }

    @Override
    public ArrayList<Edificio> buscarTodos() throws DAOException {
        connectToDB();
        preparedStatement = setQuery("SELECT * FROM EDIFICIO");
        Edificio edificio = null;
        ArrayList<Edificio> edificios = new ArrayList<>();
        try {
            ResultSet rs = preparedStatement.executeQuery(); //se ejecuta la consulta
            while (rs.next()) {
                edificio = new Edificio(rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("UBICACION"),
                        rs.getInt("CANTPISOS"),
                new ArrayList<Gastos>(),new ArrayList<Entrada>(),new ArrayList<UnidadFuncional>());
                edificios.add(edificio);
            }
        }
        catch (SQLException e){ //ver si hay error de sql
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
        }
        return edificios;
    }

}



