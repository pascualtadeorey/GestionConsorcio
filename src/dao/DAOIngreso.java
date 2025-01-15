package dao;

import entidades.Entrada;

import java.sql.*;
import java.util.ArrayList;

public class DAOIngreso extends DAO<Entrada> {

    @Override
    public void guardar(Entrada elemento) throws DAOException {
        this.connectToDB();
        preparedStatement = setQuery("INSERT INTO INGRESO VALUES (?,?,?,?,?)");
        try {
            preparedStatement.setInt(1, elemento.getID());
            preparedStatement.setInt(2, elemento.getValor());
            preparedStatement.setDate(3, Date.valueOf(elemento.getFecha()));
            preparedStatement.setInt(4, elemento.getFkUF());
            preparedStatement.setInt(5, elemento.getFkED());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }
    }


    @Override
    public void modificar(Entrada elemento) throws DAOException {
        this.connectToDB();
        preparedStatement = setQuery("UPDATE INGRESO SET VALOR=?,FECHA=? WHERE ID=?");
        try {
            preparedStatement.setInt(1, elemento.getValor());
            preparedStatement.setDate(2, Date.valueOf(elemento.getFecha()));
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
        preparedStatement = setQuery("DELETE FROM INGRESO WHERE ID=?");
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
    public Entrada buscar(int id) throws DAOException {
        Entrada entrada = null;
        this.connectToDB();
        preparedStatement = setQuery("SELECT * FROM INGRESO");
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                entrada = new Entrada(rs.getInt("ID"), rs.getInt("ID_UF"), rs.getInt("ID_ED"), rs.getInt("VALOR"), rs.getDate("FECHA").toLocalDate());
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }
        return entrada;
    }


    @Override
    public ArrayList buscarTodos() throws DAOException {
        Entrada entrada = null;
        ArrayList<Entrada> listaEntradas = new ArrayList<>();
        this.connectToDB();
        preparedStatement = setQuery("SELECT * FROM INGRESO");
        try {
            ResultSet rs = preparedStatement.executeQuery(); //se ejecuta la consulta
            while (rs.next()) {
                entrada = new Entrada(rs.getInt("ID"), rs.getInt("ID_UF"), rs.getInt("ID_ED"), rs.getInt("VALOR"), rs.getDate("FECHA").toLocalDate());
                listaEntradas.add(entrada);
            }
        } catch (SQLException e) { //ver si hay error de sql
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }
        return listaEntradas;
    }

    public int ultimoID() throws DAOException {
        int id = 0;
        Entrada entrada = null;
        this.connectToDB();
        preparedStatement = setQuery("SELECT * FROM INGRESO");
        try {
            ResultSet rs = preparedStatement.executeQuery(); //se ejecuta la consulta
            while (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException e) { //ver si hay error de sql
            throw new DAOException("Error en la consulta");
        } finally {
            closeConnections();
        }
        return id;
    }
}
