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
                    break;
                }
            }
            int[] agrupado_int = {rut, curso, comuna, establecimiento, tipo_establecimiento, especialidad, sector_vive, ascendencia};
            for (int campo : agrupado_int) {
                if (validar_cero(campo)) {
                    flag = true;
                } else {
                    arreglo[0] = "N";
                    arreglo[1] = "El " + campo + " no paso la validacion";
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

    public String[] validarFamiliar() {

        return arreglo;
    }

    public String[] validarApoderado() {

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
        if (numero > 0) {
            flag = true;
        }
        return flag;
    }
}
