package menu;

import java.util.Scanner;

public class MenuPrincipal {
    public void menu (){
        Scanner scan = new Scanner(System.in);
        int eleccion = 0;
        System.out.printf("\n======HOSPITAL========");
        System.out.printf("\n[ 1 ] Si es administrador.");
        System.out.printf("\n[ 2 ] Si es un paciente.");
        System.out.printf("\n[ 0 ] Si desea terminar el programa");
        System.out.printf("\n\nSu eleccion: ");
        eleccion = scan.nextInt();
        switch (eleccion){
            case 1:
                //chequeo si existe ese admin y si la contra es correcta
                //menuAdmin();
                break;
            case 2:
                //chequeo si existe ese user y si la contra es correcta con nombre y dni
                //menuUser();
                break;
        }

    }
    public static void menuAdmin (){
        //eliminar turno
        //modificar turno
        //ver turnos propios
    }

    public static void menuUser (){
        //ver medicos
        //agregar turno
        //eliminar SU turno
        //modificar SU turno
        //
    }

}
