package clasesPersonas;

import extras.Especialidades;

public class Medico extends  Persona{
    private Especialidades especialidad;
    private int id;


    public Medico(String nombreYapellido, long telefono, long dni, int edad, int id) {
        super(nombreYapellido, telefono, dni, edad);
        this.especialidad = especialidad;
        this.id = id;
    }


    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
