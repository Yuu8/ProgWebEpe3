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
}
