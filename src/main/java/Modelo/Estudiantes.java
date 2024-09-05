package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Estudiantes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int estudiante_id;
    private String estudiante_nombre;
    
    @ManyToMany
    @JoinTable(
            name = "usuarios_cursos",
            joinColumns = @JoinColumn(name = "id_estudiante"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private List<Cursos> curso_asignado;

    public Estudiantes() {
    }

    public Estudiantes(int estudiante_id, String estudiante_nombre, List<Cursos> curso_asignado) {
        this.estudiante_id = estudiante_id;
        this.estudiante_nombre = estudiante_nombre;
        this.curso_asignado = curso_asignado;
    }

    public int getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(int estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    public String getEstudiante_nombre() {
        return estudiante_nombre;
    }

    public void setEstudiante_nombre(String estudiante_nombre) {
        this.estudiante_nombre = estudiante_nombre;
    }

    public List<Cursos> getCurso_asignado() {
        return curso_asignado;
    }

    public void setCurso_asignado(List<Cursos> curso_asignado) {
        this.curso_asignado = curso_asignado;
    }
}
