/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cancion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MEDAC
 */
public class ControladorCanciones {
    //Atributos
    private final ConexionMySQL conexion;
    
    //Constructor
    public ControladorCanciones(ConexionMySQL conexion){
        this.conexion = conexion;
    }
    
    //Obtengo todos los artículos de la lista
    public ArrayList<Cancion> obtenerCanciones() throws SQLException{
        ArrayList<Cancion> lista = new ArrayList<>();
        String consulta = "SELECT * FROM songs"; //Editar nombre tabla aquí
        ResultSet rset = conexion.ejercutarSelect(consulta);
        while(rset.next()){
            String title = rset.getString("Título"); //Editar nombre de las columnas de la tabla y el tipo de variable
            String album = rset.getString("Álbum");
            String artist = rset.getString("Artista");
            String duration = rset.getString("Duración");
            Cancion song = new Cancion(title, album, artist, duration); //Cambiar por el nombre de las columnas
            lista.add(song);
        }
        
        return lista;
    }
}
