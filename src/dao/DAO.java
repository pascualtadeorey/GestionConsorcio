package dao;

import entidades.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class DAO<T extends Entity> implements IDAO<T> {
    private Connection conexion;
    protected PreparedStatement preparedStatement;

    protected void connectToDB() throws DAOException {
        conexion = null;
        preparedStatement = null;
        try {
            Class.forName(DatosBD.DB_JDBC_DRIVER);
            conexion = DriverManager.getConnection(DatosBD.DB_URL, DatosBD.DB_USER, DatosBD.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("ERROR al conectar a la base de datos");
        }

    }

    protected PreparedStatement setQuery(String query) throws DAOException {
        try {
            return conexion.prepareStatement(query);
        } catch (SQLException e) {
            throw new DAOException("ERROR generando la consulta SQL");
        }
    }

    protected void closeConnections() throws DAOException {
        try {
            preparedStatement.close();
            conexion.close();
        } catch (SQLException e) {
            throw new DAOException("ERROR al cerrar la conexion");
        }
    }
}