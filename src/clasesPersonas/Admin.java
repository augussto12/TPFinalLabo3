package clasesPersonas;

import manejoJSON.LeerArchivoPersonas;
import menu.MenuPrincipal;
import org.json.JSONException;

import java.util.List;
import java.util.Scanner;

public class Admin extends Persona {
    public Admin(String nombreYapellido, long dni, String contrasenia) {
        super(nombreYapellido, dni, contrasenia);
    }

    public Admin() {
    }

    public static Admin buscarAdminPorUser(String user, List<Admin> lista) {
        Admin admin = null;
        for (Admin a : lista) {
            if (a.getNombreYapellido().equals(user)) {
                admin = a;
            }
        }
        return admin;
    }

    public static void iniciarSesionAdmin(List<Admin> administradores) throws JSONException {
        Scanner scan = new Scanner(System.in);
        System.out.printf("\ningrese su usuario: ");
        String userAdmin = scan.nextLine();
        Admin admin = Admin.buscarAdminPorUser(userAdmin, administradores);
        if (admin == null) {
            System.out.println("Admin no encontrado.");
            System.out.print("\nPresione Enter para continuar...");
            scan.nextLine();
            MenuPrincipal.menu();
        }
        System.out.println("\nIngrese su contrasenia: ");
        String contraseniaAdmin = scan.nextLine();
        if (!admin.getContrasenia().equals(contraseniaAdmin)) {
            System.out.println("Contrasenia incorrecta.");
            System.out.print("\nPresione Enter para continuar...");
            scan.nextLine();
            MenuPrincipal.menu();
        }
        MenuPrincipal.menuAdmin();
    }

}
