/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author MEDAC
 */
public class ControladorUsuarios {
    //Atributos
    private final ConexionMySQL conexion;
    
    //Constructor
    public ControladorUsuarios(ConexionMySQL conexion){
        this.conexion = conexion;
    }
    
    //Obtengo todos los artículos de la lista
    public boolean comprobarUsuario(String usu, String contra) throws SQLException{
        ArrayList<Usuario> lista = new ArrayList<>();
        String consulta = "SELECT * FROM login_data"; //Editar nombre tabla aquí
        ResultSet rset = conexion.ejercutarSelect(consulta);
        while(rset.next()){
            String user = rset.getString("Usuario"); //Editar nombre de las columnas de la tabla y el tipo de variable
            String pass = rset.getString("Contraseña");
            String answer = rset.getString("Respuesta");
            Usuario usuario = new Usuario(user,pass,answer); //Cambiar por el nombre de las columnas
            lista.add(usuario);
        }
        
        boolean correcto = false;
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getUser().equals(usu) && (lista.get(i).getPass().equals(contra))){
                correcto = true;
            } else {
                correcto = false;
            }
        }
        
        return correcto;
    }
    
    public boolean comprobarRespuesta (String respuesta) throws SQLException{
        ArrayList<Usuario> lista = new ArrayList<>();
        String consulta = "SELECT * FROM login_data"; //Editar nombre tabla aquí
        ResultSet rset = conexion.ejercutarSelect(consulta);
        while(rset.next()){
            String user = rset.getString("Usuario"); //Editar nombre de las columnas de la tabla y el tipo de variable
            String pass = rset.getString("Contraseña");
            String answer = rset.getString("Respuesta");
            Usuario usuario = new Usuario(user,pass,answer); //Cambiar por el nombre de las columnas
            lista.add(usuario);
        }
        
        boolean correcto = false;
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getAnswer().equals(respuesta)){
                correcto = true;
                JOptionPane.showMessageDialog(null, "La contraseña es: " + lista.get(i).getPass(), "Contraseña recuperada con éxito", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                correcto = false;
            }
        }
        return correcto;
    }
    
}
