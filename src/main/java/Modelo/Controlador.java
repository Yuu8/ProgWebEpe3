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
}
