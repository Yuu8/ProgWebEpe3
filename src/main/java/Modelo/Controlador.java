package Modelo;

import Persistencia.PersistenciaController;
import java.util.List;

public class Controlador {
    
    PersistenciaController PersisCtrl = new PersistenciaController();
    
    public List<Usuarios> obtenerUsuarios(){
        return PersisCtrl.obtenerUsuarios();
    }
}
