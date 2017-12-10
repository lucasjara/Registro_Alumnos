/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lucas
 */
public class JRollback {
    boolean flag = false;
    public boolean tr_ingresar_campos(ArrayList<String> arrayString,ArrayList<Integer> arrayInt,ArrayList<Date> arrayDate){
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        PreparedStatement stmt4 = null;
        PreparedStatement stmt5 = null;
        PreparedStatement stmt6 = null;
        try {
            cn.setAutoCommit(false);
            stmt1 = cn.prepareStatement("INSERT INTO tb_familiares VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            stmt2 = cn.prepareStatement("INSERT INTO miTabla VALUES( ?, ? );");
            stmt3 = cn.prepareStatement("INSERT INTO miTabla VALUES( ?, ? );");
            stmt4 = cn.prepareStatement("INSERT INTO miTabla VALUES( ?, ? );");
            stmt5 = cn.prepareStatement("INSERT INTO miTabla VALUES( ?, ? );");
            stmt6 = cn.prepareStatement("INSERT INTO miTabla VALUES( ?, ? );");
            // Tabla con Datos de Familiares
             stmt1.setString(2, "asd");
             stmt1.setString(3, "micorreo@mail.com");
             stmt1.executeUpdate();
             
             cn.commit();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
             if(cn!=null)
             {
                 System.out.println("Rollback");
                 try {
                     //deshace todos los cambios realizados en los datos
                     cn.rollback();
                 } catch (SQLException ex1) {
                     System.err.println( "No se pudo deshacer" + ex1.getMessage() );    
                 }
             }      
        }finally{
            System.out.println( "cierra conexion a la base de datos" );    
             try {
                 if(stmt1!=null) stmt1.close();                
                 if(stmt2!=null) stmt2.close();                
                 if(cn!=null) cn.close();
             } catch (SQLException ex) {
                 System.err.println( ex.getMessage() );    
             }
        }
        return flag;
    }
    conectar cc = new conectar();
    Connection cn = this.cc.conexion();
}
