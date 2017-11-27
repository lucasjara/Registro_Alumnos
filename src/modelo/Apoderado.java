/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vista.interfaz_ingreso;

/**
 *
 * @author Lucas
 */
public class Apoderado {
    //Variable Utilizada para Ingresar Consultas
    private String CadSql = "";
    // Bandera para evaluar en metodos
    private boolean flag = false;
    
    public void obtener_tipo_apoderado() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_tipo_apoderado";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_tipo_apoderado.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_tipo_apoderado.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_tipo_apoderado.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_tipo_prevision() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_prevision";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_vinculo_alumno.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_vinculo_alumno.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_vinculo_alumno.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    conectar cc = new conectar();
    Connection cn = this.cc.conexion();
}
