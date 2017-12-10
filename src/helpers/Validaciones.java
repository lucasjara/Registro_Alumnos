package helpers;

import java.awt.HeadlessException;
import java.util.Date;
import javax.swing.JOptionPane;

public class Validaciones {

    public boolean flag = false;
    /**
     * arreglo[0] = S/N arreglo [1] = Motivo
     */
    public String[] arreglo = new String[2];

    /**
     *
     * @param nombres
     * @param apellido_pat
     * @param apellido_mat
     * @param rut
     * @param dv
     * @param fecha_nacimiento
     * @param direccion
     * @param telefono
     * @param curso
     * @param fecha_matricula
     * @param poblacion
     * @param comuna
     * @param establecimiento
     * @param tipo_establecimiento
     * @param repite_curso
     * @param cual
     * @param especialidad
     * @param sector_vive
     * @param viaja
     * @param donde_vive
     * @param otros
     * @param ascendencia
     * @param certificado_nacimiento
     * @param certificado_programa_puente
     * @param Certificado_estudio
     * @param necesita_pae
     * @param pertenece_programa_pruente
     * @return Arreglo con respuesta y Motivo de validacion de campos
     * completados
     */
    public String[] validarUsuario(String nombres, String apellido_pat, String apellido_mat, int rut, String dv, Date fecha_nacimiento, String direccion, String telefono, int curso, Date fecha_matricula, String poblacion, int comuna, int establecimiento, int tipo_establecimiento, String repite_curso, String cual, int especialidad, int sector_vive, int viaja, String donde_vive, String otros, int ascendencia, String certificado_nacimiento, String certificado_programa_puente, String Certificado_estudio, String necesita_pae, String pertenece_programa_pruente) {
        try {
            String[] agrupado_string = {nombres, apellido_pat, apellido_mat, dv, direccion, telefono, poblacion, donde_vive};
            for (String campo : agrupado_string) {
                if (validar_vacio(campo) && campo != null && validar_maximo_caracteres(campo)) {
                    flag = true;
                } else {
                    arreglo[0] = "N";
                    arreglo[1] = "El " + campo + " no paso la validacion";
                    flag = false;
                    break;
                }
            }

            int[] agrupado_int = {rut, curso, comuna, establecimiento, tipo_establecimiento, especialidad, sector_vive, ascendencia};
            if (flag == true) {
                for (int campo : agrupado_int) {
                    if (validar_cero(campo)) {
                        flag = true;
                    } else {
                        arreglo[0] = "N";
                        arreglo[1] = "Debe Seleccionar cada uno de las listas desplegables";
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                arreglo[0] = "S";
                arreglo[1] = "Validacion Correcta";
            }
        } catch (HeadlessException e) {
            arreglo[0] = "N";
            arreglo[1] = "Excepcion no controlada: " + e;
        }
        return arreglo;
    }

    public String[] validarFamiliar(String dv_padre, String nombre_padre, String ingreso_padre, String ocupacion_padre, String telefono_padre, String domicilio_padre, String dv_madre,
            String nombre_madre, String ingreso_madre, String ocupacion_madre, String telefono_madre, String domicilio_madre, String hermanos_estudiando, String dv_rut_jefe_hogar,
            int rut_padre, int nivel_educacional_padre, int rut_madre, int nivel_educacional_madre, int integrantes, int abuelos, int n_hermanos, int tios, int educacion_basica, int educacion_media,
            int educacion_universitaria, int relacion_jefe_hogar, int prevision, int salud, int religion, int rut_jefe_hogar) {
        try {
            String[] agrupado_string = {dv_padre, nombre_padre, ingreso_padre, ocupacion_padre, telefono_padre, domicilio_padre, dv_madre, nombre_madre, ingreso_madre,
                ocupacion_madre, telefono_madre, domicilio_madre, hermanos_estudiando, dv_rut_jefe_hogar};
            for (String campo : agrupado_string) {
                if (validar_vacio(campo) && campo != null && validar_maximo_caracteres(campo)) {
                    flag = true;
                } else {
                    arreglo[0] = "N";
                    arreglo[1] = "El " + campo + " no paso la validacion";
                    flag = false;
                    break;
                }
            }
            int[] agrupado_int = {rut_padre, nivel_educacional_padre, rut_madre, nivel_educacional_madre, relacion_jefe_hogar, prevision, salud, religion, rut_jefe_hogar};
            if (flag == true) {
                for (int campo : agrupado_int) {
                    if (validar_cero(campo)) {
                        flag = true;
                    } else {
                        arreglo[0] = "N";
                        arreglo[1] = "Debe Seleccionar cada uno de las listas desplegables";
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                arreglo[0] = "S";
                arreglo[1] = "Validacion Correcta";
            }
        } catch (HeadlessException e) {
            arreglo[0] = "N";
            arreglo[1] = "Excepcion no controlada: " + e;
        }
        return arreglo;
    }

    public String[] validarApoderado(String dv_apoderado, String nombres_apoderado, String apellido_mat_apoderado, String apellido_pat_apoderado, String telefono_apoderado,
            int rut_apoderado, int vinculo_alumno_apoderado, int tipo_apoderado) {
        try {
            String[] agrupado_string = {dv_apoderado, nombres_apoderado, apellido_mat_apoderado, apellido_pat_apoderado, telefono_apoderado};
            for (String campo : agrupado_string) {
                if (validar_vacio(campo) && campo != null && validar_maximo_caracteres(campo)) {
                    flag = true;
                } else {
                    arreglo[0] = "N";
                    arreglo[1] = "El " + campo + " no paso la validacion";
                    break;
                }
            }
            int[] agrupado_int = {rut_apoderado, vinculo_alumno_apoderado, tipo_apoderado};
            if (flag == true) {
                for (int campo : agrupado_int) {
                    if (validar_cero(campo)) {
                        flag = true;
                    } else {
                        arreglo[0] = "N";
                        arreglo[1] = "Debe Seleccionar cada uno de las listas desplegables";
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                arreglo[0] = "S";
                arreglo[1] = "Validacion Correcta";
            }
        } catch (HeadlessException e) {
            arreglo[0] = "N";
            arreglo[1] = "Excepcion no controlada: " + e;
        }
        return arreglo;
    }

    private boolean validar_vacio(String campo) {
        if (!campo.equals("")) {
            flag = true;
        }
        return flag;
    }

    private boolean validar_maximo_caracteres(String campo) {
        if (campo.length() <= 200) {
            flag = true;
        }
        return flag;
    }

    private boolean validar_cero(int numero) {
        flag = numero > 0;
        return flag;
    }
}
