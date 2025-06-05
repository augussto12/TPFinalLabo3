package Validaciones;

import clasesManejoTurnos.Turno;

import java.util.Scanner;

public class Validar {

    public static int validarSwitch(int max) {
        Scanner scan = new Scanner(System.in);
        int eleccion = validarEntero();

        while (eleccion > max || eleccion < 0) {
            System.out.println("Ingreo invalido. Cargue otro numero: ");
            eleccion = validarEntero();

        }
        return eleccion;
    }

    public static int validarEntero() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingresa un número entero.");
            scan.next();
        }
        int valor = scan.nextInt();
        scan.nextLine();
        return valor;
    }

    //CHEQUEAR QUE ESTE BIEN LA VALIDACION DE STRING
    public static String validarString() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) { // Si detecta un entero, lo rechaza
            System.out.println("Entrada inválida. Por favor, ingresa solo texto.");
            scan.next(); // Descartar la entrada incorrecta
        }
        return scan.nextLine(); // Capturar la entrada válida
    }

    public static long validarLong() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextLong()) {
            System.out.println("Entrada inválida.");
            scan.next();
        }
        long valor = scan.nextLong();
        scan.nextLine();
        return valor;
    }
}
