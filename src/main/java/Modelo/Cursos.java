package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Cursos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int curso_id;
    private String curso_nombre;
    
    @ManyToMany(mappedBy = "curso_asignado")
    private List<Estudiantes> estudiante_asignado;

    public Cursos() {
    }

    public Cursos(int curso_id, String curso_nombre, List<Estudiantes> estudiante_asignado) {
        this.curso_id = curso_id;
        this.curso_nombre = curso_nombre;
        this.estudiante_asignado = estudiante_asignado;
    }

    public int getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }

    public String getCurso_nombre() {
        return curso_nombre;
    }

    public void setCurso_nombre(String curso_nombre) {
        this.curso_nombre = curso_nombre;
    }

    public List<Estudiantes> getEstudiante_asignado() {
        return estudiante_asignado;
    }

    public void setEstudiante_asignado(List<Estudiantes> estudiante_asignado) {
        this.estudiante_asignado = estudiante_asignado;
    }
}
