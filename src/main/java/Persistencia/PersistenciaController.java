package Persistencia;

import Modelo.*;
import java.util.List;

public class PersistenciaController {
    UsuariosJpaController usuJpa = new UsuariosJpaController();
    CursosJpaController curJpa = new CursosJpaController();
    EstudiantesJpaController estJpa = new EstudiantesJpaController();
    
    public List<Usuarios> obtenerUsuarios(){
        return usuJpa.findUsuariosEntities();
    }
    
    public void CrearEstudiante(Estudiantes est){
        estJpa.create(est);
    }
    
    public void CrearMateria(Cursos curs){
        curJpa.create(curs);
    }
    
    public List<Estudiantes> listaEstudiantes(){
        return estJpa.findEstudiantesEntities();
    }
    
    public List<Cursos> listaCursos(){
        return curJpa.findCursosEntities();
    }
    
    public Estudiantes obtEstudiante(int idEstudiante){
        return estJpa.findEstudiantes(idEstudiante);
    }
    
    public Cursos obtCurso(int idCurso){
        return curJpa.findCursos(idCurso);
    }
    
    public void editarEstudiante(Estudiantes est){
        try {
            estJpa.edit(est);
        } catch (Exception e) {
            System.out.println("Error al editar estudiante: "+e.getMessage());
        }
    }
    
    public void editarMateria(Cursos curs){
        try {
            curJpa.edit(curs);
        } catch (Exception e) {
            System.out.println("Error al editar curso: "+e.getMessage());
        }
    }
    
    public void EliminaEstudiante(int idEstudiante){
        try {
            estJpa.destroy(idEstudiante);
        } catch (Exception e) {
            System.out.println("Error al eliminar estudiante: "+e.getMessage());
        }
    }
    
    public void EliminaMateria(int idMateria){
        try {
            curJpa.destroy(idMateria);
        } catch (Exception e) {
            System.out.println("Error al eliminar materia: "+e.getMessage());
        }
    }
}
