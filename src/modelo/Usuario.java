/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import vista.interfaz_ingreso;
import static vista.usuarios.panel_listado.tb_listado_usuarios;

/**
 *
 * @author Lucas
 */
public class Usuario {

    //Variable Utilizada para Ingresar Consultas
    private String CadSql = "";
    // Bandera para evaluar en metodos
    private boolean flag = false;

    /**
     * Obtenemos el listado de Alumnos para la carga inicial del Mantenedor
     *
     * @param texto Campo de Busqueda
     * @param opcion Parametro para eleccion de Sql a usar
     */
    public void obtener_listado_alumnos(String texto, int opcion) {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] cabeceras = new String[]{"ID", "RUT", "NOMBRE", "APODERADO", "PARENTESCO"};
        modelo.setColumnIdentifiers(cabeceras);
        switch (opcion) {
            case 1:
                CadSql = "SELECT alumno.id, CONCAT(alumno.RUT,'-',alumno.DV) as rut, alumno.NOMBRES as NOMBRE_ALUMNO, apoderado.NOMBRES as NOMBRE_APODERADO, vinculo.DESCRIPCION AS VINCULO_APODERADO "
                        + "FROM tb_alumno as alumno "
                        + "JOIN tb_apoderados as apoderado " + "ON apoderado.TB_ALUMNO_ID=alumno.ID "
                        + "JOIN tb_vinculo_alumno as vinculo " + "ON apoderado.TB_VINCULO_ALUMNO_ID=vinculo.ID";
                break;
            case 2:
                CadSql = "SELECT t.* FROM (SELECT alumno.id, CONCAT(alumno.RUT,'-',alumno.DV) as rut, alumno.NOMBRES as NOMBRE_ALUMNO, apoderado.NOMBRES as NOMBRE_APODERADO, vinculo.DESCRIPCION AS VINCULO_APODERADO "
                        + "FROM tb_alumno as alumno "
                        + "JOIN tb_apoderados as apoderado " + "ON apoderado.TB_ALUMNO_ID=alumno.ID "
                        + "JOIN tb_vinculo_alumno as vinculo " + "ON apoderado.TB_VINCULO_ALUMNO_ID=vinculo.ID "
                        + ") t WHERE t.rut LIKE '%" + texto + "%' or  t.NOMBRE_ALUMNO LIKE '%" + texto + "%' or t.NOMBRE_APODERADO LIKE '%" + texto + "%' or t.VINCULO_APODERADO LIKE '%" + texto + "%'";
                break;
        }
        try {
            String[] datos = new String[cabeceras.length];
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            }
            tb_listado_usuarios.setModel(modelo);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    // Metodo para Ingresar Alumnos 
    public boolean IngresarAlumno(int rut, String dv, String Nombres, String Apellido_M, String Apellido_P, Date fecha, String domicilio, String numero, int comuna_id) {
        try {
            PreparedStatement pst = this.cn.prepareStatement("INSERT INTO tb_alumno(RUT,DV,NOMBRES,APELLIDO_MAT,APELLIDO_PAT,FECHA_NACIMIENTO,DOMICILIO,NUMERO,TB_COMUNA_ID) VALUES (?,?,?,?,?,?,?,?,?");
            pst.setInt(1, rut);
            pst.setString(2, dv);
            pst.setString(3, Nombres);
            pst.setString(4, Apellido_M);
            pst.setString(5, Apellido_P);
            pst.setDate(6, (java.sql.Date) fecha);
            pst.setString(7, domicilio);
            pst.setString(8, numero);
            pst.setInt(9, comuna_id);
            pst.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    // Actualizamos Datos para el Alumno
    public boolean ActualizarDatosAlumno(int rut, String dv, String Nombres, String Apellido_M, String Apellido_P, Date fecha, String domicilio, String numero, int comuna_id, int id) {
        try {
            PreparedStatement pst = this.cn.prepareStatement("UPDATE tb_alumno SET RUT=?,DV=?,NOMBRES=?,APELLIDO_MAT=?,APELLIDO_PAT=?,APELLIDO_FECHA_NACIMIENTO=?,DOMICILIO=?,NUMERO=?,TB_FAMILIARES_ID=?,APELLIDO_PAT=? WHERE ID=?;");
            pst.setInt(1, rut);
            pst.setString(2, dv);
            pst.setString(3, Nombres);
            pst.setString(4, Apellido_M);
            pst.setString(5, Apellido_P);
            pst.setDate(6, (java.sql.Date) fecha);
            pst.setString(7, domicilio);
            pst.setString(8, numero);
            pst.setInt(9, comuna_id);
            pst.setInt(10, id);
            pst.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public void obtener_comunas() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_comuna";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_comuna.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_comuna.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_comuna.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_curso() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_curso";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_curso.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_curso.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_curso.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_establecimiento() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_establecimiento";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_establecimiento.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_establecimiento.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_establecimiento.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_especialidad() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_especialidad";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_especialidad.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_especialidad.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_especialidad.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_tipo_establecimiento() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_tipo_establecimiento";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_tipo_establecimiento.insertItemAt("Seleccione",0);
            interfaz_ingreso.cbo_tipo_establecimiento.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_tipo_establecimiento.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_ascendencia() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_ascendencia_indigena";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_ascendencia.insertItemAt("Seleccionar",0);
            interfaz_ingreso.cbo_ascendencia.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_ascendencia.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_lugar_vive() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_lugar_vive";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_lugar_vive.insertItemAt("Seleccionar",0);
            interfaz_ingreso.cbo_lugar_vive.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_lugar_vive.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void obtener_periodo_viaje() {
        try {
            CadSql = "SELECT id,descripcion FROM tb_periodo_viaje";
            Statement st = this.cn.createStatement();
            ResultSet rs = st.executeQuery(CadSql);
            interfaz_ingreso.cbo_periodo_viaje.insertItemAt("Ninguno",0);
            interfaz_ingreso.cbo_periodo_viaje.setSelectedIndex(0);
            while (rs.next()) {
                interfaz_ingreso.cbo_periodo_viaje.insertItemAt(rs.getString(2), Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    conectar cc = new conectar();
    Connection cn = this.cc.conexion();
}
