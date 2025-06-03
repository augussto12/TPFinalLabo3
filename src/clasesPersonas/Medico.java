package clasesPersonas;

import extras.Especialidades;

public class Medico extends  Persona{
    private Especialidades especialidad;
    private int id;


    public Medico(String nombreYapellido, int edad, String dni, long telefono, String contrasenia, Especialidades especialidad, int id) {
        super(nombreYapellido, edad, dni, telefono, contrasenia);
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
