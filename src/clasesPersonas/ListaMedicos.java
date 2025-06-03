package clasesPersonas;
import extras.Especialidades;

import java.util.ArrayList;
import java.util.List;

public class ListaMedicos{
    private List<Medico> medicos;

    public ListaMedicos() {
        this.medicos = new ArrayList<>();
    }

    public void agregarMedico (String nombre, long dni, long telefono, Especialidades especialidad,int edad )  {
        for (Medico m : medicos){
            if (m.getDni() == dni){
                System.out.printf("\nEse medico ya existe.");
            }else{
                int id = medicos.size() + 1; // ID incremental basado en el tamaño
                Medico medico = new Medico(nombre,telefono, dni, edad, id);
                medicos.add(medico);
            }
        }
        //cargar en archivo JSON
    }
    public Medico buscarMedicoPorId(int id)  {
        Medico medico = null;
        for (Medico m : medicos){
            if (m.getId() == id){
                medico = m;
            }
        }
        return medico;
    }

    public void listarMedicos() {
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