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


    public static Medico buscarMedicoPorId(int id, List<Medico>medicos)  {
        Medico medico = null;
        for (Medico m : medicos){
            if (m.getId() == id){
                medico = m;
            }
        }
        return medico;
    }


    public int getUltimoId() {
        return medicos.size();
    }
}