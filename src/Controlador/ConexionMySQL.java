/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author MEDAC
 */
public class ConexionMySQL {
    //Atributos
    private final String BD, USUARIO, PASS, HOST;
    private Connection connection;
    private TimeZone zonaHoraria;
    
    //Constructores
    public ConexionMySQL(String usuario, String pass, String bd){
        HOST = "localhost";
        USUARIO = usuario;
        PASS = pass;
        BD = bd;
        connection = null;
    }
    
    //Metodos
    //Conectamos con la base de datos
    public void conectar() throws SQLException, ClassNotFoundException{
        if(connection == null || connection.isClosed()){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Calendar now = Calendar.getInstance();
                zonaHoraria = now.getTimeZone();
                connection = (Connection)DriverManager.getConnection("jdbc:mysql://"+HOST+"/"+BD+"?user="+USUARIO+"&password="+PASS+"&userLegacyDateTimeCode=false&serverTimeZone="+zonaHoraria.getID());
            } catch(SQLException e){
                System.out.println("Error");
            }
        }
    }
    
    //Desconectamos de la base de datos
    public void desconectar() throws SQLException{
        if(connection != null && !connection.isClosed()){
            connection.close();
        }
    }
    
    //Ejecutar consulta SELECT
    public ResultSet ejercutarSelect(String consulta) throws SQLException{
        Statement stmt = connection.createStatement();
        ResultSet rset = stmt.executeQuery(consulta);
        return rset;
    }
    
    //Ejecutar consulta INSERT, DELETE y UPDATE
    public int ejecutarInsertDeleteUpdate(String consulta) throws SQLException{
        Statement stmt = connection.createStatement();
        int fila = stmt.executeUpdate(consulta);
        return fila;
    }
}

