package clasesPersonas;

import java.util.List;

public class Admin extends Persona{
    public Admin(String nombreYapellido, String dni, String contrasenia) {
        super(nombreYapellido, dni, contrasenia);
    }

    public Admin() {
    }

    public static Admin buscarAdminPorUser (String user, List<Admin> lista){
        Admin admin = null;
        for (Admin a : lista){
            if (a.getNombreYapellido().equals(user)){
                admin = a;
            }
        }
        return admin;
    }
}
