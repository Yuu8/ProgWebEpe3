package Modelo;

import Persistencia.PersistenciaController;
import java.util.List;

public class Controlador {
    
    PersistenciaController PersisCtrl = new PersistenciaController();
    
    public List<Usuarios> obtenerUsuarios(){
        return PersisCtrl.obtenerUsuarios();
    }
    
    public void CrearEstudiante(Estudiantes est){
        PersisCtrl.CrearEstudiante(est);
    }
    
    public void CrearMateria(Cursos cur){
        PersisCtrl.CrearMateria(cur);
    }
    
    public List<Estudiantes> listaEstudiantes(){
        return PersisCtrl.listaEstudiantes();
    }
    
    public List<Cursos> listaCursos(){
        return PersisCtrl.listaCursos();
    }
    
    public void EliminarEstudiante(int idEstudiante){
        PersisCtrl.EliminaEstudiante(idEstudiante);
    }
    
    public void EliminarMateria(int idMateria){
        PersisCtrl.EliminaMateria(idMateria);
    }
}
