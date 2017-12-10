package modelo;

import conexion.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import vista.interfaz_ingreso;

public class Familiar {
    //Variable Utilizada para Ingresar Consultas
    private String CadSql = "";
    // Bandera para evaluar en metodos
    private boolean flag = false;

    public void obtener_tipo_prevision() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_prevision";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_prevision.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_prevision.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_prevision.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_estado_salud() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_estado_salud";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_salud_familiar.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_salud_familiar.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_salud_familiar.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_religion() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_religion";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_religion_familiar.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_religion_familiar.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_religion_familiar.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_nivel_educacional() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_nivel_educacional";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_nivel_educacional_madre_familiar.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_nivel_educacional_madre_familiar.setSelectedIndex(0);
            interfaz_ingreso.cbo_nivel_educacional_padre_familiar.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_nivel_educacional_padre_familiar.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_nivel_educacional_madre_familiar.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
                interfaz_ingreso.cbo_nivel_educacional_padre_familiar.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_vinculo_alumno() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_vinculo_alumno";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_vinculo_alumno_apoderado.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_vinculo_alumno_apoderado.setSelectedIndex(0);
            interfaz_ingreso.cbo_jefe_hogar.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_jefe_hogar.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_vinculo_alumno_apoderado.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
                interfaz_ingreso.cbo_jefe_hogar.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    conectar cc = new conectar();
    Connection cn = this.cc.conexion();
}
