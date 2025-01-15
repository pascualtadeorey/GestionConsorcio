package dao;

import entidades.Gastos;

import java.sql.*;

import java.util.ArrayList;

public class DAOGastos extends DAO<Gastos>{

    @Override
    public void guardar(Gastos elemento) throws DAOException {

        this.connectToDB();
        preparedStatement = setQuery("INSERT INTO GASTO VALUES (?,?,?,?,?)");
        try {
            preparedStatement.setInt(1, elemento.getId());
            preparedStatement.setString(2, elemento.getConcepto());
            preparedStatement.setInt(3, elemento.getValor());
            preparedStatement.setDate(4, Date.valueOf(elemento.getFecha()));
            preparedStatement.setInt(5, elemento.getId_edif());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error en la consulta");
        }
        finally {
           closeConnections();
        }
    }


    @Override
    public void modificar(Gastos elemento) throws DAOException {
        this.connectToDB();
        preparedStatement = setQuery("UPDATE GASTO SET CONCEPTO=?,VALOR=?,FECHA=? WHERE ID=?");
        try {
            preparedStatement.setString(1,elemento.getConcepto());
            preparedStatement.setInt(2,elemento.getValor());
            preparedStatement.setDate(3,Date.valueOf(elemento.getFecha()));
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
        }

    }

    @Override
    public void eliminar(int id) throws DAOException {
        this.connectToDB();
        preparedStatement = setQuery("DELETE FROM GASTO WHERE ID=?");
        try {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
        }

    }

    @Override
    public Gastos buscar(int id) throws DAOException {
        Gastos gasto = null;
        this.connectToDB();
        preparedStatement = setQuery("SELECT * FROM GASTO");
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                gasto = new Gastos(rs.getInt("ID"), rs.getInt("ID_ED"), rs.getString("CONCEPTO"), rs.getInt("VALOR"),rs.getDate("FECHA").toLocalDate());
            }
        } catch (SQLException e){
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
        }
        return gasto;
    }

    @Override
    public ArrayList<Gastos> buscarTodos() throws DAOException {
        this.connectToDB();
        preparedStatement = this.setQuery("SELECT * FROM GASTO");
        Gastos gastos;
        ArrayList<Gastos> listaGastos = new ArrayList<>();
        try {
            ResultSet rs = preparedStatement.executeQuery(); //se ejecuta la consulta
            while (rs.next()) {
                gastos = new Gastos(rs.getInt("ID"),rs.getInt("ID_EDIFICIO"), rs.getString("CONCEPTO"), rs.getInt("VALOR"),rs.getDate("FECHA").toLocalDate());
                listaGastos.add(gastos);
            }
        }
        catch (SQLException e){ //ver si hay error de sql
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
        }
        return listaGastos;
    }

    public int ultimoID() throws DAOException{
        int id = 0;
        this.connectToDB();
        preparedStatement = this.setQuery("SELECT * FROM GASTO");
        try {
            ResultSet rs = preparedStatement.executeQuery(); //se ejecuta la consulta
            while (rs.next()) {
                id = rs.getInt("ID");
            }
        }
        catch (SQLException e){ //ver si hay error de sql
            throw new DAOException("Error en la consulta");
        }
        finally {
            closeConnections();
        }
        return id;
    }
}

