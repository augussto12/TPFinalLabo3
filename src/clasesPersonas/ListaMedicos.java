package clasesPersonas;
import extras.Especialidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaMedicos{
    private List<Medico> medicos;

    public ListaMedicos() {
        this.medicos = new ArrayList<>();
    }

    public void agregarMedico (String nombre, String dni, long telefono, Especialidades especialidad,int edad,String contrasenia )  {
        for (Medico m : medicos){
            if (Objects.equals(m.getDni(), dni)){
                System.out.printf("\nEse medico ya existe.");
            }else{
                int id = medicos.size() + 1; // ID incremental basado en el tamaño
                Medico medico = new Medico(nombre,edad,dni,telefono,contrasenia,especialidad,id);
                medicos.add(medico);
            }
        }
        //cargar en archivo JSON
    }
    public static Medico buscarMedicoPorId(int id, List<Medico>medicos)  {
        Medico medico = null;
        for (Medico m : medicos){
            if (m.getId() == id){
                medico = m;
            }
        }
        return medico;
    }

    public static void listarMedicos(List<Medico>medicos) {
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
        } else {
            System.out.println("Lista de médicos:");
            //leer JSON
        }
    }

    public int getUltimoId() {
        return medicos.size();
    }
}