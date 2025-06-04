package clasesPersonas;

import java.util.List;

public class Admin extends Persona{
    public Admin(String nombreYapellido, String dni, String contrasenia) {
        super(nombreYapellido, dni, contrasenia);
    }

    public Admin() {
    }

    public static Admin buscarAdminPorDNI (String dni, List<Admin> lista){
        Admin admin = null;
        for (Admin a : lista){
            if (a.getDni().equals(dni)){
                admin = a;
            }
        }
        return admin;
    }
}
